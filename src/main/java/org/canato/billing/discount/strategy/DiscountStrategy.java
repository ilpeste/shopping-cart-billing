package org.canato.billing.discount.strategy;

/**
 * Generic interface for defining discount strategies.
 * 
 * @author matteo
 *
 * @param <T>
 */
public interface DiscountStrategy<T> {
	
	/**
	 * Check if the discount strategy is applicable on the passed element.
	 * 
	 * @param element
	 * @return
	 */
	boolean isApplicableOn(T element);
	
	/**
	 * Returns the discount amount for the passed element.
	 * 
	 * @param element
	 * @return
	 */
	Double getAmount(T element);
}
