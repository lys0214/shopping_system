package com.tentact.shopping.entity;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true,chain = true)
@Data
public class Account {
	private String accountId;
	private String accountName;
	private String accountPwd;
	private String accountRealName;
	private String accountBirthday;
	private String accountPhone;
	private int roleId;
	private Role role;
	private Level level;
	private String updateAuthor;
	private String createAuthor;
	
	public Level getLevel() {
		return level;
	}
	public Account(String accountId, String accountName, String accountPwd, String accountRealName,
			String accountBirthday, String accountPhone, Role role) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountPwd = accountPwd;
		this.accountRealName = accountRealName;
		this.accountBirthday = accountBirthday;
		this.accountPhone = accountPhone;
		this.role = role;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}

	public String getAccountRealName() {
		return accountRealName;
	}

	public void setAccountRealName(String accountRealName) {
		this.accountRealName = accountRealName;
	}

	public String getAccountBirthday() {
		return accountBirthday;
	}

	public void setAccountBirthday(String accountBirthday) {
		this.accountBirthday = accountBirthday;
	}

	public String getAccountPhone() {
		return accountPhone;
	}

	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getUpdateAuthor() {
		return updateAuthor;
	}

	public void setUpdateAuthor(String updateAuthor) {
		this.updateAuthor = updateAuthor;
	}

	public String getCreateAuthor() {
		return createAuthor;
	}

	public void setCreateAuthor(String createAuthor) {
		this.createAuthor = createAuthor;
	}

	public Account(String accountId, String accountName, String accountPwd, String accountRealName,
				   String accountBirthday, String accountPhone) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountPwd = accountPwd;
		this.accountRealName = accountRealName;
		this.accountBirthday = accountBirthday;
		this.accountPhone = accountPhone;
	}
	public Account() {
		super();
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountName=" + accountName + ", accountPwd=" + accountPwd
				+ ", accountRealName=" + accountRealName + ", accountBirthday=" + accountBirthday + ", accountPhone="
				+ accountPhone + ", role=" + role + ", level=" + level + "]";
	}
	public Account(String accountId, String accountName, String accountPwd, String accountRealName,
			String accountBirthday, String accountPhone, Role role, Level level) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountPwd = accountPwd;
		this.accountRealName = accountRealName;
		this.accountBirthday = accountBirthday;
		this.accountPhone = accountPhone;
		this.role = role;
		this.level = level;
	}



}
