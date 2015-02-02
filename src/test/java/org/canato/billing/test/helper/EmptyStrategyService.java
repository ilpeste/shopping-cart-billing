package org.canato.billing.test.helper;

import java.util.ArrayList;
import java.util.List;

import org.canato.billing.bean.Item;
import org.canato.billing.bean.Receipt;
import org.canato.billing.discount.service.StrategyService;
import org.canato.billing.discount.strategy.DiscountStrategy;

import com.google.inject.Singleton;

/**
 * Mock service which simulates empty lists of discount strategies.
 * 
 * @author matteo
 *
 */
@Singleton
public class EmptyStrategyService implements StrategyService {

	@Override
	public List<DiscountStrategy<Item>> getItemStrategies() {
		return new ArrayList<DiscountStrategy<Item>>();
	}
	
	@Override
	public List<DiscountStrategy<Receipt>> getReceiptStrategies() {
		return new ArrayList<DiscountStrategy<Receipt>>();
	}
	
}
