package queryComponents;

import java.util.HashSet;
import java.util.Set;

public class Table {

	private String name;
	private Set<Column> columns;
	private Set<Table> tableJoins;

	public Table(String name, Set<Column> columns) {
		this.name = name;
		this.columns = columns;
	}

	public Table(String name, Set<Column> columns, Set<Table> tableJoins) {
		this.name = name;
		this.columns = columns;
		this.tableJoins = tableJoins;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Column> getColumns() {
		return columns;
	}

	public void setColumns(Set<Column> columns) {
		this.columns = columns;
	}

	public Set<Table> getTableJoins() {
		return tableJoins;
	}

	public void setTableJoins(Set<Table> tableJoins) {
		this.tableJoins = tableJoins;
	}

	public void addColumn(Column col) {

		if (columns == null) {
			columns = new HashSet<Column>();			
		}
		
		if (!columNameExists(col)) {
				columns.add(col);
		}

	}

	private boolean columNameExists(Column col) {

		for (Column column : columns) {
			if (column.getName().equals(col.getName())) {
				return true;
			}
		}
		return false;
	}

	public void addTableJoin(Table tableJoin) {

		if (tableJoins == null) {
			tableJoins = new HashSet<Table>();			
		}
		
		if (!tableNameExists(tableJoin)) {
				tableJoins.add(tableJoin);
		}

	}

	private boolean tableNameExists(Table tableJoin) {

		for (Table table : tableJoins) {
			if (table.getName().equals(tableJoin.getName())) {
				return true;
			}
		}
		return false;
	}

}
