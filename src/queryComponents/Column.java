package queryComponents;

public class Column {
	
	private String name;
	private String type;
	private String Default;
	private String lenght;
	private boolean required;
	public Column(String name, String type, String default1, String lenght, boolean required, boolean autoincrement,
			boolean fk, boolean pk) {

		this.name = name;
		this.type = type;
		this.Default = default1;
		this.lenght = lenght;
		this.required = required;
		this.autoincrement = autoincrement;
		this.fk = fk;
		this.pk = pk;
	}
	public String getDefault() {
		return Default;
	}
	public void setDefault(String default1) {
		Default = default1;
	}
	public String getLenght() {
		return lenght;
	}
	public void setLenght(String lenght) {
		this.lenght = lenght;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public boolean isAutoincrement() {
		return autoincrement;
	}
	public void setAutoincrement(boolean autoincrement) {
		this.autoincrement = autoincrement;
	}
	public boolean isFk() {
		return fk;
	}
	public void setFk(boolean fk) {
		this.fk = fk;
	}
	public boolean isPk() {
		return pk;
	}
	public void setPk(boolean pk) {
		this.pk = pk;
	}
	private boolean autoincrement;
	private boolean fk;
	private boolean pk;
	
	
	public Column(String name, String type) {
		this.name = name;
		this.type = type;
	}
	public Column() {

	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
