package org.canato.billing.test.bean;

import static org.junit.Assert.assertEquals;

import org.canato.billing.bean.Receipt;
import org.canato.billing.test.helper.Constant;
import org.canato.billing.test.helper.ReceiptHelper;
import org.junit.Test;

/**
 * Unit test for {@link Receipt}.
 * 
 */
public class ReceiptTest {

	@Test
	public void grossTotalOnEmptyReceiptShouldBeZero() {
		Receipt receipt = ReceiptHelper.getEmptyReceipt();
		assertEquals(0d, receipt.getGrossTotal(), Constant.DELTA_PRECISION);
	}
	
	@Test
	public void discountOnEmptyReceiptShouldBeZero() {
		Receipt receipt = ReceiptHelper.getEmptyReceipt();
		assertEquals(0d, receipt.getDiscount(), Constant.DELTA_PRECISION);
	}
	
	@Test
	public void totalOnEmptyReceiptShouldBeZero() {
		Receipt receipt = ReceiptHelper.getEmptyReceipt();
		assertEquals(0d, receipt.getTotal(), Constant.DELTA_PRECISION);
	}
	
	@Test
	public void totalDiscountOnEmptyReceiptShouldBeZero() {
		Receipt receipt = ReceiptHelper.getEmptyReceipt();
		assertEquals(0d, receipt.getTotalDiscount(), Constant.DELTA_PRECISION);
	}

	@Test
	public void shouldReturnGrossTotal() {
		Receipt receiptWithDiscount = ReceiptHelper.getBigAmountReceiptWithDiscount();
		assertEquals(66.6d, receiptWithDiscount.getGrossTotal(), Constant.DELTA_PRECISION);
		
		Receipt receiptNoDiscount = ReceiptHelper.getBigAmountReceipt();
		assertEquals(66.6d, receiptNoDiscount.getGrossTotal(), Constant.DELTA_PRECISION);
	}
	
	@Test
	public void shouldReturnDiscount() {
		Receipt receiptWithDiscount = ReceiptHelper.getBigAmountReceiptWithDiscount();
		assertEquals(5d, receiptWithDiscount.getDiscount(), Constant.DELTA_PRECISION);
		
		Receipt receiptNoDiscount = ReceiptHelper.getBigAmountReceipt();
		assertEquals(0d, receiptNoDiscount.getDiscount(), Constant.DELTA_PRECISION);
	}
	
	@Test
	public void shouldReturnTotal() {
		Receipt receiptWithDiscount = ReceiptHelper.getBigAmountReceiptWithDiscount();
		assertEquals(61.6d, receiptWithDiscount.getTotal(), Constant.DELTA_PRECISION);
		
		Receipt receiptNoDiscount = ReceiptHelper.getBigAmountReceipt();
		assertEquals(66.6d, receiptNoDiscount.getTotal(), Constant.DELTA_PRECISION);
	}
	
	@Test
	public void shouldReturnTotalDiscount() {
		Receipt receiptWithDiscount = ReceiptHelper.getBigAmountReceiptWithDiscount();
		assertEquals(5d, receiptWithDiscount.getTotalDiscount(), Constant.DELTA_PRECISION);
		
		Receipt receiptNoDiscount = ReceiptHelper.getBigAmountReceipt();
		assertEquals(0d, receiptNoDiscount.getTotalDiscount(), Constant.DELTA_PRECISION);
	}
	
}