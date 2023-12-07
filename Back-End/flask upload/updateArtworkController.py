from flask import Flask, request, jsonify
from flask import Blueprint
import os
import pymysql
from datetime import datetime

app = Flask(__name__)
artwork_routes = Blueprint('artwork_routes', __name__)

UPLOAD_FOLDER = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'Back-End', 'Upload', 'Art-Here')
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

def allowed_file(filename):
    return '.' in filename

def create_db_connection():
    # Adjust the database connection parameters
    return pymysql.connect(host='your_host', user='your_user', password='your_password', database='your_db')

def update_artwork_file(artwork_id, title, description, tags, file):
    try:
        # Create a database connection
        connection = create_db_connection()

        with connection.cursor() as cursor:
            # Fetch artwork data
            cursor.execute('SELECT * FROM artworks WHERE artwork_id = %s', (artwork_id,))
            artwork = cursor.fetchone()

            if not artwork:
                return jsonify({'error': 'Artwork not found'}), 404

            # Delete the existing image file
            if artwork['image_url']:
                os.remove(artwork['image_url'])

            # Save the new image file
            if file and allowed_file(file.filename):
                filename = f'image-{artwork_id}-{datetime.now().timestamp()}{os.path.splitext(file.filename)[1]}'
                file_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)
                file.save(file_path)

                # Update artwork data in the database
                cursor.execute("""
                    UPDATE artworks
                    SET title = %s, description = %s, tags = %s, image_url = %s, updated_at = CURRENT_TIMESTAMP
                    WHERE artwork_id = %s
                """, (title, description, tags, file_path, artwork_id))

                connection.commit()
                return jsonify({'message': 'Artwork updated successfully'}), 200
            else:
                return jsonify({'error': 'Invalid file format'}), 400

    except Exception as e:
        print(f'Error updating artwork: {e}')
        return jsonify({'error': 'Internal Server Error'}), 500

    finally:
        connection.close()

@artwork_routes.route('/artworks/<artwork_id>', methods=['PUT'])
def update_artwork(artwork_id):
    # Check if the request contains a file part
    if 'image' in request.files:
        file = request.files['image']
    else:
        file = None

    title = request.form.get('title')
    description = request.form.get('description')
    tags = request.form.get('tags')

    return update_artwork_file(artwork_id, title, description, tags, file)

app.register_blueprint(artwork_routes)

if __name__ == '__main__':
    app.run(debug=True)
