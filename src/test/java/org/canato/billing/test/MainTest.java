package org.canato.billing.test;

import org.canato.billing.bean.Receipt;
import org.canato.billing.bean.ReceiptItem;
import org.canato.billing.util.DiscountManagerInjector;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Main test for the exercise input.
 * 
 * @author matteo
 *
 */
public class MainTest {
	
	private Receipt.Builder builderReceipt;
	
	@Before
	public void setUp() {
		Injector guiceInjector = Guice.createInjector(new DiscountManagerInjector());
		builderReceipt = guiceInjector.getInstance(Receipt.Builder.class);
	}

	@Test
	public void testInput1() {
		System.out.println("-------- input 1 --------");

		builderReceipt.add(ItemHelper.getPasta(), 1);
		builderReceipt.add(ItemHelper.getBook1(), 1);
		
		Receipt receipt = builderReceipt.build();
		
		for (ReceiptItem item : receipt.getItems()) {
			System.out.println(item);
		}
		System.out.println("----------------");
		System.out.println(receipt);
	}
	
	@Test
	public void testInput2() {
		System.out.println("-------- input 2 --------");
		
		builderReceipt.add(ItemHelper.getCoffee(), 1);
		builderReceipt.add(ItemHelper.getPasta(), 1);
		builderReceipt.add(ItemHelper.getCake(), 1);
		
		Receipt receipt = builderReceipt.build();
		
		for (ReceiptItem item : receipt.getItems()) {
			System.out.println(item);
		}
		System.out.println("----------------");
		System.out.println(receipt);
	}
	
	@Test
	public void testInput3() {
		System.out.println("-------- input 3 --------");
		
		builderReceipt.add(ItemHelper.getChocolate(), 10);
		builderReceipt.add(ItemHelper.getWine(), 1);
		builderReceipt.add(ItemHelper.getBook2(), 1);
		builderReceipt.add(ItemHelper.getApple(), 5);
		
		Receipt receipt = builderReceipt.build();
		
		for (ReceiptItem item : receipt.getItems()) {
			System.out.println(item);
		}
		System.out.println("----------------");
		System.out.println(receipt);
	}
	
}
