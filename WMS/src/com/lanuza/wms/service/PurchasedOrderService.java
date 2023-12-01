package com.lanuza.wms.service;

import java.util.List;

import javax.swing.JTable;

import com.lanuza.wms.model.PurchasedOrder;

public interface PurchasedOrderService {
	
	List<PurchasedOrder> getAllPurchasedOrders();
	
	PurchasedOrder getPurchasedOrderById(int orderId);
	
	void addPurchasedOrder( PurchasedOrder purchasedOrder);
	
	void updatePurchasedOrder( PurchasedOrder purchasedOrder);
	
	void deletePurchasedOrder(int orderId);
	
	void tableLoad(JTable table);

}
