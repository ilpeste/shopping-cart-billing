package org.canato.billing.bean;

import java.io.Serializable;

import org.canato.billing.util.CommonUtils;

/**
 * The item of the bill/receipt bean: is represented as an {@link Item} 
 * with a discount amount applied on.
 * 
 * It implements {@link Serializable} in order to make easier
 * the transformation into a JSON, XML or other serializable data type.
 * 
 * @author matteo
 *
 */
public class ReceiptItem implements Serializable {
	
	private static final long serialVersionUID = -8161109734816482230L;
	
	private Item item;				// the item
	private Double discount;		// the discount for each item

	public ReceiptItem(Item item, Double discount) {
		super();
		this.item = item;
		this.discount = discount;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Double getDiscount() {
		return discount;
	}
	
	public Double getFinalPrice() {
		if (item.getGrossTotal() <= 0) {
			return new Double(0);
		}
		return CommonUtils.round(item.getGrossTotal() - discount); 
	}
	
	public String print() {
		return String.format("%16s%16d%16.2f%16.2f%16.2f", item.getName(), item.getQuantity(), item.getPrice(), discount, getFinalPrice());
	}
	
}