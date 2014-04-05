package com.ctech.admin.beans;

public class SpecialCat {
	private String id;
	private String category;

	public SpecialCat() {
	}

	public SpecialCat(String id, String category) {
		setId(id);
		setCategory(category);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
