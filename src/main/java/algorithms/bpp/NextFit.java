package main.java.algorithms.bpp;

import javafx.scene.control.ListView;
import main.java.graphs.Box;
import main.java.graphs.Product;
import main.java.pane.simulation.BppSimulation;

import java.util.ArrayList;

public class NextFit
{
	public int boxVolume;
	private BppSimulation simulation;
	public ListView<String> consoleList = new ListView<String>();
	public ArrayList<Box> returnBoxes = new ArrayList<Box>();
	private boolean fitted = false;
	private Box currentBox = new Box(boxVolume);
	public NextFit(BppSimulation simulation){
		this.simulation = simulation;
	}

	public void printAllProducts(ArrayList<Product> products, BppSimulation simulation){
		for(Product product : products){
			simulation.addConsoleItem("products: "+ product,"DEBUG");
		}
	}
	public ArrayList<Box> executeNextFit(ArrayList<Product> products, BppSimulation simulation) {
		returnBoxes.clear();
		returnBoxes.add(currentBox);
		simulation.addConsoleItem("Box size used: "+ boxVolume +".","INFO");
		for (Product product : products)
		{
			if (currentBox.checkFit(product.GetSize()) && !fitted)
			{
				currentBox.addProduct(product);
				fitted = true;
				simulation.addConsoleItem("Added product (size: "+product.GetSize()+")to current box.","DEBUG");
			}
			if (!fitted)
			{
				currentBox = new Box(boxVolume);
				currentBox.addProduct(product);
				returnBoxes.add(currentBox);
				simulation.addConsoleItem("Could not add product(size: "+product.GetSize()+") to current box. Created new box. Total amount of boxes: "+returnBoxes.size() + ".","DEBUG");
			}
			fitted = false;
		}
		currentBox= new Box(boxVolume);
		simulation.addConsoleItem("Total amount of boxes needed:"+returnBoxes.size()+", filled with "+ products.size()+" products.","DEBUG");
		simulation.addConsoleItem("FINISHED.","INFO");

		return returnBoxes;
	}
}
