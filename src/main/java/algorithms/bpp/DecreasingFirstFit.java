package main.java.algorithms.bpp;

import main.java.graphs.Box;
import main.java.graphs.Product;
import main.java.pane.simulation.BppSimulation;

import java.util.ArrayList;
import java.util.Collections;

public class DecreasingFirstFit
{
	public int boxVolume;
	private BppSimulation simulation;
	private ArrayList<Box> returnBoxes = new ArrayList<Box>();
	private boolean doesFit = false;
	// initialize currentBox with volume 0 so a new one gets called at the
	// start.
	private Box currentBox = new Box(0);

	public DecreasingFirstFit(BppSimulation simulation)
	{
		this.simulation = simulation;
	}

	public ArrayList<Box> executeDecreasingFirstFit(ArrayList<Product> products)
	{
		returnBoxes.clear();
		// sort and reverse collection ordered in descending order
		Collections.sort(products);
		Collections.reverse(products);
		// loop through products
		for (Product product : products)
		{
			// loop through boxes
			for (Box currentBox : returnBoxes)
			{
				// check if product fits in box, if not go to next box
				if (currentBox.checkFit((int) product.GetSize()) && !doesFit)
				{
					currentBox.addProduct(product);
					simulation.addConsoleItem("Product fitted", "DEBUG");
					// variable that prevents one product added in more boxes.
					doesFit = true;
				}
			}
			// if product did not fit anywhere, create new box
			if (!doesFit)
			{
				Box newBox = new Box(boxVolume);
				returnBoxes.add(newBox);
				newBox.addProduct(product);
				simulation.addConsoleItem("Product did not fit with size: " + product.GetSize()
						+ ", new box created and added, amount of boxes " + returnBoxes.size(), "DEBUG");
			}
			doesFit = false;
		}
		simulation.addConsoleItem("FINISHED", "INFO");
		simulation.addConsoleItem("Boxes used: " + returnBoxes.size(), "INFO");
		return returnBoxes;
	}
}
