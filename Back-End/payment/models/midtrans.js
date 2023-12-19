var connection = require('../../db');
const Sequelize = require('sequelize');
const CatoraUser = require('./catoraUser'); // Pastikan Anda memiliki model untuk tabel catora_users

const Order = connection.define('order', {
  order_id: {
    type: Sequelize.INTEGER,
    primaryKey: true,
    autoIncrement: true,
  },
  seller_id: {
    type: Sequelize.INTEGER,
    allowNull: false,
    references: {
      model: CatoraUser, // Model untuk tabel catora_users
      key: 'user_id' // Kolom yang diacu di tabel catora_users
    }
  },
  buyer_id: {
    type: Sequelize.INTEGER,
    allowNull: false,
    references: {
      model: CatoraUser, // Model untuk tabel catora_users
      key: 'user_id' // Kolom yang diacu di tabel catora_users
    }
  },
  title: {
    type: Sequelize.TEXT,
    allowNull: false
  },
  description: {
    type: Sequelize.TEXT,
    allowNull: false
  },
  response_midtrans: {
    type: Sequelize.TEXT,
    allowNull: true
  },
  createdAt: {
    type: Sequelize.DATE,
    allowNull: false
  },
  updatedAt: {
    type: Sequelize.DATE,
    allowNull: false
  }
}, {
  timestamps: true,
  freezeTableName: true,
  tableName: 'order'
});

module.exports = Order;
