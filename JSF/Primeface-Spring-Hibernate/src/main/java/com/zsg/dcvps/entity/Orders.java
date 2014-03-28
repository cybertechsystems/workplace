package com.zsg.dcvps.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "checks")
@NamedQueries({
	@NamedQuery(name = "Orders.findByOrderscheckId", 
				query = " SELECT o FROM Orders o WHERE o.checkId = ? "),
	
	@NamedQuery(name = "Orders.findAllOrders", 
				query = " SELECT o FROM Orders o "),
	@NamedQuery(name = "Orders.totalNoOfOrders",
				query = " SELECT count(o) FROM Orders o")
})
public class Orders implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "checkId", unique = true, nullable = false)
	private Integer checkId;
	
	@Column(name = "orderNum", nullable = false, length = 100)
	private String orderNum;
	
	@Column(name = "chkOpenTime", nullable = false, length = 100)
	private Date chkOpenTime;	
	
	@Column(name = "ipadId", nullable = false, length = 100)
	private String ipadId;
	
	@Column(name = "isOpen", nullable = false, length = 100)
	private boolean isOpen;
	
	@Column(name = "isUpdated", nullable = false, length = 100)
	private boolean isUpdated;
	
	@Column(name = "remark", nullable = false, length = 100)
	private String remark; 
	
	@Transient
	private Details	details;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "orderdetails", joinColumns = {
			@JoinColumn(name = "checkId" , referencedColumnName = "checkId"),
			
		},inverseJoinColumns = {
			@JoinColumn(name = "detailId" , referencedColumnName = "detailId"),
	})
	
	private List<Details> detailList;
	
	public String getOrderNum() {
        return orderNum;
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

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public List<Details> getDetailList() {
		
		return detailList;
	}

	public void setDetailList(List<Details> detailList) {
		this.detailList = detailList;
	}

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}


	public Date getChkOpenTime() {
		return chkOpenTime;
	}

	public void setChkOpenTime(Date chkOpenTime) {
		this.chkOpenTime = chkOpenTime;
	}


	public boolean getIsOpen() {
		return isOpen;
	}


	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}


	public boolean getIsUpdated() {
		return isUpdated;
	}


	public void setIsUpdated(boolean isUpdated) {
		this.isUpdated = isUpdated;
	}


	public String getIpadId() {
		return ipadId;
	}


	public void setIpadId(String ipadId) {
		this.ipadId = ipadId;
	}
}
