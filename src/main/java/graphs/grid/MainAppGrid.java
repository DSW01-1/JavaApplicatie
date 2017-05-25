package main.java.graphs.grid;

import java.util.ArrayList;

import main.java.constant.Constants;
import main.java.main.Vector2;
import main.java.main.product.Product;

public class MainAppGrid extends PathGrid
{
	private ArrayList<Product> productArray;

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
		pathList = shortestPath;
		productArray = products;

		Redraw();
	}

	public void Redraw()
	{
		gc.clearRect(0, 0, getWidth(), getHeight());

		if (robotPos != null)
		{
			DrawRobotPos(robotPos);
		}
		
		if(pathList != null)
		{
			DrawPathLines(pathList);
		}
		

		for (Product product : productArray)
		{
			DrawTile(product.GetCoords());
		}

		super.Redraw();
	}

}
