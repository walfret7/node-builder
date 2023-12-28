package nodeComponents;

import java.util.ArrayList;
import java.util.List;

import components.Module;

public class Controller {

	public static String getController(Module module) {
		
		
		String name=module.getName();
		boolean getAll = module.isSelectAll();
		boolean getById = module.isSelectById();
		boolean create = module.isCreate();
		boolean update = module.isUpdate();
		boolean delete = module.isDelete();
		
		List<String> attrs = new ArrayList<String>();
		
		module.getAtributos().forEach(attr->attrs.add(attr.getName()));
		
		String controller = 
				"const "+name+"Model = require('../models/"+name+"Model');\r\n"
				+ "\r\n"	
				+ "class "+name+"Controller {\r\n"
				+ " ";
				
				controller += getAll? " static async getAll(req, res) {\r\n"
				+ "    try {\r\n"
				+ "      const data = await "+name+"Model.getAll();\r\n"
				+ "      res.json(data);\r\n"
				+ "    } catch (error) {\r\n"
				+ "      console.error('Error al obtener "+name+":', error);\r\n"
				+ "      res.status(500).json({ error: 'Error interno del servidor' });\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "\r\n":"\r\n";
				
				controller += getById? "  static async getById(req, res) {\r\n"
				+ "    const { id } = req.params;\r\n"
				+ "    try {\r\n"
				+ "      const data = await "+name+"Model.getById(id);\r\n"
				+ "      if (data) {\r\n"
				+ "        res.json(data);\r\n"
				+ "      } else {\r\n"
				+ "        res.status(404).json({ error: '"+name+" no encontrado' });\r\n"
				+ "      }\r\n"
				+ "    } catch (error) {\r\n"
				+ "      console.error('Error al obtener "+name+" por ID:', error);\r\n"
				+ "      res.status(500).json({ error: 'Error interno del servidor' });\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "\r\n":"\r\n";
				
				controller += create? "  static async create(req, res) {\r\n"
				+ "    const {"+String.join(", ", attrs)+"} = req.body;\r\n"
				+ "    try {\r\n"
				+ "      const nuevoId = await "+name+"Model.create("+String.join(", ", attrs)+");\r\n"
				+ "      res.status(201).json({ id: nuevoId, mensaje: '"+name+" creado exitosamente' });\r\n"
				+ "    } catch (error) {\r\n"
				+ "      console.error('Error al crear "+name+":', error);\r\n"
				+ "      res.status(500).json({ error: 'Error interno del servidor' });\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "\r\n":"\r\n";
				
				controller += update? "  static async update(req, res) {\r\n"
				+ "    const { id } = req.params;\r\n"
				+ "    const { "+String.join(", ", attrs)+" } = req.body;\r\n"
				+ "    try {\r\n"
				+ "      await "+name+"Model.update(id, "+String.join(", ", attrs)+");\r\n"
				+ "      res.json({ mensaje: '"+name+" actualizado exitosamente' });\r\n"
				+ "    } catch (error) {\r\n"
				+ "      console.error('Error al actualizar "+name+":', error);\r\n"
				+ "      res.status(500).json({ error: 'Error interno del servidor' });\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "\r\n":"\r\n";
				
				controller += delete? "  static async delete(req, res) {\r\n"
				+ "    const { id } = req.params;\r\n"
				+ "    try {\r\n"
				+ "      await "+name+"Model.delete(id);\r\n"
				+ "      res.json({ mensaje: '"+name+" eliminado exitosamente' });\r\n"
				+ "    } catch (error) {\r\n"
				+ "      console.error('Error al eliminar "+name+":', error);\r\n"
				+ "      res.status(500).json({ error: 'Error interno del servidor' });\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}\r\n":"\r\n";
				
				controller+= "\r\n"
				+ "module.exports = "+name+"Controller;\r\n";
		
		
		return controller;
	}
}
