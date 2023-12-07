from flask import Flask, request, jsonify
from flask import Blueprint
import os
import pymysql

# Assuming you have a database connection function named create_db_connection
# and assuming you have a similar delete_artwork_file function
from utils import create_db_connection, delete_artwork_file

app = Flask(__name__)
artwork_routes = Blueprint('artwork_routes', __name__)

def delete_artwork_file(image_path):
    try:
        os.remove(image_path)
    except FileNotFoundError:
        pass
    except Exception as e:
        print(f'Error deleting image file: {e}')

@artwork_routes.route('/artworks/<artwork_id>', methods=['DELETE'])
def delete_artwork(artwork_id):
    try:
        # Create a database connection
        connection = create_db_connection()

        with connection.cursor() as cursor:
            # Fetch the artwork data
            cursor.execute('SELECT * FROM artworks WHERE artwork_id = %s', (artwork_id,))
            artwork = cursor.fetchone()

            if not artwork:
                return jsonify({'error': 'Artwork not found'}), 404

            # Delete the artwork file
            delete_artwork_file(artwork['image_url'])

            # Delete the artwork from the database
            cursor.execute('DELETE FROM artworks WHERE artwork_id = %s', (artwork_id,))
            connection.commit()

            return jsonify({'message': 'Artwork deleted successfully'}), 200

    except Exception as e:
        print(f'Error deleting artwork: {e}')
        return jsonify({'error': 'Internal Server Error'}), 500

    finally:
        connection.close()

app.register_blueprint(artwork_routes)

if __name__ == '__main__':
    app.run(debug=True)
