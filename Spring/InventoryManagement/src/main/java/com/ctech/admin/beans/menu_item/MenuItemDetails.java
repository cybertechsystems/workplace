package com.ctech.admin.beans.menu_item;

import java.io.Serializable;

public class MenuItemDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private String sID;
	private String maincourseId;
	private String sidedishId;
	private String sauceId;
	private String advertisementId;

	public String getsID() {
		return sID;
	}

	public void setsID(String sID) {
		this.sID = sID;
	}

	public String getmaincourseId() {
		return maincourseId;
	}

	public void setmaincourseId(String maincourseId) {
		this.maincourseId = maincourseId;
	}

	public String getsidedishId() {
		return sidedishId;
	}

	public void setsidedishId(String sidedishId) {
		this.sidedishId = sidedishId;
	}

	public String getsauceId() {
		return sauceId;
	}

	public void setsauceId(String sauceId) {
		this.sauceId = sauceId;
	}

	public String getadvertisementId() {
		return advertisementId;
	}

	public void setadvertisementId(String advertisementId) {
		this.advertisementId = advertisementId;
	}
}
