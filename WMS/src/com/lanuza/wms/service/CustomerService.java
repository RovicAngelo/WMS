package com.lanuza.wms.service;

//business rules,validation here

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.model.Customer;


public interface CustomerService {
	List<Customer> getAllCustomers();
	
	Customer getCustomerById(int customerId);
	
	void addCustomer(Customer customer);
	
	void updateCustomer(Customer customer);
	
	void deleteCustomer(int customerId);
	
	void tableLoad(JTable table);
}
