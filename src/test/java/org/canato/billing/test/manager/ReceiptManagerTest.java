package org.canato.billing.test.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.canato.billing.bean.Item;
import org.canato.billing.bean.Receipt;
import org.canato.billing.manager.ReceiptManager;
import org.canato.billing.manager.ReceiptManagerImpl;
import org.canato.billing.test.helper.Constant;
import org.canato.billing.test.helper.EmptyStrategyService;
import org.canato.billing.test.helper.ItemHelper;
import org.canato.billing.util.DiscountManagerInjector;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Unit test for {@link ReceiptManagerImpl}.
 * 
 */
public class ReceiptManagerTest {
	
	private ReceiptManager manager;
	
	@Before
	public void setUp() {
		Injector guiceInjector = Guice.createInjector(new DiscountManagerInjector());
		manager = guiceInjector.getInstance(ReceiptManager.class);
	}

	@Test
	public void shouldCalculateReceiptForInput1() {
		List<Item> items = new ArrayList<Item>();
		items.add(ItemHelper.getPasta());
		items.add(ItemHelper.getBook1());
		
		Receipt receipt = manager.calculate(items);
		
		assertNotNull(receipt);
		assertEquals(12.88d, receipt.getGrossTotal(), Constant.DELTA_PRECISION);
		assertEquals(0d, receipt.getDiscount(), Constant.DELTA_PRECISION);
		assertEquals(12.90d, receipt.getTotal(), Constant.DELTA_PRECISION);
		assertEquals(1.53d, receipt.getTotalDiscount(), Constant.DELTA_PRECISION);
	}
	
	@Test
	public void shouldCalculateReceiptForInput2() {
		List<Item> items = new ArrayList<Item>();
		items.add(ItemHelper.getCoffee());
		items.add(ItemHelper.getPasta());
		items.add(ItemHelper.getCake());
		
		Receipt receipt = manager.calculate(items);
		
		assertNotNull(receipt);
		assertEquals(9.29d, receipt.getGrossTotal(), Constant.DELTA_PRECISION);
		assertEquals(0d, receipt.getDiscount(), Constant.DELTA_PRECISION);
		assertEquals(9.3d, receipt.getTotal(), Constant.DELTA_PRECISION);
		assertEquals(0.56d, receipt.getTotalDiscount(), Constant.DELTA_PRECISION);
	}
	
	@Test
	public void shouldCalculateReceiptForInput3() {
		List<Item> items = new ArrayList<Item>();
		items.add(ItemHelper.getChocolate());
		items.add(ItemHelper.getWine());
		items.add(ItemHelper.getBook2());
		items.add(ItemHelper.getApple());
		
		Receipt receipt = manager.calculate(items);
		
		assertNotNull(receipt);
		assertEquals(47.24d, receipt.getGrossTotal(), Constant.DELTA_PRECISION);
		assertEquals(2.36d, receipt.getDiscount(), Constant.DELTA_PRECISION);
		assertEquals(44.9d, receipt.getTotal(), Constant.DELTA_PRECISION);
		assertEquals(4.17d, receipt.getTotalDiscount(), Constant.DELTA_PRECISION);
	}
	
	/**
	 * The following test don't use the manager injected but another one
	 */
	@Test
	public void shouldCalculateReceiptWithEmptyDiscountStrategiesList() {
		ReceiptManagerImpl managerForEmptyDiscountStrategies = new ReceiptManagerImpl();
		
		managerForEmptyDiscountStrategies.setDiscountStrategyService(new EmptyStrategyService());
		
		List<Item> items = new ArrayList<Item>();
		items.add(ItemHelper.getPasta());
		items.add(ItemHelper.getCoffee());
		items.add(ItemHelper.getBook1());
		
		Receipt receipt = managerForEmptyDiscountStrategies.calculate(items);
		
		assertNotNull(receipt);
		assertEquals(17.62d, receipt.getGrossTotal(), Constant.DELTA_PRECISION);
		assertEquals(0d, receipt.getDiscount(), Constant.DELTA_PRECISION);
		assertEquals(17.60d, receipt.getTotal(), Constant.DELTA_PRECISION);
		assertEquals(0d, receipt.getTotalDiscount(), Constant.DELTA_PRECISION);
	}
	
}