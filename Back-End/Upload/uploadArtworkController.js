// const express = require('express');
// const router = express.Router();
// const multer = require('multer');
// const path = require('path');
// const fs = require('fs');
// const db = require('../connection');

// const storage = multer.diskStorage({
//   destination: function (req, file, cb) {
//     cb(null, path.join(process.cwd(), 'images'));
//   },
//   filename: function (req, file, cb) {
//     cb(null, file.fieldname + '-' + Date.now() + path.extname(file.originalname));
//   },
// });

// const upload = multer({ storage });

// router.post('/artworks', upload.single('image'), (req, res) => {
//   const { user_id, title, description, tags } = req.body;
//   if (!user_id || !title || !req.file) {
//     return res.status(400).json({ error: 'Missing required fields' });
//   }

//   const image_url = req.file.path;

//   const insertQuery = `
//     INSERT INTO artworks (user_id, title, description, tags, image_url)
//     VALUES (?, ?, ?, ?, ?)
//   `;

//   db.query(insertQuery, [user_id, title, description, tags, image_url], (error, results) => {
//     if (error) {
//       console.error('Error creating artwork:', error);
//       return res.status(500).json({ error: 'Internal Server Error' });
//     }

//     res.status(201).json({ message: 'Artwork Uploaded successfully' });
//   });
// });

// module.exports = router;

const express = require('express');
const router = express.Router();
const multer = require('multer');
const path = require('path');
const fs = require('fs');
const db = require('../connection');

const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, path.join(process.cwd(), 'images'));
  },
  filename: function (req, file, cb) {
    cb(null, file.fieldname + '-' + Date.now() + path.extname(file.originalname));
  },
});

const upload = multer({ storage });

router.post('/artworks', upload.single('image'), (req, res) => {
  // Your existing code for handling image upload

  // Return a response indicating successful image upload
  res.status(201).json({ message: 'Artwork Uploaded successfully' });
});

// Add this route to serve static images
router.get('/images/:filename', (req, res) => {
  const filename = req.params.filename;
  const filePath = path.join(process.cwd(), 'images', filename);

  // Check if the file exists
  if (fs.existsSync(filePath)) {
    // Send the file as a response
    res.sendFile(filePath);
  } else {
    // If the file doesn't exist, return a 404 error
    res.status(404).json({ error: 'Image not found' });
  }
});

module.exports = router;
