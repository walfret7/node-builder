const pool = require('../config/dbConfig');

class entidaaaaModel {


static async getAll() {
       const [rows] = await pool.query('SELECT * FROM queryComponents.Table@4400eafc');
       return rows;
    }

static async getById(id) {
        const [rows] = await pool.query('SELECT * FROM queryComponents.Table@4400eafc WHERE queryComponents.Table@4400eafc.id=?', [id]);
        return rows[0];
    }

static async create(nombre) {
        const [result] = await pool.query('INSERT INTO queryComponents.Table@4400eafc(queryComponents.Column@435a2a8)values(?)', [nombre]);
        return result.insertId;
    }

static async update(id, nombre) {
        await pool.query('UPDATE queryComponents.Table@4400eafc SET queryComponents.Column@435a2a8=? WHERE id=?', [nombre, id]);
    }

    static async delete(id) {
        await pool.query('DELETE FROM queryComponents.Table@4400eafc WHERE id=?', [id]);
    }

}

module.exports = UsuarioModel;
