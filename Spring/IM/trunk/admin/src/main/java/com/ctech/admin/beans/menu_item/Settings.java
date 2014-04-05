package com.ctech.admin.beans.menu_item;

import java.io.Serializable;

public class Settings implements Serializable {
	private static final long serialVersionUID = 1L;
 
	private String settingID;
	private String settingName;
	private String settingDescription;


	public String getsettingID() {
		return settingID;
	}

	public void setsettingID(String settingID) {
		this.settingID = settingID;
	}

	public String getsettingName() {
		return settingName;
	}

	public void setsettingName(String settingName) {
		this.settingName = settingName;
	}

	public String getsettingDescription() {
		return settingDescription;
	}

	public void setsettingDescription(String settingDescription) {
		this.settingDescription = settingDescription;
	}
}
