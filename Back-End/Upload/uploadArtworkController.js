const express = require('express');
const router = express.Router();
const multer = require('multer');
const path = require('path');
const fs = require('fs');
const db = require('../connection');
const tf = require('@tensorflow/tfjs-node');

const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, path.join(process.cwd(), 'Back-End', 'Upload', 'Art-Here'));
  },
  filename: function (req, file, cb) {
    cb(null, file.fieldname + '-' + Date.now() + path.extname(file.originalname));
  },
});

const upload = multer({ storage });

async function loadModel() {
  return await tf.loadLayersModel('file://./Capstone_model.h5');
}

router.post('/artworks', upload.single('image'), async (req, res) => {
  const { user_id, title, description, tags } = req.body;
  if (!user_id || !title || !req.file) {
    return res.status(400).json({ error: 'Missing required fields' });
  }

  const image_url = req.file.path;

  // Extract features from the uploaded image
  const features = extractFeatures(image_url);

  // Load the model asynchronously
  const model = await loadModel();

  // Predict using the loaded model
  const predictions = await model.predict(features);

  // Check if the image is created by AI
  const isAIImage = predictions.arraySync()[0][0] > 0.5;

  // Store the image or reject it
  if (isAIImage) {
    res.status(403).json({ error: 'Artwork created by AI is not allowed' });
  } else {
    // Save the image
    const savePath = path.join(process.cwd(), 'Back-End', 'Upload', 'Art-Here', image_url);
    fs.copyFileSync(req.file.path, savePath);

    // Store the image data in the database
    const insertQuery = `
      INSERT INTO artworks (user_id, title, description, tags, image_url)
      VALUES (?, ?, ?, ?, ?)
    `;

    db.query(insertQuery, [user_id, title, description, tags, image_url], (error, results) => {
      if (error) {
        console.error('Error creating artwork:', error);
        return res.status(500).json({ error: 'Internal Server Error' });
      }

      res.status(201).json({ message: 'Artwork uploaded successfully' });
    });
  }
});

// Function to extract features from an image
function extractFeatures(image_url) {
  // ... Your code to extract features from the image
  // ...

  return features;
}

module.exports = router;
