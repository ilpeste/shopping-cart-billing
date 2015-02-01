package org.canato.billing.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.canato.billing.discount.manager.DiscountStrategyManager;

import com.google.inject.Inject;

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

	protected List<ReceiptItem> items; 	// the items
	protected Double grossTotal; 		// the gross total of the receipt
	protected Double total; 			// the total of the receipt
	protected Double discount; 			// the discount applied to the receipt

	private Receipt(List<ReceiptItem> items) {
		super();
		// make the items' list unmodifiable
		this.items = Collections.unmodifiableList(items);
		
		// calculate the grossTotal
		Double sum = new Double(0);
		for (ReceiptItem item : items) {
			sum += item.getItemGrossTotal() - item.getDiscount();
		}
		this.grossTotal = sum;
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
		return grossTotal;
	}

	public Double getTotal() {
		return total;
	}

	public Double getDiscount() {
		return discount;
	}

	// setter for total and discount are private
	private void setTotal(Double total) {
		this.total = total;
	}
	
	private void setDiscount(Double discount) {
		this.discount = discount;
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
		return sum + discount;
	}

	/**
	 * Receipt builder.
	 *
	 */
	public static class Builder {
		private List<ReceiptItem> items = new ArrayList<ReceiptItem>();
		private DiscountStrategyManager discountMngr;

		@Inject
		public void setDiscountMngr(DiscountStrategyManager discountMngr) {
			this.discountMngr = discountMngr;
		}

		public Builder() {
			super();
		}

		public Builder add(Item item, int qty) {
			items.add(new ReceiptItem(item, qty, null));
			return this;
		}
		
		/**
		 * Returns the {@link Receipt}. The discount strategies are evaluated only
		 * at this point.
		 * 
		 * @return
		 */
		public Receipt build() {
			
			// store the discount amount for each item by calling the discount manager
			for (ReceiptItem receiptItem : items) {
				Double itemDiscountAmount = discountMngr.getDiscountAmount(receiptItem.getItem());
//				Double discount = BillUtil.round(itemDiscountAmount * qty);
				Double discount = itemDiscountAmount * receiptItem.getQuantity();
				receiptItem.setDiscount(discount);
			}
			
			// store the discount amount for the receipt
			Receipt receipt = new Receipt(items);
			Double receiptDiscountAmount = discountMngr.getDiscountAmount(receipt);
//			Double receiptDiscountAmount = BillUtil.round(discountMngr.getDiscountAmount(receipt));

			receipt.setDiscount(receiptDiscountAmount);
			receipt.setTotal(receipt.getGrossTotal() - receiptDiscountAmount);
			//receipt.setTotal(BillUtil.round(receipt.getGrossTotal() - receiptDiscountAmount, 5));
			
			return receipt;
		}
	}
	
	@Override
	public String toString() {
		return String.format("Gross Total = %.2f\nDiscount = %.2f\nTOTAL = %.2f\n(total discounts = %.3f)", grossTotal, discount, total, getTotalDiscount());
	}

}
