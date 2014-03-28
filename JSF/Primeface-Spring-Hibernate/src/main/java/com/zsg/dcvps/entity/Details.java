package com.zsg.dcvps.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "details")
@NamedQueries({
	@NamedQuery(name = "Details.findByDetailId", 
				query = " SELECT d FROM Details d WHERE d.detailId = ? "),
	@NamedQuery(name = "Details.findDetails", 
				query = " SELECT d FROM Details d WHERE d.checkId = ? "),
	@NamedQuery(name = "Details.totalNoOfDetails",
				query = " SELECT count(d) FROM Details d WHERE d.checkId = ?")
})
public class Details implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "detailId", unique = true, nullable = false)
	private Integer detailId;
	
	@Column(name = "checkId", nullable = false, length = 100)
	private Integer checkId;
	
	@Column(name = "menuItemNo", nullable = false, length = 100)
	private String menuItemNo;
	
	@Column(name = "quantity", nullable = false, length = 100)
	private String quantity;
	
	@Column(name = "isUpdated", nullable = false, length = 100)
	private boolean isUpdated;
	
	@Column(name = "chkOpenTime", nullable = false, length = 100)
	private Date chkOpenTime;
	
	@Column(name = "remark", nullable = false, length = 100)
	private String remark;
	
	@Column(name = "isCondiment", nullable = false, length = 100)
	private String isCondiment;
	
	@Column(name = "timestamp", nullable = false, length = 100)
	private String timestamp;
	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
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


	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Date getChkOpenTime() {
		return chkOpenTime;
	}

	public void setChkOpenTime(Date chkOpenTime) {
		this.chkOpenTime = chkOpenTime;
	}

	public boolean getIsUpdated() {
		return isUpdated;
	}

	public void setIsUpdated(boolean isUpdated) {
		this.isUpdated = isUpdated;
	}
	
	

}
