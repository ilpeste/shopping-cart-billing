package org.canato.billing.test.discount.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.canato.billing.bean.Item;
import org.canato.billing.discount.strategy.ItemTypeDiscountStrategy;
import org.canato.billing.test.helper.Constant;
import org.canato.billing.test.helper.ItemHelper;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link ItemTypeDiscountStrategy}.
 * 
 */
public class ItemTypeDiscountStrategyTest {

	private ItemTypeDiscountStrategy strategy;

	@Before
	public void setUp() {
		strategy = new ItemTypeDiscountStrategy(Item.Type.GROCERY, 10f);
	}

	@Test
	public void shouldBeApplicableWhenItemIsGrocery() {
		boolean applicable = strategy.isApplicableOn(ItemHelper.getPasta());
		Double amount = strategy.getAmount(ItemHelper.getPasta());
		
		assertTrue(applicable);
		assertEquals(0.43d, amount, Constant.DELTA_PRECISION);
	}

	@Test
	public void shouldNotBeApplicableCustomerItemIsNotGrocery() {
		boolean applicable = strategy.isApplicableOn(ItemHelper.getBook1());
		Double amount = strategy.getAmount(ItemHelper.getBook1());
		
		assertFalse(applicable);
		assertEquals(0d, amount, Constant.DELTA_PRECISION);
	}
	
	@Test
	public void shouldNotBeApplicableOnNull() {
		boolean applicable = strategy.isApplicableOn(null);
		assertFalse(applicable);
	}
	
	@Test
	public void amountShouldBeZeroIfItemIsNull() {
		Double amount = strategy.getAmount(null);
		assertEquals(0, amount, Constant.DELTA_PRECISION);
	}
	
}