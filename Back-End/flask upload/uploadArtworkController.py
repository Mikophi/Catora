from flask import Flask, request, jsonify
from werkzeug.utils import secure_filename
import os
import tensorflow as tf
from tensorflow.keras.preprocessing import image
import numpy as np
import pymysql  # Assuming you are using MySQL as the database

app = Flask(__name__)

UPLOAD_FOLDER = 'Back-End/Upload/Art-Here'
ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg', 'gif'}

app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

def load_model():
    return tf.keras.models.load_model('./Capstone_model.h5')  # Adjust the path as needed

@app.route('/artworks', methods=['POST'])
def upload_artwork():
    user_id = request.form.get('user_id')
    title = request.form.get('title')
    description = request.form.get('description')
    tags = request.form.get('tags')

    if 'image' not in request.files or not user_id or not title:
        return jsonify({'error': 'Missing required fields'}), 400

    file = request.files['image']

    if file.filename == '':
        return jsonify({'error': 'No selected file'}), 400

    if file and allowed_file(file.filename):
        filename = secure_filename(file.filename)
        file_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        file.save(file_path)

        # Extract features from the uploaded image
        features = extract_features(file_path)

        # Load the model
        model = load_model()

        # Predict using the loaded model
        predictions = model.predict(np.expand_dims(features, axis=0))

        # Check if the image is created by AI
        is_ai_image = predictions[0][0] > 0.5

        # Store the image or reject it
        if is_ai_image:
            os.remove(file_path)
            return jsonify({'error': 'Artwork created by AI is not allowed'}), 403
        else:
            # Save the image
            save_path = os.path.join(app.root_path, file_path)
            os.rename(file_path, save_path)

            # Store the image data in the database
            insert_query = """
                INSERT INTO artworks (user_id, title, description, tags, image_url)
                VALUES (%s, %s, %s, %s, %s)
            """

            db = pymysql.connect(host='your_host', user='your_user', password='your_password', database='your_db')
            cursor = db.cursor()

            try:
                cursor.execute(insert_query, (user_id, title, description, tags, file_path))
                db.commit()
                return jsonify({'message': 'Artwork uploaded successfully'}), 201
            except Exception as e:
                print('Error creating artwork:', e)
                db.rollback()
                return jsonify({'error': 'Internal Server Error'}), 500
            finally:
                cursor.close()
                db.close()

    else:
        return jsonify({'error': 'Invalid file format'}), 400

def extract_features(image_path):
    # ... Your code to extract features from the image
    # ...

    return features

if __name__ == '__main__':
    app.run(debug=True)
