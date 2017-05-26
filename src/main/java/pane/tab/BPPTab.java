package main.java.pane.tab;

import java.util.ArrayList;

import main.java.algorithms.BPPAlgorithm;
import main.java.algorithms.bpp.DecreasingFirstFit;
import main.java.graphs.BoxPane;
import main.java.main.product.Box;
import main.java.main.product.Product;
import main.java.pane.base.StyledPane;

public class BPPTab extends StyledPane
{
	private BoxPane boxPane;
	private ArrayList<Product> products;

	public void Setup(ArrayList<Product> products)
	{
		this.products = products;
		CreateBoxPane();
	}

	private void CreateBoxPane()
	{
		boxPane = new BoxPane();
		getChildren().add(boxPane);

		runAlgorithm(new DecreasingFirstFit());
	}

	public void runAlgorithm(BPPAlgorithm algorithmToRun)
	{
		ArrayList<Box> returnBoxes = algorithmToRun.getBoxes(products, 10);
		boxPane.AddBoxList(returnBoxes);
	}

}
