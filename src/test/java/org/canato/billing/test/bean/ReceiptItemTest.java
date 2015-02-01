package org.canato.billing.test.bean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.canato.billing.bean.Item;
import org.canato.billing.bean.ReceiptItem;
import org.junit.Test;


/**
 * Unit test for {@link ReceiptItem}.
 * 
 */
public class ReceiptItemTest {

	@Test
	public void shouldReturnGrossTotal() {
		ReceiptItem item = new ReceiptItem(new Item("Test", 3d, Item.Type.OTHER), 10, 0d);
		assertThat(item.getItemGrossTotal(), is(30d));
	}
	
}