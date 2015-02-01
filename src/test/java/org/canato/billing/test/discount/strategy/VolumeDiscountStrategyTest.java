package org.canato.billing.test.discount.strategy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.canato.billing.bean.Item;
import org.canato.billing.discount.strategy.ItemTypeDiscountStrategy;
import org.canato.billing.discount.strategy.VolumeDiscountStrategy;
import org.canato.billing.test.ItemHelper;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link VolumeDiscountStrategy}.
 * 
 */
public class VolumeDiscountStrategyTest {

	private VolumeDiscountStrategy strategy;

	@Before
	public void setUp() {
		strategy = new VolumeDiscountStrategy(40d, 5f);
	}

//	@Test
//	public void shouldBeApplicableWhenGrossIsLo() {
//		boolean applicable = strategy.isApplicableOn(ItemHelper.getPasta());
//		assertThat(applicable, is(true));
//	}
//
//	@Test
//	public void shouldNotBeApplicableCustomerItemIsNotGrocery() {
//		boolean applicable = strategy.isApplicableOn(ItemHelper.getBook1());
//		assertThat(applicable, is(false));
//	}
	
}