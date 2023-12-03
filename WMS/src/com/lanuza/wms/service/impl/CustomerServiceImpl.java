package com.lanuza.wms.service.impl;

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.dao.CustomerDAO;
import com.lanuza.wms.model.Customer;
import com.lanuza.wms.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

	@Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomer();
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return customerDAO.getCustomerById(customerId);
    }

    @Override
    public void addCustomer(Customer customer) {
    	customerDAO.addCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
    	customerDAO.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
    	customerDAO.deleteCustomer(customerId);
    }

	@Override
	public void tableLoad(JTable table) {
		customerDAO.tableLoad(table);		
	}

}
