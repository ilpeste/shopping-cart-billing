package org.canato.billing.discount.manager;

import java.util.List;

import org.canato.billing.bean.Item;
import org.canato.billing.bean.Receipt;
import org.canato.billing.discount.service.StrategyService;
import org.canato.billing.discount.strategy.DiscountStrategy;

import com.google.inject.Inject;

/**
 * It calls the {@link StrategyService} injected, for loading 
 * the defined {@link DiscountStrategy}. Then it offer some
 * useful methods for calculating the discount on the item/receipt
 * passed.
 * 
 * TODO: add Singleton annotation??
 * 
 * @author matteo
 *
 */
public class DiscountStrategyManager implements DiscountManager {
	
	private StrategyService discountStrategyService;
	
	@Inject
	public void setDiscountStrategyService(StrategyService discountStrategyService) {
		this.discountStrategyService = discountStrategyService;
	}
	
	public Double getDiscountAmount(Item item) {
		List<DiscountStrategy<Item>> list = discountStrategyService.getItemStrategies();
		if (list.isEmpty()) {
			return new Double(0);
		}
		
		return calculateAmount(item, list);
	}
	
	public Double getDiscountAmount(Receipt receipt) {
		List<DiscountStrategy<Receipt>> list = discountStrategyService.getReceiptStrategies();		
		if (list.isEmpty()) {
			return new Double(0);
		}
		
		return calculateAmount(receipt, list);
	}
	
	private <T> Double calculateAmount(T element, List<DiscountStrategy<T>> strategies) {
		Double discountAmount = new Double(0);
		for (DiscountStrategy<T> discountStrategy : strategies) {
			if (discountStrategy.isApplicableOn(element)) {
				discountAmount += discountStrategy.getAmount(element);
			}
		}
		return discountAmount;
	}
	
}
