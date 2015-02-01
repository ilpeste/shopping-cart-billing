package org.canato.billing.util;

import org.canato.billing.discount.manager.DiscountManager;
import org.canato.billing.discount.manager.DiscountStrategyManager;
import org.canato.billing.discount.service.StaticStrategyService;
import org.canato.billing.discount.service.StrategyService;

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
		//bind(StrategyService.class).to(XMLStrategyService.class);
		
		// bind to the discount strategy manager
		bind(DiscountManager.class).to(DiscountStrategyManager.class);
	}
	
}
