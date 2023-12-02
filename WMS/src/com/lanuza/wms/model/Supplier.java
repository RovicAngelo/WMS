package com.lanuza.wms.model;

public class Supplier {
	
	private int supplierId;
	private String name,phoneNo;
	public Supplier() {
		super();
	}
	public Supplier(int supplierId, String name, String phoneNo) {
		super();
		this.supplierId = supplierId;
		this.name = name;
		this.phoneNo = phoneNo;
	}
	public Supplier(String name, String phoneNo) {
		super();
		this.name = name;
		this.phoneNo = phoneNo;
	}
	public Supplier(String name) {
		super();
		this.name = name;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
}
