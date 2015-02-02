package org.canato.billing.test.bean;

import static org.junit.Assert.assertEquals;

import org.canato.billing.bean.Item;
import org.canato.billing.bean.ReceiptItem;
import org.canato.billing.test.helper.Constant;
import org.junit.Test;

/**
 * Unit test for {@link ReceiptItem}.
 * 
 */
public class ReceiptItemTest {

	@Test
	public void shouldReturnFinalPriceTotal() {
		ReceiptItem item = new ReceiptItem(new Item("Test", 3d, Item.Type.OTHER, 10), 3d);
		assertEquals(27, item.getFinalPrice(), Constant.DELTA_PRECISION);
	}
	
	@Test
	public void shouldReturnFinalPriceTotalZeroIfQuantityIsZero() {
		ReceiptItem item = new ReceiptItem(new Item("Test", 3d, Item.Type.OTHER, 0), 3d);
		assertEquals(0, item.getFinalPrice(), Constant.DELTA_PRECISION);
	}
	
	@Test
	public void shouldReturnFinalPriceTotalZeroIfPriceIsZero() {
		ReceiptItem item = new ReceiptItem(new Item("Test", 0d, Item.Type.OTHER, 10), 3d);
		assertEquals(0, item.getFinalPrice(), Constant.DELTA_PRECISION);
	}
	
	@Test
	public void shouldReturnFinalPriceTotalEqualToGrossTotal() {
		ReceiptItem item = new ReceiptItem(new Item("Test", 3d, Item.Type.OTHER, 10), 0d);
		assertEquals(30d, item.getFinalPrice(), Constant.DELTA_PRECISION);
	}
	
}