package org.canato.billing.discount.manager;

import org.canato.billing.bean.Item;
import org.canato.billing.bean.Receipt;

/**
 * Interface for the discount manager for getting the discount
 * amounts of the current discount strategies.
 * 
 * @author matteo
 *
 */
public interface DiscountManager {

	/**
	 * Get the discount amount for the item with the current discount strategies.
	 * 
	 * @param item
	 * @return
	 */
	Double getDiscountAmount(Item item);
	
	/**
	 * Get the discount amount for the receipt with the current discount strategies.
	 * 
	 * @param item
	 * @return
	 */
	Double getDiscountAmount(Receipt receipt);

}
