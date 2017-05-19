package main.java.graphs.grid;

import main.java.constant.Constants;
import main.java.main.ScreenProperties;

public class ProductGrid extends BaseGrid
{

	public ProductGrid(int tileAmount)
	{
		super(tileAmount);
		setLayoutX(ScreenProperties.getScreenWidth() / 2 - Constants.gridSize / 2);
		setLayoutY(15);
	}

	@Override
	public void Redraw()
	{
		super.Redraw();
	}

}
