package com.ctech.admin.beans.food_order;

import java.io.Serializable;

public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;

	private String checkId;
	private String orderNum;	
	private String chkOpenTime;	
	private String ipadId;
	private String isOpen;
	private String isUpdated;
	private String remark; 
	
	public String getCheckId() {
        return checkId;
    }
	
	public String getOrderNum() {
        return orderNum;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }
    
    public String getchkOpenTime() {
        return chkOpenTime;
    }

    public void setchkOpenTime(String chkOpenTime) {
        this.chkOpenTime = chkOpenTime;
    }
    
    public String getipadId() {
        return ipadId;
    }

    public void setipadId(String ipadId) {
        this.ipadId = ipadId;
    }
    
    public String getisOpen() {
        return isOpen;
    }

    public void setisOpen(String isOpen) {
        this.isOpen = isOpen;
    }

	public String getIsUpdated() {
		return isUpdated;
	}

	public void setIsUpdated(String isUpdated) {
		this.isUpdated = isUpdated;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}	

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
}
