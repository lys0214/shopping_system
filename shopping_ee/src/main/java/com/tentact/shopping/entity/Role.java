package com.tentact.shopping.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true,chain = true)
public class Role {
	private int roleId;
	private String roleName;
	private String roleMemo;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleMemo() {
		return roleMemo;
	}
	public void setRoleMemo(String roleMemo) {
		this.roleMemo = roleMemo;
	}
	public Role(int roleId, String roleName, String roleMemo) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleMemo = roleMemo;
	}
	public Role() {
		super();
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleMemo=" + roleMemo + "]";
	}


	
}
