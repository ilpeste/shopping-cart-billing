package org.canato.billing.discount.service;

import java.util.ArrayList;
import java.util.List;

import org.canato.billing.bean.Item;
import org.canato.billing.bean.Receipt;
import org.canato.billing.discount.strategy.DiscountStrategy;
import org.canato.billing.discount.strategy.ItemTypeDiscountStrategy;
import org.canato.billing.discount.strategy.VolumeDiscountStrategy;

import com.google.inject.Singleton;

/**
 * Static implementation of the {@link StrategyService}.
 * 
 * This is a mock! All the values are hard-coded!
 * 
 * @author matteo
 *
 */
@Singleton
public class StaticStrategyService implements StrategyService {

	@Override
	public List<DiscountStrategy<Item>> getItemStrategies() {
		List<DiscountStrategy<Item>> list = new ArrayList<DiscountStrategy<Item>>();
		list.add(new ItemTypeDiscountStrategy(Item.Type.GROCERY, 7.5f));
		list.add(new ItemTypeDiscountStrategy(Item.Type.BOOK, 12.0f));
		
		return list;
	}
	
	@Override
	public List<DiscountStrategy<Receipt>> getReceiptStrategies() {
		List<DiscountStrategy<Receipt>> list = new ArrayList<DiscountStrategy<Receipt>>();
		list.add(new VolumeDiscountStrategy(40.0d, 5.0f));
		
		return list;
	}
	
}
