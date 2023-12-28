package nodeComponents;

import java.util.ArrayList;
import java.util.List;

import components.Module;
import queryComponents.Query;
import queryComponents.Table;

public class Model {

	public static String getModel(Module module) {
		
		String name=module.getName();
		boolean getAll = module.isSelectAll();
		boolean getById = module.isSelectById();
		boolean create = module.isCreate();
		boolean update = module.isUpdate();
		boolean delete = module.isDelete();
		Table table = module.getTable();
		
		List<String>cols=new ArrayList<String>();		
		table.getColumns().forEach(column->cols.add(column.getName()));
		cols.remove("id");
		Query query=new Query(table);
						
		String model=""
				+ "const pool = require('../config/dbConfig');\r\n"
				+ "\r\n"
				+ "class "+name+"Model {\r\n"
				+ "\r\n"
				+ "\r\n";
				
				model+=getAll? "static async getAll() {\r\n"
				+ "       const [rows] = await pool.query('"+query.selectAllQuery()+"');\r\n"
				+ "       return rows;\r\n"
				+ "    }\r\n"
				+ "\r\n":"\r\n";
				
				model+=getById? "static async getById(id) {\r\n"
				+ "        const [rows] = await pool.query('"+query.selectByIdQuery()+"', [id]);\r\n"
				+ "        return rows[0];\r\n"
				+ "    }\r\n"
				+ "\r\n":"\r\n";
				
				model+=create? "static async create("+String.join(",", cols)+") {\r\n"
				+ "        const [result] = await pool.query('"+query.insertQuery()+"', ["+String.join(",", cols)+"]);\r\n"
				+ "        return result.insertId;\r\n"
				+ "    }\r\n"
				+ "\r\n":"\r\n";
				
				model+=update? "static async update(id, "+String.join(",", cols)+") {\r\n"
				+ "        await pool.query('"+query.updateQuery()+"', ["+String.join(",", cols)+", id]);\r\n"
				+ "    }\r\n"
				+ "\r\n":"\r\n";
				
				model+=delete? "    static async delete(id) {\r\n"
				+ "        await pool.query('"+query.deleteQuery()+"', [id]);\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "}\r\n":"\r\n";
				
				model+= "\r\n"
				+ "module.exports = "+name+"Model;\r\n";
		
		return model;
	}
}
