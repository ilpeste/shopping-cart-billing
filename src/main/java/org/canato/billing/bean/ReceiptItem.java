package org.canato.billing.bean;

import java.io.Serializable;

/**
 * The item of the bill/receipt bean.
 * It implements {@link Serializable} in order to make easier
 * the transformation into a JSON, XML or other serializable data type.
 * 
 * @author matteo
 *
 */
public class ReceiptItem implements Serializable {
	
	private static final long serialVersionUID = -8161109734816482230L;
	
	private Item item;				// the item
	private int quantity;			// the quantity for each item
	private Double discount;		// the discount for each item

	public ReceiptItem(Item item, int quantity, Double discount) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.discount = discount;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	public Double getItemGrossTotal() {
		return item.getPrice() * quantity;
	}
	
	@Override
	public String toString() {
		return String.format("%16s%16d%16.2f%16.2f%16.2f", item.getName(), quantity, item.getPrice(), discount, getItemGrossTotal()-discount);
	}
	
}
