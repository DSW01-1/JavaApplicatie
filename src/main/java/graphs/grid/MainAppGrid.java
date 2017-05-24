package main.java.graphs.grid;

import java.util.ArrayList;

import main.java.constant.Constants;
import main.java.main.Vector2;
import main.java.main.product.Product;

public class MainAppGrid extends BaseGrid
{

	public MainAppGrid()
	{
		super(Constants.baseWareHouseSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void AddMouseEventHandler()
	{
		// TODO Auto-generated method stub

	}

	public void Redraw(ArrayList<Vector2> shortestPath, ArrayList<Product> products)
	{
		gc.clearRect(0, 0, getWidth(), getHeight());

		for (Product product : products)
		{
			DrawTile(product.GetCoords());
		}
		
	}

}
