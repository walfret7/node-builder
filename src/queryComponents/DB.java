package queryComponents;

public class DB {
	private String driver;
	private String name;
	private String port;
	private String host;
	private String password;
	private String user;

	public DB(String driver, String name, String port, String host, String password, String user) {
		this.driver = driver;
		this.name = name;
		this.port = port;
		this.host = host;
		this.password = password;
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
}
