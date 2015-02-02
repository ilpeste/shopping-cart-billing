package org.canato.billing.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.canato.billing.util.CommonUtils;

/**
 * The bill/receipt bean.
 * 
 * Note that once the receipt is created, this MUST BE immutable! 
 * 
 * This is due to the fact that, in a real world, the receipt should be
 * passed to a payment service/processor. If the discounts are retrived
 * at running time, they can change from the source and the customer 
 * could be asked to pay something different from the total of the bill.
 *   
 * This is why the {@link List<ReceiptItem>}, the discount are unmodifiable.
 * 
 * It implements {@link Serializable} in order to make easier the transformation
 * into a JSON, XML or other serializable data type.
 * 
 * @author matteo
 *
 */
public class Receipt implements Serializable {

	private static final long serialVersionUID = -8193978199380070835L;

	protected List<ReceiptItem> items; 	// the receipt items
	protected Double discount; 			// the discount applied to the entire receipt
	
	public Receipt(List<ReceiptItem> items, Double discount) {
		super();
		this.items = Collections.unmodifiableList(items);
		this.discount = discount;
	}
	
	public Receipt(List<ReceiptItem> items) {
		this(items, new Double(0));
	}
	
	public List<ReceiptItem> getItems() {
		return items;
	}

	/**
	 * The gross total of the receipts
	 * 
	 * @return
	 */
	public Double getGrossTotal() {
		Double sum = new Double(0);
		for (ReceiptItem item : items) {
			sum += item.getItem().getGrossTotal() - item.getDiscount();
		}
		return CommonUtils.round(sum);
	}

	public Double getTotal() {
//		return BillUtil.round(getGrossTotal() - discount, 5);
		return getGrossTotal() - discount;
	}

	public Double getDiscount() {
		return discount;
	}

	/**
	 * Returns the total discount applied in the
	 * 
	 * @return
	 */
	public Double getTotalDiscount() {
		Double sum = new Double(0);
		for (ReceiptItem item : items) {
			sum += item.getDiscount();
		}
		return CommonUtils.round(sum + discount);
	}
	
	public String print() {
		return String.format("Gross Total = %.2f\nDiscount = %.2f\nTOTAL = %.2f\n(total discounts = %.2f)", getGrossTotal(), discount, getTotal(), getTotalDiscount());
	}

}