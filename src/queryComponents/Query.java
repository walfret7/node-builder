package queryComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Query {

	private Table table;

	public String selectAllQuery() {

		String select = "SELECT * FROM " + table.getName();

		if (table.getTableJoins() != null && table.getTableJoins().size() > 0) {
			for (Table tableJoin : table.getTableJoins()) {
				select += " JOIN " + tableJoin + " ON " + table .getName()+ ".id_" + tableJoin + " = " + tableJoin + ".id ";
			}
		}

		return select;
	}

	public String selectByIdQuery() {

		String select = "SELECT * FROM " + table.getName();

		if (table.getTableJoins() != null && table.getTableJoins().size() > 0) {
			for (Table tableJoin : table.getTableJoins()) {
				select += " JOIN " + tableJoin + " ON " + table.getName() + ".id_" + tableJoin + " = " + tableJoin + ".id ";
			}
		}

		select += " WHERE " + table.getName() + ".id=?";

		return select;
	}

	public String insertQuery() {

		String insert = "INSERT INTO " + table.getName() + "(";
		String placeholder = "";

		List<Column> columns = new ArrayList<Column>(table.getColumns());

		for (int i = 0; i < columns.size(); i++) {

			insert += columns.get(i).getName();
			placeholder += "?";

			if (i != columns.size() - 1) {
				insert += ",";
				placeholder += ",";
			}

		}

		insert += ")values(" + placeholder + ")";

		return insert;
	}

	public String updateQuery() {

		String update = "UPDATE " + table.getName() + " SET ";
		List<Column> columns = new ArrayList<Column>(table.getColumns());

		for (int i = 0; i < columns.size(); i++) {

			update += columns.get(i).getName() + "=?";
			update += (i != columns.size() - 1) ? "," : "";

		}

		update += " WHERE id=?";

		return update;
	}

	public String deleteQuery() {
		String delete = "DELETE FROM " + table.getName() + " WHERE id=?";
		return delete;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Query(Table table) {
		this.table = table;
	}

	public String dropQuery() {
		return "DROP TABLE IF EXISTS " + table.getName();
	}

	public String createQuery() {

		Set<Table> joins = table.getTableJoins();
		Set<Column> columns = table.getColumns();
		String create = "CREATE TABLE " + table.getName() + "(";
		for (Column column : columns) {
			create += column.getName() + " " + column.getType() + ",";
		}
		if (table.getTableJoins() != null && table.getTableJoins().size() > 0) {
			for (Table tableJoin : joins) {
				create += "FOREIGN KEY (" + tableJoin.getName() + "_id) REFERENCES " + tableJoin.getName() + "(id),";
			}
		}
		create += "PRIMARY KEY(id))";

		return create;
	}
}
