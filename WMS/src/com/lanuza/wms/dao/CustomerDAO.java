package com.lanuza.wms.dao;

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.model.Customer;

//product crud
public interface CustomerDAO {
	
	Customer getCustomerById(int customerId);
	
	List<Customer> getAllCustomer();
    
	void addCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomer(int customerId);
	
	void tableLoad(JTable table);

}
