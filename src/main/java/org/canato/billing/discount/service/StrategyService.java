package org.canato.billing.discount.service;

import java.util.List;

import org.canato.billing.bean.Item;
import org.canato.billing.bean.Receipt;
import org.canato.billing.discount.strategy.DiscountStrategy;

/**
 * Interface provides the base contract for the discount strategy services.
 * 
 * This interface may have different implementation based on the source of the
 * data: Static, XML, DB etc...
 * 
 * In this demo application I'll implement the Static and (maybe) the XML one.
 * 
 * @author matteo
 *
 */
public interface StrategyService {
	
	/**
	 * Returns the items' discount strategies from the source (Static, XML, DB etc.).
	 * 
	 * @return
	 */
	List<DiscountStrategy<Item>> 	getItemStrategies();
	
	/**
	 * Returns the receipts' discount strategies from the source (Static, XML, DB etc.).
	 * 
	 * @return
	 */	
	List<DiscountStrategy<Receipt>> getReceiptStrategies();	

}
