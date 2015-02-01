package org.canato.billing.test.discount.strategy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.canato.billing.bean.Item;
import org.canato.billing.discount.strategy.ItemTypeDiscountStrategy;
import org.canato.billing.test.ItemHelper;
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
		strategy = new ItemTypeDiscountStrategy(Item.Type.GROCERY, 1f);
	}

	@Test
	public void shouldBeApplicableWhenItemIsGrocery() {
		boolean applicable = strategy.isApplicableOn(ItemHelper.getPasta());
		assertThat(applicable, is(true));
	}

	@Test
	public void shouldNotBeApplicableCustomerItemIsNotGrocery() {
		boolean applicable = strategy.isApplicableOn(ItemHelper.getBook1());
		assertThat(applicable, is(false));
	}
	
}