from flask import Flask, jsonify, send_from_directory
from flask import Blueprint
import os
import pymysql

app = Flask(__name__)
artwork_routes = Blueprint('artwork_routes', __name__)

UPLOAD_FOLDER = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'Back-End', 'Upload', 'Art-Here')
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

@artwork_routes.route('/images/<filename>')
def get_image(filename):
    return send_from_directory(app.config['UPLOAD_FOLDER'], filename)

@artwork_routes.route('/artworks', methods=['GET'])
def get_artworks():
    try:
        # Create a database connection
        connection = pymysql.connect(host='your_host', user='your_user', password='your_password', database='your_db')
        with connection.cursor() as cursor:
            # Fetch artworks from the database
            cursor.execute('SELECT * FROM artworks')
            results = cursor.fetchall()

            # Create a list of artwork objects with resolved image URLs
            artworksWithResolvedUrls = []
            for artwork in results:
                if artwork['image_url']:
                    artwork['image_url'] = f'http://localhost:5000/images/{os.path.basename(artwork["image_url"])}'
                artworksWithResolvedUrls.append(artwork)

            return jsonify(artworksWithResolvedUrls), 200

    except Exception as e:
        print(f'Error fetching artworks: {e}')
        return jsonify({'error': 'Internal Server Error'}), 500

    finally:
        connection.close()

app.register_blueprint(artwork_routes)

if __name__ == '__main__':
    app.run(debug=True)
