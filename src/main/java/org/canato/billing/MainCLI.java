package org.canato.billing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.canato.billing.bean.Item;
import org.canato.billing.bean.Receipt;
import org.canato.billing.bean.ReceiptItem;
import org.canato.billing.manager.ReceiptManager;
import org.canato.billing.util.DiscountManagerInjector;

import au.com.bytecode.opencsv.CSVReader;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Main class used for reading the CSV passed in args[0] and calculating the
 * bill.
 * 
 * @author matteo
 *
 */
public class MainCLI {
	
	private static ReceiptManager manager;

	public static void main(String[] args) {
		MainCLI obj = new MainCLI();
		
		if (args.length < 1) {
			System.out.println("missing CSV fileName");
		} else {
			Injector guiceInjector = Guice.createInjector(new DiscountManagerInjector());
			manager = guiceInjector.getInstance(ReceiptManager.class);
			
			obj.run(args[0]);			
		}
	}
	
	/**
	 * Read the CSV file, convert each line into a list of {@link Item}
	 * and call the billing service.
	 * 
	 * @param fileName
	 */
	public void run(String fileName) {
		CSVReader reader = null;
		try {
			List<Item> items = new ArrayList<Item>();
			
			reader = new CSVReader(new FileReader(fileName));
			String[] itemLine;
			while ((itemLine = reader.readNext()) != null) {
				Item.Type type 	= Item.Type.valueOf(itemLine[0]);
				String name 	= itemLine[1];
				int quantity 	= new Integer(itemLine[2]);
				Double price	= new Double(itemLine[3]);
				
				items.add(new Item(name, price, type, quantity));
			}
			
			Receipt receipt = manager.calculate(items);
			
			for (ReceiptItem item : receipt.getItems()) {
				System.out.println(item.print());
			}
			System.out.println("----------------");
			System.out.println(receipt.print());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("exiting...");
		}

	}

}
