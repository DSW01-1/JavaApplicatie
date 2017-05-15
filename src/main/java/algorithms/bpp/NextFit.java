package main.java.algorithms.bpp;

import javafx.scene.control.ListView;
import main.java.graphs.Box;
import main.java.graphs.Product;
import main.java.pane.simulation.BppSimulation;

import java.util.ArrayList;

public class NextFit
{
	// boxVolume gets set from bppSimulation
	public int boxVolume;
	// list of boxes returned
	public ArrayList<Box> returnBoxes = new ArrayList<Box>();
	// initialize currentbox as box with volume 0 so a new box gets created.
	private Box currentBox = new Box(0);
	private BppSimulation simulation;

	public NextFit(BppSimulation simulation){
		this.simulation=simulation;
	}


	// method to print all products in list
	public void printAllProducts(ArrayList<Product> products)
	{
		for (Product product : products)
		{
			simulation.addConsoleItem("products: " + product, "DEBUG");
		}
	}

	// method to execute algorithm
	public ArrayList<Box> executeNextFit(ArrayList<Product> products)
	{
		// clear returnBoxes arraylist so no leftovers of previous calculations
		// remain.
		returnBoxes.clear();
		simulation.addConsoleItem("Cleared cache.", "DEBUG");
		simulation.addConsoleItem("Starting Next Fit Algorithm.", "INFO");
		simulation.addConsoleItem("Box size used: " + boxVolume + ". Starting with 0 boxes.", "INFO");
		Long startTime = System.nanoTime();
		// loop through all products asked for
		for (Product product : products)
		{
			// check if product fits in current box
			if (currentBox.checkFit(product.GetSize()))
			{
				currentBox.addProduct(product);
				simulation.addConsoleItem("Added product (size: " + product.GetSize() + ") to current box.", "DEBUG");
			}
			else
			{
				// else create new box for currentBox and add product then
				currentBox = new Box(boxVolume);
				currentBox.addProduct(product);
				returnBoxes.add(currentBox);
				simulation.addConsoleItem("Could not add product(size: " + product.GetSize()
						+ ") to current box. Created new box. Total amount of boxes: " + returnBoxes.size()
						+ ". Added product to new box.", "DEBUG");
			}
		}
		// set currentBox size 0 again so a new one immediately gets initialized
		// and added next time at execution
		currentBox = new Box(0);
		Long endTime = System.nanoTime();
		simulation.addConsoleItem("Total amount of boxes needed:" + returnBoxes.size() + ", filled with "
				+ products.size() + " products.", "INFO");
		simulation.addConsoleItem("FINISHED.", "INFO");
		Long duration = (endTime - startTime) / 100000;
		simulation.addConsoleItem("Took " + duration + " milliseconds", "INFO");
		System.gc();
		return returnBoxes;

	}
}
