package nodeComponents;



import components.Module;
import queryComponents.Query;


public class Migrate {

	
	
	public static String getMigrate(Module module) {
		
		
		Query query = new Query(module.getTable());
		
		String migrate = ""
				+ "const pool = require('../config/dbConfig');\r\n"
				+ "\r\n"
				+ "const dropTableQuery = '"+query.dropQuery()+"';"
				+ "\r\n"
				+ "const createTableQuery = '"+query.createQuery()+"';"
				+ "\r\n"
				+ "(async () => {\r\n"
				+ "  const connection = await pool.getConnection();\r\n"
				+ "\r\n"
				+ "  try {\r\n"
				+ "    // Ejecuta DROP TABLE\r\n"
				+ "    await connection.query(dropTableQuery);\r\n"
				+ "    console.log('Tabla eliminada (si existía)');\r\n"
				+ "\r\n"
				+ "    // Ejecuta CREATE TABLE después de DROP TABLE\r\n"
				+ "    await connection.query(createTableQuery);\r\n"
				+ "    console.log('Tabla creada exitosamente.');\r\n"
				+ "  } catch (err) {\r\n"
				+ "    console.error('Error al ejecutar las sentencias:', err);\r\n"
				+ "  } finally {\r\n"
				+ "    // Libera la conexión después de ejecutar las consultas\r\n"
				+ "    connection.release();\r\n"
				+ "  }\r\n"
				+ "})();";
		
		return migrate;
	}
	
}
