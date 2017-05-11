package main.java.algorithms.bpp;

import main.java.graphs.Box;
import main.java.graphs.Product;
import java.util.ArrayList;

public class NextFit
{
	public ArrayList<Box> returnBoxes = new ArrayList<Box>();
	private boolean fitted = false;
	private Box currentBox = new Box(10);

	public ArrayList<Box> executeNextFit(ArrayList<Product> products) {
		returnBoxes.clear();
		returnBoxes.add(currentBox);
		for (Product product : products)
		{
			if (currentBox.checkFit(product.GetSize()) && !fitted)
			{
				currentBox.addProduct(product);
				fitted = true;
			}
			if (!fitted)
			{
				currentBox = new Box(10);
				currentBox.addProduct(product);
				returnBoxes.add(currentBox);
			}
			fitted = false;
		}
		currentBox= new Box(10);
		System.out.println(returnBoxes.size() + " box(es) needed");
		System.out.println(returnBoxes);
		return returnBoxes;
	}
}
