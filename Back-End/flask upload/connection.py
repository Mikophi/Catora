from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

# Replace 'mysql://root:@localhost/catora' with your actual database connection details
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:@localhost/catora'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)
db.init_app(app)

with app.app_context():
    db.create_all()

if __name__ == '__main__':
    app.run(debug=True)
