package components;

public class Atributo {

	private String label;
	private String name;
	private String type;
	private String config;
	private boolean required;
	private boolean showForm;
	private boolean showTable;

	public Atributo(String label, String name, String type, String config,  boolean required,
			boolean form, boolean table) {

		this.label = label;
		this.name = name;
		this.type = type;
		this.config = config;
		this.required = required;
		this.showForm = form;
		this.showTable = table;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public String getConfig() {
		return config;
	}

	public void setConfig(String datasource) {
		this.config = datasource;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setForm(boolean form) {
		this.showForm = form;
	}

	public boolean isShowTable() {
		return showTable;
	}

	public void setTable(boolean table) {
		this.showTable = table;
	}
}
