package org.canato.billing.test.discount.strategy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.canato.billing.discount.strategy.VolumeDiscountStrategy;
import org.canato.billing.test.helper.ReceiptHelper;
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
		strategy = new VolumeDiscountStrategy(30d, 5f);
	}

	@Test
	public void shouldBeApplicableWhenReceiptGrossTotalIsBig() {
		boolean applicable = strategy.isApplicableOn(ReceiptHelper.getBigAmountReceipt());
		assertThat(applicable, is(true));
	}

	@Test
	public void shouldNotBeApplicableWhenReceiptGrossTotalIsSmall() {
		boolean applicable = strategy.isApplicableOn(ReceiptHelper.getSmallAmountReceipt());
		assertThat(applicable, is(false));
	}
	
}