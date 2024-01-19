package com.lanuza.wms.service.impl;

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.dao.AccountDAO;
import com.lanuza.wms.model.Account;
import com.lanuza.wms.service.AccountService;

public class AccountServiceImpl implements AccountService{
	
	   private final AccountDAO accountDAO;

	    public AccountServiceImpl(AccountDAO accountDAO) {
	        this.accountDAO = accountDAO;
	    }

		@Override
	    public List<Account> getAllAccount() {
	        return accountDAO.getAllAccount();
	    }

	    @Override
	    public Account getAccountById(int accountId) {
	        return accountDAO.getAccountById(accountId);
	    }

	    @Override
	    public void addAccount(Account account) {
	    	accountDAO.addAccount(account);
	    }

	    @Override
	    public void updateAccount(Account account) {
	    	accountDAO.updateAccount(account);
	    }

	    @Override
	    public void deleteAccount(int accountId) {
	    	accountDAO.deleteAccount(accountId);
	    }

		@Override
		public void tableLoad(JTable table) {
			accountDAO.tableLoad(table);
			
		}
		
		@Override
		public Account getAccountByUsernameAndPassword(String username, String password) {
	        return accountDAO.getAccountByUsernameAndPassword(username, password);
	    }		

		@Override
		public int getTotalItems() {
			return accountDAO.getTotalItems();
		}

}
