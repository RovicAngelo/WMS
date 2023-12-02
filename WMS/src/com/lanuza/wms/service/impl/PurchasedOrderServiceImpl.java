package com.lanuza.wms.service.impl;

import java.util.List;
import java.util.Map;

import javax.swing.JTable;

import com.lanuza.wms.dao.PurchasedOrderDAO;
import com.lanuza.wms.model.PurchasedOrder;
import com.lanuza.wms.service.PurchasedOrderService;

public class PurchasedOrderServiceImpl implements PurchasedOrderService{
	 private final PurchasedOrderDAO purchaseOrderDAO;

	    public PurchasedOrderServiceImpl(PurchasedOrderDAO purchaseOrderDAO) {
	        this.purchaseOrderDAO = purchaseOrderDAO;
	    }

		@Override
	    public List<PurchasedOrder> getAllPurchasedOrders() {
	        return purchaseOrderDAO.getAllPurchasedOrders();
	    }

	    @Override
	    public PurchasedOrder getPurchasedOrderById(int orderId) {
	        return purchaseOrderDAO.getPurchasedOrderById(orderId);
	    }

	    @Override
	    public void addPurchasedOrder(PurchasedOrder purchaseOrder) {
	    	purchaseOrderDAO.addPurchasedOrder(purchaseOrder);
	    }

	    @Override
	    public void updatePurchasedOrder(PurchasedOrder purchaseOrder) {
	    	purchaseOrderDAO.updatePurchasedOrder(purchaseOrder);
	    }

	    @Override
	    public void deletePurchasedOrder(int receivingId) {
	    	purchaseOrderDAO.deletePurchasedOrder(receivingId);
	    }

		@Override
		public void tableLoad(JTable table) {
			purchaseOrderDAO.tableLoad(table);
		}
		
		public double getSumOfTotal() {
	        return purchaseOrderDAO.getSumOfTotal();
	    }

		@Override
		public Map<String, Object> getAvailabilityAndPriceByProductDescription(String selectedProduct) {
			// TODO Auto-generated method stub
			return purchaseOrderDAO.getAvailabilityAndPriceByProductDescription(selectedProduct);
		}

		@Override
		public void reflectPurchaseOrderToStock() {
			purchaseOrderDAO.reflectPurchaseOrderToStock();
			
		}

}
