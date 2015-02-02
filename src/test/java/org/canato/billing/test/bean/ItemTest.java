package org.canato.billing.test.bean;

import static org.junit.Assert.assertEquals;

import org.canato.billing.bean.Item;
import org.canato.billing.test.helper.Constant;
import org.junit.Test;

/**
 * Unit test for {@link Item}.
 * 
 */
public class ItemTest {

	@Test
	public void shouldReturnGrossTotal() {
		Item item = new Item("Test", 3d, Item.Type.OTHER, 10);
		assertEquals(30d, item.getGrossTotal(), Constant.DELTA_PRECISION);
	}
	
	@Test
	public void shouldBeZeroIfQuantityIsZero() {
		Item item = new Item("Test", 3d, Item.Type.OTHER, 0);
		assertEquals(0, item.getGrossTotal(), Constant.DELTA_PRECISION);
	}
	
	@Test
	public void shouldBeZeroIfPriceIsZero() {
		Item item = new Item("Test", 0d, Item.Type.OTHER, 10);
		assertEquals(0, item.getGrossTotal(), Constant.DELTA_PRECISION);
	}
	
}