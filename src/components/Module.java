package components;

import java.util.List;

import queryComponents.Table;

public class Module {

	private String name;
	private boolean selectAll;
	private boolean selectById;
	private boolean create;
	private boolean update;
	private boolean delete;
	private Table table;
	private Table tableChild;	
	private List<Atributo> atributos;


	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Table getTableChild() {
		return tableChild;
	}

	public void setTableChild(Table tableChild) {
		this.tableChild = tableChild;
	}

	public Module(String name, boolean selectAll, boolean selectById, boolean create, boolean update, boolean delete,
			Table table, List<Atributo> atributos) {
		this.name = name;
		this.selectAll = selectAll;
		this.selectById = selectById;
		this.create = create;
		this.update = update;
		this.delete = delete;
		this.atributos = atributos;
		this.table = table;

	}

	public Module(String name, boolean selectAll, boolean selectById, boolean create, boolean update, boolean delete,
			Table table, Table tableChild, List<Atributo> atributos) {
		this.name = name;
		this.selectAll = selectAll;
		this.selectById = selectById;
		this.create = create;
		this.update = update;
		this.delete = delete;
		this.atributos = atributos;
		this.table = table;
		this.tableChild = tableChild;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelectAll() {
		return selectAll;
	}

	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}

	public boolean isSelectById() {
		return selectById;
	}

	public void setSelectById(boolean selectById) {
		this.selectById = selectById;
	}

	public boolean isCreate() {
		return create;
	}

	public void setCreate(boolean create) {
		this.create = create;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}
}
