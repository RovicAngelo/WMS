package com.lanuza.wms.dao;

import java.util.List;
import java.util.Map;

import javax.swing.JTable;

import com.lanuza.wms.model.ReceivingEntry;

public interface ReceivingEntryDAO {

    /**
     * Adds a new receiving entry to the database.
     *
     * @param receivingId    The receiving ID.
     * @param productName    The name of the product received.
     * @param productPrice   The price of the received product.
     * @param quantity       The quantity of the received product.
     * @param total          The total cost of the receiving entry.
     * @param supplierName   The name of the supplier.
     * @param receivedDate   The date of the receiving entry.
     */
    void addReceivingEntry(ReceivingEntry receivingEntry);

    /**
     * Retrieves a list of all receiving entries from the database.
     *
     * @return A list of receiving entries.
     */
    List<ReceivingEntry> getAllReceivingEntries();

    /**
     * Retrieves a specific receiving entry by its receiving ID.
     *
     * @param receivingId The receiving ID.
     * @return The receiving entry.
     */
    ReceivingEntry getReceivingEntryById(int receivingId);

    /**
     * Updates the details of a receiving entry in the database.
     *
     * @param receivingId   The receiving ID.
     * @param productName   The new name of the received product.
     * @param productPrice  The new price of the received product.
     * @param quantity      The new quantity of the received product.
     * @param total         The new total cost of the receiving entry.
     * @param supplierName  The new name of the supplier.
     * @param receivedDate  The new date of the receiving entry.
     */
    void updateReceivingEntry(ReceivingEntry receivingEntry);

    /**
     * Deletes a receiving entry from the database based on its receiving ID.
     *
     * @param receivingId The receiving ID.
     */
    void deleteReceivingEntry(int receivingId);
    
	void tableLoad(JTable table);
	
	double getSumOfTotal();
	
	void reflectReceivingEntryToStock();
	
	Map<String, Object> getAvailabilityAndPriceByProductDescription(String selectedProduct);
	
	List<String> getAllProductDescriptions();
	
	List<Object[]> getSearchBy (String text);
}

