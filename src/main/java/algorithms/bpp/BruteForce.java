package main.java.algorithms.bpp;

import main.java.graphs.Box;
import main.java.graphs.Product;
import main.java.pane.simulation.BppSimulation;

import java.util.ArrayList;
import java.util.Collections;

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
		int currentBoxAmount;
		int bestBoxAmount = products.size() + 1;
		for (int i = 0; i < products.size(); i++)
		{
			currentBoxAmount = sortProducts(products).size();
			if (currentBoxAmount < bestBoxAmount)
			{
				bestBoxAmount = currentBoxAmount;
				simulation.addConsoleItem("New best amount", "DEBUG");
			}
			else
			{
				bestBoxAmount = currentBoxAmount;
			}
			Collections.rotate(products, 1);
		}
		simulation.addConsoleItem("Best Box amount = " + bestBoxAmount, "DEBUG");
		simulation.addConsoleItem("FINISHED", "INFO");
		return returnBoxes;
	}

	private ArrayList<Box> sortProducts(ArrayList<Product> products)
	{
		ArrayList<Box> sortedBoxes = new ArrayList<>();
		boolean doesFit = false;
		for (Product product : products)
		{
			for (Box currentBox : sortedBoxes)
			{
				if (currentBox.checkFit(product.GetSize()) && !doesFit)
				{
					simulation.addConsoleItem("Product fitted", "DEBUG");
					currentBox.addProduct(product);
					doesFit = true;
				}
			}
			if (!doesFit)
			{
				Box newBox = new Box(boxVolume);
				sortedBoxes.add(newBox);
				newBox.addProduct(product);
				simulation.addConsoleItem("Product did not fit, new box created. Amount of boxes is: " + sortedBoxes.size(), "DEBUG");
			}
			doesFit = false;
		}
		return sortedBoxes;
	}
}
