import os
import mysql.connector
from mysql.connector import Error
from flask import Flask, request, jsonify
from werkzeug.utils import secure_filename

# Fungsi untuk membuat koneksi ke database MySQL
def create_connection():
    connection = None
    try:
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="",
            database="catora"
        )
        print("Connected to MySQL database!")
    except Error as e:
        print(f"Error connecting to MySQL: {e}")

    return connection

# Membuat aplikasi Flask
app = Flask(__name__)

# Menentukan direktori untuk unggahan file
upload_dir = os.path.join(os.getcwd(), 'Art-Here')
os.makedirs(upload_dir, exist_ok=True)
app.config['UPLOAD_FOLDER'] = upload_dir

# Mendefinisikan rute untuk unggah file
@app.route('/artworks', methods=['POST'])
def upload_file():
    if 'user_id' not in request.form or 'title' not in request.form or 'image' not in request.files:
        return jsonify(error='Missing required fields'), 400

    user_id = request.form['user_id']
    title = request.form['title']
    description = request.form.get('description')
    tags = request.form.get('tags')
    file = request.files['image']

    filename = secure_filename(file.filename)
    image_url = os.path.join(app.config['UPLOAD_FOLDER'], filename)
    file.save(image_url)

    conn = create_connection()
    cursor = conn.cursor()

    try:
        cursor.execute("""
            INSERT INTO artworks (user_id, title, description, tags, image_url)
            VALUES (%s, %s, %s, %s, %s)
        """, (user_id, title, description, tags, image_url))
        conn.commit()
        return jsonify(message='Artwork Uploaded successfully'), 201
    except Exception as e:
        print(f'Error creating artwork: {str(e)}')
        return jsonify(error='Internal Server Error'), 500

# Menjalankan aplikasi Flask
if __name__ == '__main__':
    app.run(debug=False)
