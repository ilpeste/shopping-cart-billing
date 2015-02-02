package org.canato.billing.util;

import org.canato.billing.discount.service.StaticStrategyService;
import org.canato.billing.discount.service.StrategyService;
import org.canato.billing.manager.ReceiptManager;
import org.canato.billing.manager.ReceiptManagerImpl;

import com.google.inject.AbstractModule;

/**
 * Guice dependency injector configuration.
 * 
 * @author matteo
 *
 */
public class DiscountManagerInjector extends AbstractModule {
	
	@Override
	protected void configure() {
		// just change here the class responsible for getting the discount strategies to apply
		bind(StrategyService.class).to(StaticStrategyService.class);
//		bind(StrategyService.class).to(XMLStrategyService.class);
		
		// bind to the receipt manager
		bind(ReceiptManager.class).to(ReceiptManagerImpl.class);
	}
	
}
