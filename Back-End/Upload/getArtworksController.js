const express = require('express');
const path = require('path');
const router = express.Router();
const db = require('../connection');
const { resolve } = require('url');

router.use('/images', express.static(path.join(__dirname, '../Back-End/Upload/Art-Here')));

router.get('/artworks', (req, res) => {
  const selectQuery = `
    SELECT * FROM artworks
  `;

  db.query(selectQuery, (error, results) => {
    if (error) {
      console.error('Error fetching artworks:', error);
      return res.status(500).json({ error: 'Internal Server Error' });
    }

    const artworksWithResolvedUrls = results.map((artwork) => {
      return {
        ...artwork,
        image_url: artwork.image_url
          ? resolve('http://localhost:3000/images', artwork.image_url)
          : null,
      };
    });

    res.status(200).json(artworksWithResolvedUrls);
  });
});

module.exports = router;
