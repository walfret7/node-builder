package nodeComponents;

import components.Module;

public class Routes {

	/**
	 * Retorna la estructura del archivo de rutas para el modulo
	 * @param module el modulo
	 * @return String texto de rutas para el modulo
	 * */
	public static String getRoutes(Module module) {
		
		String name=module.getName();
		boolean getAll = module.isSelectAll();
		boolean getById = module.isSelectById();
		boolean create = module.isCreate();
		boolean update = module.isUpdate();
		boolean delete = module.isDelete();
		
		
		String  route=""
				+ "const express = require('express');\r\n"
				+ "const "+name+"Controller = require('../controllers/"+name+"Controller');\r\n"
				+ "\r\n"
				+ "const router = express.Router();\r\n"
				+ "\r\n"
				+ "\r\n";
				route += getAll ? "router.get('/', " + name + "Controller.getAll);\r\n" : "\r\n";
				route += getById ? "router.get('/:id', " + name + "Controller.getById);\r\n" : "\r\n";
				route += create ?  "router.post('/', " + name + "Controller.create);\r\n" : "\r\n";
				route += update ?  "router.put('/:id', " + name + "Controller.update);\r\n" : "\r\n";
				route += delete ?  "router.delete('/:id', " + name + "Controller.delete);\r\n" : "\r\n";
				route += "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "module.exports = router;\r\n"
				+ "";
		
		return route;
	}

}
