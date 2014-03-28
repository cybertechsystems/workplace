package com.zsg.dcvps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@NamedQueries({
	@NamedQuery(name = "Role.findByRoleId", 
				query = " FROM Role r WHERE r.roleId = ? "),
})
public class Role {
	@Id
	@Column(name = "roleId",nullable = false)
	private Integer roleId;
	
	@Column(name = "roleName")
	private String roleName;


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
