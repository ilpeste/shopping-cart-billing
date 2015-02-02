package org.canato.billing.test.discount.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		assertFalse(applicable);
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
		
		assertTrue(applicable);
		assertEquals(3.33d, amount, Constant.DELTA_PRECISION);
	}

	@Test
	public void shouldNotBeApplicableWhenReceiptGrossTotalIsSmall() {
		boolean applicable = strategy.isApplicableOn(ReceiptHelper.getSmallAmountReceipt());
		Double amount = strategy.getAmount(ReceiptHelper.getSmallAmountReceipt());
		
		assertFalse(applicable);
		assertEquals(0d, amount, Constant.DELTA_PRECISION);
	}
	
}