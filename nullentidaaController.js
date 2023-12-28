const entidaaModel = require('../models/entidaaModel');

class entidaaController {
  static async getAll(req, res) {
    try {
      const data = await entidaaModel.getAll();
      res.json(data);
    } catch (error) {
      console.error('Error al obtener entidaa:', error);
      res.status(500).json({ error: 'Error interno del servidor' });
    }
  }

  static async getById(req, res) {
    const { id } = req.params;
    try {
      const data = await entidaaModel.getById(id);
      if (data) {
        res.json(data);
      } else {
        res.status(404).json({ error: 'entidaa no encontrado' });
      }
    } catch (error) {
      console.error('Error al obtener entidaa por ID:', error);
      res.status(500).json({ error: 'Error interno del servidor' });
    }
  }

  static async create(req, res) {
    const {nombre} = req.body;
    try {
      const nuevoId = await entidaaModel.create(nombre);
      res.status(201).json({ id: nuevoId, mensaje: 'entidaa creado exitosamente' });
    } catch (error) {
      console.error('Error al crear entidaa:', error);
      res.status(500).json({ error: 'Error interno del servidor' });
    }
  }

  static async update(req, res) {
    const { id } = req.params;
    const { nombre } = req.body;
    try {
      await entidaaModel.update(id, nombre);
      res.json({ mensaje: 'entidaa actualizado exitosamente' });
    } catch (error) {
      console.error('Error al actualizar entidaa:', error);
      res.status(500).json({ error: 'Error interno del servidor' });
    }
  }

  static async delete(req, res) {
    const { id } = req.params;
    try {
      await entidaaModel.delete(id);
      res.json({ mensaje: 'entidaa eliminado exitosamente' });
    } catch (error) {
      console.error('Error al eliminar entidaa:', error);
      res.status(500).json({ error: 'Error interno del servidor' });
    }
  }
}

module.exports = entidaaController;
