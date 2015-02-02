package org.canato.billing.manager;

import java.util.List;

import org.canato.billing.bean.Item;
import org.canato.billing.bean.Receipt;

/**
 * Interface for the discount manager for getting the discount
 * amounts of the current discount strategies.
 * 
 * @author matteo
 *
 */
public interface ReceiptManager {

	/**
	 * Returns the receipt/bill calculated for the order items passed.
	 * 
	 * @param items
	 * @return
	 */
	Receipt calculate(List<Item> items);

}
