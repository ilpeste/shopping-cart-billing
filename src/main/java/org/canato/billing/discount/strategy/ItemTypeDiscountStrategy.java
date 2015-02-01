package org.canato.billing.discount.strategy;

import org.canato.billing.bean.Item;

/**
 * Discount strategy based on the type of the item passed.
 * 
 * @author matteo
 *
 */
public class ItemTypeDiscountStrategy implements DiscountStrategy<Item> {

	private Item.Type type;
	private float amount;

	public ItemTypeDiscountStrategy(Item.Type type, float amount) {
		super();
		this.type = type;
		this.amount = amount;
	}
	
	public boolean isApplicableOn(Item item) {
		if (item == null) {
			return false;
		}
		return item.getType() == type;
	}

	public Double getAmount(Item item) {
		if (item == null) {
			return new Double(0);
		}
		return item.getPrice() * (amount / 100.0f);
	}
	
}