package main.java.algorithms.bpp;

import main.java.graphs.Box;
import main.java.graphs.Product;
import main.java.pane.simulation.BppSimulation;

import java.util.ArrayList;

public class FirstFit
{
	public ArrayList<Box> returnBoxes = new ArrayList();
	private boolean doesFit = false;

	public int boxVolume;
	private BppSimulation simulation;

	public FirstFit(BppSimulation simulation)
	{
		this.simulation = simulation;
	}

	public ArrayList<Box> executeFirstFit(ArrayList<Product> products)
	{
		Long startTime = System.nanoTime();
		returnBoxes.clear();
		this.simulation.addConsoleItem("Cleared cache.", "DEBUG");
		this.simulation.addConsoleItem("Starting First Fit Algorithm.", "INFO");
		this.simulation.addConsoleItem("Box size used: " + boxVolume + ". Starting with 0 boxes.", "INFO");
		for (Product product : products)
		{
			for (Box currentBox : returnBoxes)
			{
				if (currentBox.checkFit(product.GetSize()) && !doesFit)
				{
					currentBox.addProduct(product);
					doesFit = true;
					this.simulation.addConsoleItem("Added product (size: " + product.GetSize() + ")to a box.", "DEBUG");
				}
			}
			if (!doesFit)
			{
				Box newBox = new Box(boxVolume);
				returnBoxes.add(newBox);
				newBox.addProduct(product);
				this.simulation.addConsoleItem("Could not add product(size: " + product.GetSize()
						+ ") to a box. Created new box. Total amount of boxes: " + returnBoxes.size()
						+ ". Added product to new box.", "DEBUG");

			}
			doesFit = false;
		}
		this.simulation.addConsoleItem("Total amount of boxes needed:" + returnBoxes.size() + ", filled with "
				+ products.size() + " products.", "INFO");
		this.simulation.addConsoleItem("FINISHED.", "INFO");
		Long endtime = System.nanoTime();
		Long duration = (endtime - startTime)/100000;
		simulation.addConsoleItem("Took " + duration + " milliseconds","INFO");
		endtime = null;
		startTime = null;
		duration = null;
		System.gc();
		return returnBoxes;

	}
}