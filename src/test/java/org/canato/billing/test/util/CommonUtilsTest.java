package org.canato.billing.test.util;

import static org.junit.Assert.assertEquals;

import org.canato.billing.test.helper.Constant;
import org.canato.billing.util.CommonUtils;
import org.junit.Test;

/**
 * Unit test for {@link CommonUtils}.
 * 
 */
public class CommonUtilsTest {
	
	@Test
	public void roundToScaleTwo() {
		assertEquals(8.51d, CommonUtils.round(8.509d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.50d, CommonUtils.round(8.5d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.91d, CommonUtils.round(8.9056d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.90d, CommonUtils.round(8.9044d, 2), Constant.DELTA_PRECISION);
	}
	
	@Test
	public void roundToScaleZero() {
		assertEquals(9d, CommonUtils.round(8.509d, 0), Constant.DELTA_PRECISION);
		assertEquals(8d, CommonUtils.round(8.409d, 0), Constant.DELTA_PRECISION);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void roundToScaleNegative() {
		assertEquals(8.51d, CommonUtils.round(8.509d, -1), Constant.DELTA_PRECISION);
	}
	
}