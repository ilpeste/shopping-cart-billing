package org.canato.billing.test;

import java.util.ArrayList;
import java.util.List;

import org.canato.billing.bean.Item;
import org.canato.billing.bean.Receipt;
import org.canato.billing.bean.ReceiptItem;
import org.canato.billing.manager.ReceiptManager;
import org.canato.billing.test.helper.ItemHelper;
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
	
	private ReceiptManager manager;
	
	@Before
	public void setUp() {
		Injector guiceInjector = Guice.createInjector(new DiscountManagerInjector());
		manager = guiceInjector.getInstance(ReceiptManager.class);
	}

	@Test
	public void testInput1() {
		System.out.println("-------- input 1 --------");
		
		List<Item> items = new ArrayList<Item>();
		items.add(ItemHelper.getPasta());
		items.add(ItemHelper.getBook1());
		
		Receipt receipt = manager.calculate(items);
		
		for (ReceiptItem item : receipt.getItems()) {
			System.out.println(item.print());
		}
		System.out.println("----------------");
		System.out.println(receipt.print());
	}
	
	@Test
	public void testInput2() {
		System.out.println("-------- input 2 --------");
		
		List<Item> items = new ArrayList<Item>();
		items.add(ItemHelper.getCoffee());
		items.add(ItemHelper.getPasta());
		items.add(ItemHelper.getCake());
		
		Receipt receipt = manager.calculate(items);
		
		for (ReceiptItem item : receipt.getItems()) {
			System.out.println(item.print());
		}
		System.out.println("----------------");
		System.out.println(receipt.print());
	}
	
	@Test
	public void testInput3() {
		System.out.println("-------- input 3 --------");
		
		List<Item> items = new ArrayList<Item>();
		items.add(ItemHelper.getChocolate());
		items.add(ItemHelper.getWine());
		items.add(ItemHelper.getBook2());
		items.add(ItemHelper.getApple());
		
		Receipt receipt = manager.calculate(items);
		
		for (ReceiptItem item : receipt.getItems()) {
			System.out.println(item.print());
		}
		System.out.println("----------------");
		System.out.println(receipt.print());
	}
	
}
