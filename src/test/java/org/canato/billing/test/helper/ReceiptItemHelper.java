package org.canato.billing.test.helper;

import org.canato.billing.bean.Item;
import org.canato.billing.bean.ReceiptItem;

/**
 * Helper class for running the tests.
 * 
 * @author matteo
 *
 */
public final class ReceiptItemHelper {
	
	private ReceiptItemHelper(){
	}
	
	public static ReceiptItem getPasta() {
		return new ReceiptItem(new Item("Pasta 1kg", 4.29d, Item.Type.GROCERY, 5), 0d);
	}
	
	public static ReceiptItem getBook() {
		return new ReceiptItem(new Item("Book", 15.05d, Item.Type.BOOK, 3), 0d);
	}
	
}