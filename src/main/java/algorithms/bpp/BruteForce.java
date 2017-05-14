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
		//loop through all products
		for (int i = 0; i < products.size(); i++)
		{
			//loop through all products again but with one product at each location this time, see line 40
			for(int j=0;j<products.size();j++)
			{
				//currentBoxAmount contains the amount products sorted in their current order.
				currentBoxAmount = sortProducts(products).size();
				//check if the amount is less than the current optimal amount
				if (currentBoxAmount < bestBoxAmount)
				{
					//if yes then returnBoxes becomes the product sorted in their current order.
					returnBoxes=sortProducts(products);
					//set a new best box amount
					bestBoxAmount = currentBoxAmount;
					simulation.addConsoleItem("New best amount", "DEBUG");
				}
				//rotate one product one place ahead, so we test all possible locations.
				Collections.rotate(products.subList(j, j+1), -1);
			}
			//rotate all products one place ahead so all possible combinations get tested.
			Collections.rotate(products, 1);

		}
		simulation.addConsoleItem("Best Box amount = " + bestBoxAmount, "DEBUG");
		simulation.addConsoleItem("FINISHED", "INFO");
		simulation.addConsoleItem("---------------------------------------------------------------------","INFO");
		simulation.addConsoleItem("Boxes ordered: "+returnBoxes,"DEBUG");
		for(int k=0;k<returnBoxes.get(0).getSize();k++){
			simulation.addConsoleItem(returnBoxes.get(0).printSizeAtLocation(k),"DEBUG");
		}
		return returnBoxes;
	}


	//sortproducts method, literally sorts products
	private ArrayList<Box> sortProducts(ArrayList<Product> products)
	{
		//arraylist to return
		ArrayList<Box> sortedBoxes = new ArrayList<>();
		//boolean for checking if a product is already in a box
		boolean doesFit = false;
		for (Product product : products)
		{
			//loop through existing boxes
			for (Box currentBox : sortedBoxes)
			{
				//does it fit in box? yes: add product
				if (currentBox.checkFit(product.GetSize()) && !doesFit)
				{
					simulation.addConsoleItem("Product fitted", "DEBUG");
					currentBox.addProduct(product);
					doesFit = true;
				}
			}
			//boolean doesFit false? : create new box and add product
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
