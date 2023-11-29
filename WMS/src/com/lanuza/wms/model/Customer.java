package com.lanuza.wms.model;

public class Customer {
	private int customerId;
	private String Name,Brgy,Municipality,Province,PhoneNo;
	
	public Customer() {
		super();
	}
	public Customer(int customerId, String name, String brgy, String municipality, String province, String phoneNo) {
		super();
		this.customerId = customerId;
		Name = name;
		Brgy = brgy;
		Municipality = municipality;
		Province = province;
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
	public String getBrgy() {
		return Brgy;
	}
	public void setBrgy(String brgy) {
		Brgy = brgy;
	}
	public String getMunicipality() {
		return Municipality;
	}
	public void setMunicipality(String municipality) {
		Municipality = municipality;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getPhoneNo() {
		return PhoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", Name=" + Name + ", Brgy=" + Brgy + ", Municipality="
				+ Municipality + ", Province=" + Province + ", PhoneNo=" + PhoneNo + "]";
	}
	
	
}
