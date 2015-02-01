package org.canato.billing.test;

import org.canato.billing.bean.Receipt;
import org.canato.billing.bean.ReceiptItem;
import org.canato.billing.util.DiscountManagerInjector;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class MainTest {

	@Test
	public void testInput1() {
		System.out.println("-------- input 1 --------");
		Injector guiceInjector = Guice.createInjector(new DiscountManagerInjector());
		Receipt.Builder builderReceipt = guiceInjector.getInstance(Receipt.Builder.class);
		
		builderReceipt.add(Helper.getPasta(), 1);
		builderReceipt.add(Helper.getBook1(), 1);
		
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
		Injector guiceInjector = Guice.createInjector(new DiscountManagerInjector());
		Receipt.Builder builderReceipt = guiceInjector.getInstance(Receipt.Builder.class);
		
		builderReceipt.add(Helper.getCoffee(), 1);
		builderReceipt.add(Helper.getPasta(), 1);
		builderReceipt.add(Helper.getCake(), 1);
		
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
		Injector guiceInjector = Guice.createInjector(new DiscountManagerInjector());
		Receipt.Builder builderReceipt = guiceInjector.getInstance(Receipt.Builder.class);
		
		builderReceipt.add(Helper.getChocolate(), 10);
		builderReceipt.add(Helper.getWine(), 1);
		builderReceipt.add(Helper.getBook2(), 1);
		builderReceipt.add(Helper.getApple(), 5);
		
		Receipt receipt = builderReceipt.build();
		
		for (ReceiptItem item : receipt.getItems()) {
			System.out.println(item);
		}
		System.out.println("----------------");
		System.out.println(receipt);
	}
	
}
