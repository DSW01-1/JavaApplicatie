package main.java.algorithms.bpp;

import java.util.ArrayList;
import java.util.Collections;
import main.java.algorithms.BPPAlgorithm;
import main.java.main.product.Box;
import main.java.main.product.Product;

public class BruteForce extends BPPAlgorithm
{
	// returnBoxes is de arrayList die boxen returned met de producten.
	// latestBoxes is de laatste gesorteerde arraylist
	public int boxVolume;
	private ArrayList<Product> productList1 = new ArrayList<>();
	private ArrayList<Product> productList2 = new ArrayList<>();
	private ArrayList<Box> returnBoxes = new ArrayList<Box>();

	public ArrayList<Box> getBoxes(ArrayList<Product> products)
	{
		productList1.addAll(0,products.subList(0,(products.size()/2)-1));
		productList2.addAll(0,products.subList((products.size()/2),products.size()+1));
		returnBoxes.clear();
		Long startTime = System.nanoTime();
		int currentBoxAmount;
		int bestBoxAmount = products.size() + 1;
		int amountOfCalculations = 0;
		// loop through all possibilities
		for (int i = 0; i < factorial(products.size()); i++)
		{
			amountOfCalculations++;
			// currentBoxAmount contains the amount products sorted in their
			// current order.
			currentBoxAmount = sortProducts(products).size();
			// check if the amount is less than the current optimal amount
			if (currentBoxAmount < bestBoxAmount)
			{
				// if yes then returnBoxes becomes the product sorted in their
				// current order.
				returnBoxes = sortProducts(products);
				// set a new best box amount
				bestBoxAmount = currentBoxAmount;
			}
			// rotate one product one place ahead, so we test all possible
			// locations in both sublists.
			Collections.rotate(productList1.subList(1, 2), -1);
			Collections.rotate(productList2.subList(1, 2), -1);
			// rotate all products one place ahead so all possible combinations
			// get tested.
			Collections.rotate(productList1, 1);
			Collections.rotate(productList2, 1);
			//merge productlist
			products.clear();
			products.addAll(productList1);
			products.addAll(productList2);
			}
		Long endTime = System.nanoTime();
		Long duration = (endTime - startTime) / 1000000;
		startTime = null;
		endTime = null;
		duration = null;
		System.gc();
		return returnBoxes;
	}

	// sortproducts method, literally sorts products
	private ArrayList<Box> sortProducts(ArrayList<Product> products)
	{
		// arraylist to return
		ArrayList<Box> sortedBoxes = new ArrayList<>();
		// boolean for checking if a product is already in a box
		boolean doesFit = false;
		for (Product product : products)
		{
			// loop through existing boxes
			for (Box currentBox : sortedBoxes)
			{
				// does it fit in box? yes: add product
				if (currentBox.checkFit((int) product.GetSize()) && !doesFit)
				{
					currentBox.addProduct(product);
					doesFit = true;
				}
			}
			// boolean doesFit false? : create new box and add product
			if (!doesFit)
			{
				Box newBox = new Box(boxVolume);
				sortedBoxes.add(newBox);
				newBox.addProduct(product);
			}
			doesFit = false;
		}
		return sortedBoxes;
	}

	private int factorial(int n)
	{
		int factorial = 1;
		for (int i = 1; i <= n; i++)
		{
			factorial = factorial * i;
		}
		return factorial;
	}
}
