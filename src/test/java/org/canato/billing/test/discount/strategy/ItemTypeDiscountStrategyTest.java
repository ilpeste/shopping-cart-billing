package org.canato.billing.test.discount.strategy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

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
		
		assertThat(applicable, is(true));
		assertEquals(0.43d, amount, Constant.DELTA_PRECISION);
	}

	@Test
	public void shouldNotBeApplicableCustomerItemIsNotGrocery() {
		boolean applicable = strategy.isApplicableOn(ItemHelper.getBook1());
		Double amount = strategy.getAmount(ItemHelper.getBook1());
		
		assertThat(applicable, is(false));
		assertEquals(0d, amount, Constant.DELTA_PRECISION);
	}
	
	@Test
	public void shouldNotBeApplicableOnNull() {
		boolean applicable = strategy.isApplicableOn(null);
		assertThat(applicable, is(false));
	}
	
	@Test
	public void amountShouldBeZeroIfItemIsNull() {
		Double amount = strategy.getAmount(null);
		assertEquals(0, amount, Constant.DELTA_PRECISION);
	}
	
}