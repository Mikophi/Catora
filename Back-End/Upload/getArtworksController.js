const express = require('express');
const path = require('path');
const router = express.Router();
const db = require('../connection');
const { resolve } = require('url');


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
      const resolvedImageUrl = artwork.image_url
        ? resolve('http://localhost:3000/upload/images/', path.basename(artwork.image_url))
        : null;

      return {
        ...artwork,
        image_url: resolvedImageUrl,
        id: artwork.id || null, // Pastikan id tidak undefined
      };
    });

    res.status(200).json(artworksWithResolvedUrls);
  });
});

module.exports = router;
