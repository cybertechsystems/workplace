package com.ctech.admin.beans;

import java.io.Serializable;

public class Data implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String value;
	private String primaryKey;
	private String columnName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPrimaryKey() {
		if (primaryKey == null && id != null) {
			primaryKey = id.replaceFirst("^(.+)_.+$", "$1");
		}
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getColumnName() {
		if (columnName == null && id != null) {
			columnName = id.replaceFirst("^.+_(.+)$", "$1");
		}
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

}
