package com.tentact.shopping.service;

import com.tentact.shopping.dao.AccountDAO;
import com.tentact.shopping.entity.Account;

import java.util.List;

// 业务逻辑层
public class AccountService {
	AccountDAO accountDAO = new AccountDAO();

	public Account login(Account account) {
		return accountDAO.login(account);
	}

	public List<Account> selectAll(){
		return accountDAO.selectAll();
	}

	public List<Account> selectAllByRoleId(int roleId) {
		return accountDAO.selectAllByRoleId(roleId);
	}

	public Account checkAccountId(String accountId) {
		return accountDAO.checkAccountId(accountId);
	}

	public boolean update(Account account) {
		return accountDAO.update(account);
	}

	public boolean checkAccountName(String accountName) {
		return accountDAO.checkAccountName(accountName);
	}

	public boolean add(Account account,String manageName) {
		return accountDAO.add(account, manageName);
	}
}
