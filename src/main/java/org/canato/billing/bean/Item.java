package org.canato.billing.bean;

import java.io.Serializable;

/**
 * The item bean: it represents a single line of an order.
 * 
 * It implements {@link Serializable} in order to make easier
 * the transformation into a JSON, XML or other serializable data type.
 * 
 * @author matteo
 *
 */
public class Item implements Serializable {
	
	private static final long serialVersionUID = -2629384241406382686L;
	
	/**
	 * Possible item types.
	 * TODO: make types configurable externally?
	 *
	 */
	public enum Type {
		GROCERY,
		DRINKS,
		BOOK,
		OTHER;
	}
	
	protected String name;
	protected Double price;
	protected Type type;
	protected int quantity;
	
	public Item(String name, Double price, Type type, int quantity) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Double getGrossTotal() {
		return price * quantity;
	}
	
}