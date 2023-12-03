package com.lanuza.wms.model;

public class Account {
	
	private String username, password,name,role;
	private int accountId;
		
	public Account() {
		super();
	}
	
	public Account(int accountId,String name, String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = role;
		this.accountId = accountId;
	}
	//for update queries
	public Account(String name, String username, String password, String role,int accountId) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = role;
		this.accountId = accountId;
	}
	
	public Account(String name, String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", name=" + name + ", role=" + role
				+ ", accountId=" + accountId + "]";
	}	
}
