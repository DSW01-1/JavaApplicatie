package main.java.algorithms.bpp;

import main.java.graphs.Box;
import main.java.graphs.Product;
import java.util.ArrayList;
import java.util.Collections;

public class BruteForce
{
	//returnBoxes is de arrayList die boxen returned met de producten.
	//latestBoxes is de laatste gesorteerde arraylist
	private ArrayList<Box> returnBoxes = new ArrayList<Box>();
	private ArrayList<Box> latestBoxes = new ArrayList<Box>();
	private boolean fitted;
	private int x;
	public ArrayList<Box> executeBruteForce(ArrayList<Product> products) {
		while(x<=products.size()) {
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
			Collections.rotate(products, 1);
			x++;
			if (latestBoxes.size() < returnBoxes.size()||returnBoxes.size()==0) {
				System.out.println(latestBoxes.size());
				returnBoxes = latestBoxes;
				System.out.println(returnBoxes.size());
			}
		}
		System.out.println(returnBoxes);
		System.out.println(returnBoxes.size() + " box(es) needed");
		return returnBoxes;
	}
}
