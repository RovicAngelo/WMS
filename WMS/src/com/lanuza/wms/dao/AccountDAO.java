package com.lanuza.wms.dao;

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.model.Account;

public interface AccountDAO {
	Account getAccountById(int accountId);
	
	List<Account> getAllAccount();
    
	void addAccount(Account account);

	void updateAccount(Account account);

	void deleteAccount(int accountId);
	
	void tableLoad(JTable table);
	
	Account getAccountByUsernameAndPassword(String username, String password);	
	
	int getTotalItems();
	
	Account getAccountByName(String name);	
	
	List<Account>getAllAccountNameExcept(String currentName);	
	
}
