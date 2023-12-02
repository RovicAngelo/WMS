package com.lanuza.wms.dao;

import java.util.List;
import java.util.Map;

import javax.swing.JTable;

import com.lanuza.wms.model.PurchasedOrder;

public interface PurchasedOrderDAO {

    /**
     * Adds a new purchased order to the database.
     *
     * @param orderId       The order ID.
     * @param customerName  The name of the customer.
     * @param productName   The name of the product.
     * @param productPrice  The price of the product.
     * @param quantity      The quantity of the product.
     * @param total         The total cost of the order.
     * @param order_date     The date of the order.
     */
    void addPurchasedOrder(PurchasedOrder purchasedOrder);

    /**
     * Retrieves a list of all purchased orders from the database.
     *
     * @return A list of purchased orders.
     */
    List<PurchasedOrder> getAllPurchasedOrders();

    /**
     * Retrieves a specific purchased order by its order ID.
     *
     * @param orderId The order ID.
     * @return The purchased order.
     */
    PurchasedOrder getPurchasedOrderById(int orderId);

    /**
     * Updates the details of a purchased order in the database.
     *
     * @param orderId       The order ID.
     * @param customerName  The new name of the customer.
     * @param productName   The new name of the product.
     * @param productPrice  The new price of the product.
     * @param quantity      The new quantity of the product.
     * @param total         The new total cost of the order.
     * @param orderDate     The new date of the order.
     */
    void updatePurchasedOrder(PurchasedOrder purchasedOrder);

    /**
     * Deletes a purchased order from the database based on its order ID.
     *
     * @param orderId The order ID.
     */
    void deletePurchasedOrder(int orderId);
    
	void tableLoad(JTable table);
	
	double getSumOfTotal();
	
	Map<String, Object> getAvailabilityAndPriceByProductDescription(String selectedProduct);
	
	void reflectPurchaseOrderToStock();
}

