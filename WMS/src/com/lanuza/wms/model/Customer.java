package com.lanuza.wms.model;

public class Customer {
	private int customerId;
	private String Name,PhoneNo;
	
	public Customer() {
		super();
	}
	public Customer(int customerId, String name, String phoneNo) {
		super();
		this.customerId = customerId;
		Name = name;
		PhoneNo = phoneNo;
	}
	
	public Customer( String name, String phoneNo) {
		super();
		Name = name;
		PhoneNo = phoneNo;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhoneNo() {
		return PhoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", Name=" + Name + ", PhoneNo=" + PhoneNo + "]";
	}
	
	
}
