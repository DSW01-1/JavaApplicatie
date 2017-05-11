package main.java.algorithms.bpp;

import main.java.graphs.Box;
import main.java.graphs.Product;
import java.util.ArrayList;
import java.util.Collections;

public class BruteForce
{
	private ArrayList<Box> returnBoxes = new ArrayList<Box>();
	private ArrayList<Box> latestBoxes = new ArrayList<Box>();
	private int leastBoxAmount = 1000;
	private boolean fitted;
	private int x;
	public ArrayList<Box> executeBruteForce(ArrayList<Product> products) {
		for (Product product : products) {
			for (Box currentBox : latestBoxes) {
				if (currentBox.checkFit(product.GetSize()) && fitted == false) {
					currentBox.addProduct(product);
					fitted = true;
				}
			}
			if (!fitted) {
				Box currentBox = new Box(10);
				currentBox.addProduct(product);
				latestBoxes.add(currentBox);
			}
			fitted = false;
		}
		if (latestBoxes.size() < leastBoxAmount) {
			leastBoxAmount = latestBoxes.size();
			returnBoxes = latestBoxes;
		}
		Collections.rotate(products, 1);
		System.out.println(returnBoxes);
		System.out.println(returnBoxes.size() + " box(es) needed");
		return returnBoxes;
	}

	public void clearHistory(){
		returnBoxes.clear();
		latestBoxes.clear();
		leastBoxAmount=1000;
	}
}
