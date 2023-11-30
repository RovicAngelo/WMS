package com.lanuza.wms.model;

public class Account {
	
	private String username, password,name,type;
	private int accountId;
		
	public Account() {
		super();
	}
	
	public Account( int accountId, String username, String password, String name, String type) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.type = type;
		this.accountId = accountId;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", name=" + name + ", type=" + type
				+ ", accountId=" + accountId + "]";
	}
	
	
	
}
