from flask import Flask, request, jsonify
from flask import Blueprint

# Assuming you have controllers named upload_artwork_controller, 
# update_artwork_controller, get_artworks_controller, and delete_artwork_controller
from controllers import upload_artwork_controller, update_artwork_controller, get_artworks_controller, delete_artwork_controller

app = Flask(__name__)
artwork_routes = Blueprint('artwork_routes', __name__)

@artwork_routes.route('/artworks', methods=['POST'])
def upload_artwork():
    return upload_artwork_controller(request)

@artwork_routes.route('/artworks/<artwork_id>', methods=['PUT'])
def update_artwork(artwork_id):
    return update_artwork_controller(request, artwork_id)

@artwork_routes.route('/artworks', methods=['GET'])
def get_artworks():
    return get_artworks_controller(request)

@artwork_routes.route('/artworks/<artwork_id>', methods=['DELETE'])
def delete_artwork(artwork_id):
    return delete_artwork_controller(request, artwork_id)

app.register_blueprint(artwork_routes)

if __name__ == '__main__':
    app.run(debug=True)
