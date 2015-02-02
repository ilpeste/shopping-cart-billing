package org.canato.billing.discount.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang3.StringUtils;
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
public class XMLStrategyService implements StrategyService {
	
	private static final String CONFIG_FILE_NAME 		= "discount-strategies.xml";
	private static final String NODE_ITEM_STRATEGIES 	= "item-strategies.strategy";
	private static final String NODE_RECEIPT_STRATEGIES = "receipt-strategies.strategy";
	
	private enum Code {
		ITEM_TYPE,
		VOLUME;
	}
	
	private final List<DiscountStrategy<Item>> itemStrategies;
	private final List<DiscountStrategy<Receipt>> receiptStrategies;
	
	public XMLStrategyService() {
		this.itemStrategies = new ArrayList<DiscountStrategy<Item>>();
		this.receiptStrategies = new ArrayList<DiscountStrategy<Receipt>>();		
		try {
			XMLConfiguration config = new XMLConfiguration(CONFIG_FILE_NAME);
			
			// load item discount strategies from file
			List<HierarchicalConfiguration> xmlItemStrategies = config.configurationsAt(NODE_ITEM_STRATEGIES);
			for (HierarchicalConfiguration strategyConfig : xmlItemStrategies) {
			    itemStrategies.add(createItemStrategy(strategyConfig));
			}
			
			// load receipt discount strategies from file
			List<HierarchicalConfiguration> xmlReceiptStrategies = config.configurationsAt(NODE_RECEIPT_STRATEGIES);
			for (HierarchicalConfiguration strategyConfig : xmlReceiptStrategies) {
				receiptStrategies.add(createReceiptStrategy(strategyConfig));
			}
		} catch (ConfigurationException e) {
			System.out.println("Configuration file 'discount-strategies.xml' not found in path or invalid. No discount strategies will be applied. ");
			itemStrategies.clear();
			receiptStrategies.clear();
		}
	}
	
	@Override
	public List<DiscountStrategy<Item>> getItemStrategies() {
		return itemStrategies;
	}
	
	@Override
	public List<DiscountStrategy<Receipt>> getReceiptStrategies() {
		return receiptStrategies;
	}
	
	/**
	 * Converter from the xml configuration file into the item discount strategy.
	 * 
	 * @param strategyConfig
	 * @return
	 * @throws ConfigurationException
	 */
	private DiscountStrategy<Item> createItemStrategy(HierarchicalConfiguration strategyConfig) throws ConfigurationException {
		String code = strategyConfig.getString("code");
		
		if (StringUtils.equalsIgnoreCase(code, Code.ITEM_TYPE.name())) {
			String type = strategyConfig.getString("type");
			float amount = strategyConfig.getFloat("amount");
			return new ItemTypeDiscountStrategy(Item.Type.valueOf(type), amount);
		} else {
			throw new ConfigurationException("invalid strategy code");
		}
	}
	
	/**
	 * Converter from the xml configuration file into the receipt discount strategy.
	 * 
	 * @param strategyConfig
	 * @return
	 * @throws ConfigurationException
	 */
	private DiscountStrategy<Receipt> createReceiptStrategy(HierarchicalConfiguration strategyConfig) throws ConfigurationException {
		String code = strategyConfig.getString("code");
		
		if (StringUtils.equalsIgnoreCase(code, Code.VOLUME.name())) {
			Double threshold = strategyConfig.getDouble("threshold");
			float amount = strategyConfig.getFloat("amount");
			return new VolumeDiscountStrategy(threshold, amount);
		} else {
			throw new ConfigurationException("invalid strategy code");
		}
	}
	
}