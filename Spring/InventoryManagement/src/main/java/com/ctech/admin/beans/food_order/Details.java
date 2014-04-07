package com.ctech.admin.beans.food_order;

import java.io.Serializable;

public class Details implements Serializable {
	private static final long serialVersionUID = 1L;

	private String detailId;
	private String checkId;
	private String menuItemNo;
	private String quantity;
	private String isUpdated;
	private String chkOpenTime;
	private String remark;
	private String isCondiment;
	private String timestamp;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getMenuItemNo() {
		return menuItemNo;
	}

	public void setMenuItemNo(String menuItemNo) {
		this.menuItemNo = menuItemNo;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getIsUpdated() {
		return isUpdated;
	}

	public void setIsUpdated(String isUpdated) {
		this.isUpdated = isUpdated;
	}
	public String getChkOpenTime() {
        return chkOpenTime;
    }
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsCondiment() {
		return isCondiment;
	}

	public void setIsCondiment(String isCondiment) {
		this.isCondiment = isCondiment;
	}

	public void setChkOpenTime(String chkOpenTime) {
		this.chkOpenTime = chkOpenTime;
	}
	
	

}
