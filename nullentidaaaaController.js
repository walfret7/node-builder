const entidaaaaModel = require('../models/entidaaaaModel');

class entidaaaaController {
  static async getAll(req, res) {
    try {
      const data = await entidaaaaModel.getAll();
      res.json(data);
    } catch (error) {
      console.error('Error al obtener entidaaaa:', error);
      res.status(500).json({ error: 'Error interno del servidor' });
    }
  }

  static async getById(req, res) {
    const { id } = req.params;
    try {
      const data = await entidaaaaModel.getById(id);
      if (data) {
        res.json(data);
      } else {
        res.status(404).json({ error: 'entidaaaa no encontrado' });
      }
    } catch (error) {
      console.error('Error al obtener entidaaaa por ID:', error);
      res.status(500).json({ error: 'Error interno del servidor' });
    }
  }

  static async create(req, res) {
    const {nombre} = req.body;
    try {
      const nuevoId = await entidaaaaModel.create(nombre);
      res.status(201).json({ id: nuevoId, mensaje: 'entidaaaa creado exitosamente' });
    } catch (error) {
      console.error('Error al crear entidaaaa:', error);
      res.status(500).json({ error: 'Error interno del servidor' });
    }
  }

  static async update(req, res) {
    const { id } = req.params;
    const { nombre } = req.body;
    try {
      await entidaaaaModel.update(id, nombre);
      res.json({ mensaje: 'entidaaaa actualizado exitosamente' });
    } catch (error) {
      console.error('Error al actualizar entidaaaa:', error);
      res.status(500).json({ error: 'Error interno del servidor' });
    }
  }

  static async delete(req, res) {
    const { id } = req.params;
    try {
      await entidaaaaModel.delete(id);
      res.json({ mensaje: 'entidaaaa eliminado exitosamente' });
    } catch (error) {
      console.error('Error al eliminar entidaaaa:', error);
      res.status(500).json({ error: 'Error interno del servidor' });
    }
  }
}

module.exports = entidaaaaController;
