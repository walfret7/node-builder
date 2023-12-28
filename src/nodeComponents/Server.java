package nodeComponents;

public class Server {

	public static String getServer(String port) {
				
		String server=""
				+ "const express = require('express');\r\n"
				+ "const cors = require('cors');\r\n"
				+ "const routes = require('./routes/routes');\r\n"
				+ "\r\n"
				+ "const app = express();\r\n"
				+ "const port = "+port+";\r\n"
				+ "\r\n"
				+ "app.use(express.json());\r\n"
				+ "app.use(cors());\r\n"
				+ "\r\n"
				+ "// Rutas\r\n"
				+ "app.use(routes);\r\n"
				+ "\r\n"
				+ "app.listen(port, () => {\r\n"
				+ "  console.log(`Servidor en ejecución en http://localhost:${port}`);\r\n"
				+ "});";
		
		return server;
	}
	
}
