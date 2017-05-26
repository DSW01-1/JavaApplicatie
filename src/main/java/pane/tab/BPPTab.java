package main.java.pane.tab;

import java.util.ArrayList;

import main.java.algorithms.BPPAlgorithm;
import main.java.algorithms.bpp.DecreasingFirstFit;
import main.java.graphs.BoxPane;
import main.java.main.product.Product;
import main.java.pane.base.StyledPane;

public class BPPTab extends StyledPane
{
	private BoxPane boxPane;
	private ArrayList<Product> products;

	public void Setup(ArrayList<Product> products)
	{
		CreateBoxPane();
	}

	private void CreateBoxPane()
	{
		BoxPane boxPane = new BoxPane();
		getChildren().add(boxPane);

		DecreasingFirstFit algorithm = new DecreasingFirstFit();
		runAlgorithm(algorithm);
	}

	public void runAlgorithm(BPPAlgorithm algorithmToRun)
	{
		boxPane.AddBoxList(algorithmToRun.getBoxes(products, 10));
	}

}
