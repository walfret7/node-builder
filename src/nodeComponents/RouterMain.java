package nodeComponents;

import java.util.List;

public class RouterMain {

	
	public static String getRouter(List<String> modules) {
		
		String m="";
		for (String module: modules) {
			m+="const "+module+"Routes = require('./"+module+"Routes');\r\n"
			+ "router.use('/api/"+module+"', "+module+"Routes);\r\n";
		}
		
		String router=""
				+ "const express = require('express');\r\n"
				+ "const router = express.Router();\r\n"
				+ "\r\n"
				+ m
				+ "\r\n"
				+ "module.exports = router;";
		
		return router;
	}	
	
}
