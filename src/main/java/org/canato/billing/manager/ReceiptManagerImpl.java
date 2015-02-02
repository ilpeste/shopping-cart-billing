package org.canato.billing.manager;

import java.util.ArrayList;
import java.util.List;

import org.canato.billing.bean.Item;
import org.canato.billing.bean.Receipt;
import org.canato.billing.bean.ReceiptItem;
import org.canato.billing.discount.service.StrategyService;
import org.canato.billing.discount.strategy.DiscountStrategy;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * It calls the {@link StrategyService} injected, for loading 
 * the defined {@link DiscountStrategy}. Then it offer some
 * useful methods for calculating the discount on the item/receipt
 * passed.
 * 
 * TODO: is Singleton annotation really needed??
 * 
 * @author matteo
 *
 */
@Singleton
public class ReceiptManagerImpl implements ReceiptManager {
	
	private StrategyService discountStrategyService;
	
	@Inject
	public void setDiscountStrategyService(StrategyService discountStrategyService) {
		this.discountStrategyService = discountStrategyService;
	}
	
	@Override
	public Receipt calculate(List<Item> items) {
		List<ReceiptItem> receiptItems = new ArrayList<ReceiptItem>();
		
 		// store the discount amount for each item by calling the discount manager
		for (Item item : items) {
			Double itemDiscountAmount = getDiscountAmountFor(item);
			Double discount = itemDiscountAmount * item.getQuantity();
//			Double discount = BillUtil.round(itemDiscountAmount * item.getQuantity());
			receiptItems.add(new ReceiptItem(item, discount));
		}
		
		// store the discount amount for the receipt
		Double receiptDiscountAmount = getDiscountAmountFor(new Receipt(receiptItems));
//		Double receiptDiscountAmount = BillUtil.round(getDiscountAmountFor(new Receipt(receiptItems)));
		Receipt receipt = new Receipt(receiptItems, receiptDiscountAmount);
		return receipt; 
	}
	
	private Double getDiscountAmountFor(Item item) {
		List<DiscountStrategy<Item>> list = discountStrategyService.getItemStrategies();
		if (list.isEmpty()) {
			return new Double(0);
		}
		
		return calculateDiscountAmount(item, list);
	}
	
	private Double getDiscountAmountFor(Receipt receipt) {
		List<DiscountStrategy<Receipt>> list = discountStrategyService.getReceiptStrategies();		
		if (list.isEmpty()) {
			return new Double(0);
		}
		
		return calculateDiscountAmount(receipt, list);
	}
	
	private <T> Double calculateDiscountAmount(T element, List<DiscountStrategy<T>> strategies) {
		Double discountAmount = new Double(0);
		for (DiscountStrategy<T> discountStrategy : strategies) {
			if (discountStrategy.isApplicableOn(element)) {
				discountAmount += discountStrategy.getAmount(element);
			}
		}
		return discountAmount;
	}
	
}