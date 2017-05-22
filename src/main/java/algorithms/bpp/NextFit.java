package main.java.algorithms.bpp;

import java.util.ArrayList;

import main.java.main.product.Box;
import main.java.main.product.Product;
import main.java.pane.simulation.BppSimulation;

public class NextFit
{
	// boxVolume gets set from bppSimulation
	public int boxVolume;
	// list of boxes returned
	public ArrayList<Box> returnBoxes = new ArrayList<Box>();
	// initialize currentbox as box with volume 0 so a new box gets created.
	private Box currentBox = new Box(0);

	// method to print all products in list
	public String printAllProducts(ArrayList<Product> products)
	{
		String productGegevens = new String();
		for (Product product : products)
		{
			productGegevens = ("Size: " + product.GetSize() + ". ID: " + product.GetId() + ". Coords"
					+ product.GetCoords() + ". Name: " + product.GetName() + ".");;
		}
		return productGegevens;
	}

	// method to execute algorithm
	public ArrayList<Box> executeNextFit(ArrayList<Product> products)
	{
		// clear returnBoxes arraylist so no leftovers of previous calculations
		// remain.
		returnBoxes.clear();
		Long startTime = System.nanoTime();
		// loop through all products asked for
		for (Product product : products)
		{
			// check if product fits in current box
			if (currentBox.checkFit((int) product.GetSize()))
			{
				currentBox.addProduct(product);
			}
			else
			{
				// else create new box for currentBox and add product then
				currentBox = new Box(boxVolume);
				currentBox.addProduct(product);
				returnBoxes.add(currentBox);
			}
		}
		// set currentBox size 0 again so a new one immediately gets initialized
		// and added next time at execution
		currentBox = new Box(0);
		Long endTime = System.nanoTime();
		Long duration = (endTime - startTime) / 1000000;
		System.gc();
		return returnBoxes;

	}
}
