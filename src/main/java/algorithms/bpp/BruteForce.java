package main.java.algorithms.bpp;

import java.util.ArrayList;
import java.util.Collections;

import main.java.main.product.Box;
import main.java.main.product.Product;
import main.java.pane.simulation.BppSimulation;

public class BruteForce
{
	// returnBoxes is de arrayList die boxen returned met de producten.
	// latestBoxes is de laatste gesorteerde arraylist
	public int boxVolume;
	private BppSimulation simulation;
	private ArrayList<Box> returnBoxes = new ArrayList<Box>();

	public BruteForce(BppSimulation simulation)
	{
		this.simulation = simulation;
	}

	public ArrayList<Box> executeBruteForce(ArrayList<Product> products)
	{
		simulation.addConsoleItem("Cleared cache.", "DEBUG");
		simulation.addConsoleItem("Starting Brute Force Algorithm.", "INFO");
		simulation.addConsoleItem("Box size used: " + boxVolume + ". Starting with 0 boxes.", "INFO");
		returnBoxes.clear();
		Long startTime = System.nanoTime();
		int currentBoxAmount;
		int bestBoxAmount = products.size() + 1;
		int amountOfCalculations = 0;
		// loop through all possibilities
		simulation.addConsoleItem(String.valueOf(products.size()), "DEBUG");
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
				simulation.addConsoleItem("New best amount", "DEBUG");
			}
			// rotate one product one place ahead, so we test all possible
			// locations.
			Collections.rotate(products.subList(1, products.size()), 1);
			// rotate all products one place ahead so all possible combinations
			// get tested.
			Collections.rotate(products, 1);

		}
		Long endTime = System.nanoTime();
		simulation.addConsoleItem("Best Box amount = " + bestBoxAmount, "DEBUG");
		simulation.addConsoleItem("FINISHED", "INFO");
		simulation.addConsoleItem("Calculated " + amountOfCalculations + " calculations", "INFO");
		Long duration = (endTime - startTime) / 1000000;
		simulation.addConsoleItem("Took " + duration + " milliseconds", "INFO");
		simulation.addConsoleItem("---------------------------------------------------------------------", "INFO");
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
