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
		assertEquals(8.50d, CommonUtils.round(8.5d, 2), Constant.DELTA_PRECISION);
		
		assertEquals(8.00d, CommonUtils.round(8d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.01d, CommonUtils.round(8.01d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.00d, CommonUtils.round(8.001d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.00d, CommonUtils.round(8.000001d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.00d, CommonUtils.round(8.001d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.00d, CommonUtils.round(8.002d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.00d, CommonUtils.round(8.003d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.00d, CommonUtils.round(8.004d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.00d, CommonUtils.round(8.0044d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.00d, CommonUtils.round(8.0046d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.01d, CommonUtils.round(8.005d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.01d, CommonUtils.round(8.0054d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.01d, CommonUtils.round(8.006d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.01d, CommonUtils.round(8.007d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.01d, CommonUtils.round(8.008d, 2), Constant.DELTA_PRECISION);
		assertEquals(8.01d, CommonUtils.round(8.009d, 2), Constant.DELTA_PRECISION);
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