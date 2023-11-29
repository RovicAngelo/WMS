package com.lanuza.wms.model;

public class Supplier {
	
	private int supplierId;
	private String name,brgy,municipality,province,phoneNo;
		
	public Supplier(int supplierId, String name, String brgy, String municipality, String province, String phoneNo) {
		super();
		this.supplierId = supplierId;
		this.name = name;
		this.brgy = brgy;
		this.municipality = municipality;
		this.province = province;
		this.phoneNo = phoneNo;
	}
	
	public Supplier(String name, String brgy, String municipality, String province, String phoneNo) {
		super();
		this.name = name;
		this.brgy = brgy;
		this.municipality = municipality;
		this.province = province;
		this.phoneNo = phoneNo;
	}
			
	public Supplier() {
		super();
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
	public String getBrgy() {
		return brgy;
	}
	public void setBrgy(String brgy) {
		this.brgy = brgy;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", name=" + name + ", brgy=" + brgy + ", municipality="
				+ municipality + ", province=" + province + ", phoneNo=" + phoneNo + "]";
	}
	
}
