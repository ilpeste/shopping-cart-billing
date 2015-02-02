package org.canato.billing.test.helper;

import java.util.ArrayList;
import java.util.List;

import org.canato.billing.bean.Receipt;
import org.canato.billing.bean.ReceiptItem;

/**
 * Helper class for running the tests.
 * 
 * @author matteo
 *
 */
public final class ReceiptHelper {
	
	private ReceiptHelper(){
	}
	
	public static Receipt getSmallAmountReceipt() {
		List<ReceiptItem> items = new ArrayList<ReceiptItem>();
		items.add(ReceiptItemHelper.getPasta());
		
		return new Receipt(items);
	}
	
	public static Receipt getBigAmountReceipt() {
		List<ReceiptItem> items = new ArrayList<ReceiptItem>();
		items.add(ReceiptItemHelper.getPasta());
		items.add(ReceiptItemHelper.getBook());
		
		return new Receipt(items);
	}
	
}