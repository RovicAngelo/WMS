package com.lanuza.wms.service;

import java.util.List;
import java.util.Map;

import javax.swing.JTable;

import com.lanuza.wms.model.PurchasedOrder;

public interface PurchasedOrderService {
	
	List<PurchasedOrder> getAllPurchasedOrders();
	
	PurchasedOrder getPurchasedOrderById(int orderId);
	
	void addPurchasedOrder( PurchasedOrder purchasedOrder);
	
	void updatePurchasedOrder( PurchasedOrder purchasedOrder);
	
	void deletePurchasedOrder(int orderId);
	
	void tableLoad(JTable table);
	
	double getSumOfTotal();
	
	Map<String, Object> getAvailabilityAndPriceByProductDescription(String selectedProduct);
	
	void reflectPurchaseOrderToStock();
	
	List<String> getAllProductDescription();
	
	List<String> getAllCustomerName();
	
	List<Object[]> getSearchBy (String text);
}
