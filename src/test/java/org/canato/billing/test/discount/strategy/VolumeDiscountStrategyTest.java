package org.canato.billing.test.discount.strategy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import org.canato.billing.discount.strategy.VolumeDiscountStrategy;
import org.canato.billing.test.helper.Constant;
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
	public void shouldNotBeApplicableOnNull() {
		boolean applicable = strategy.isApplicableOn(null);
		assertThat(applicable, is(false));
	}
	
	@Test
	public void amountShouldBeZeroIfItemIsNull() {
		Double amount = strategy.getAmount(null);
		assertEquals(0, amount, Constant.DELTA_PRECISION);
	}

	@Test
	public void shouldBeApplicableWhenReceiptGrossTotalIsBig() {
		boolean applicable = strategy.isApplicableOn(ReceiptHelper.getBigAmountReceipt());
		Double amount = strategy.getAmount(ReceiptHelper.getBigAmountReceipt());
		
		assertThat(applicable, is(true));
		assertEquals(3.33d, amount, Constant.DELTA_PRECISION);
	}

	@Test
	public void shouldNotBeApplicableWhenReceiptGrossTotalIsSmall() {
		boolean applicable = strategy.isApplicableOn(ReceiptHelper.getSmallAmountReceipt());
		Double amount = strategy.getAmount(ReceiptHelper.getSmallAmountReceipt());
		
		assertThat(applicable, is(false));
		assertEquals(0d, amount, Constant.DELTA_PRECISION);
	}
	
}