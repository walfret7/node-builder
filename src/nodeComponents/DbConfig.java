package nodeComponents;

import queryComponents.DB;

public class DbConfig {

	
	public static String getConfig(DB db) {
		String config=
				"const sql = require('"+db.getDriver()+"');\r\n"
				+ "\r\n"
				+ "const dbConfig = {\r\n"
				+ "  host: '"+db.getHost()+"',\r\n"
				+ "  user: '"+db.getUser()+"',\r\n"
				+ "  password: '"+db.getPassword()+"',\r\n"
				+ "  port: '"+db.getPort()+"',\r\n"
				+ "  database: '"+db.getName()+"',\r\n"
				+ "};\r\n"
				+ "\r\n";
				
				
				config+= (db.getDriver().equals("mysql2"))?"const pool = sql.createPool(dbConfig);\r\n"
				+ "\r\n"
				+ "module.exports = pool.promise();\r\n"
				+ "":"const pool = new Pool(dbConfig);\r\n";
		return config;
	}
	
}
