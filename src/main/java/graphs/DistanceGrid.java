package main.java.graphs;

import java.util.ArrayList;

import main.java.graphs.grid.GridTile;

/**
 * Created by William on 17-5-2017.
 */
public class DistanceGrid
{
	public double[][] distanceGrid;

	public DistanceGrid(ArrayList<GridTile> coordList)
	{
		distanceGrid = new double[coordList.size()][coordList.size()];
		calcDistancePoints(coordList);
	}

	public void calcDistancePoints(ArrayList<GridTile> coordList)
	{

		int rowCount = 0;
		for (GridTile calcPoint : coordList)
		{
			int y1 = calcPoint.getYcoord();
			int x1 = calcPoint.getXcoord();

			int columnCount = 0;
			for (GridTile calcPointTo : coordList)
			{
				if (rowCount == columnCount)
				{
					this.distanceGrid[rowCount][columnCount] = -999;
				}
				else
				{
					int y2 = calcPointTo.getYcoord();
					int x2 = calcPointTo.getXcoord();
					this.distanceGrid[rowCount][columnCount] = (double) Math.round(Math.hypot(x2 - x1, y2 - y1) * 10d)
							/ 10d;
				}
				columnCount++;
			}
			rowCount++;
		}
	}

	public void print()
	{
		for (double[] row : distanceGrid)
		{
			for (double column : row)
			{
				System.out.print(String.format("  %s  ", column));
			}
			System.out.println();
		}
	}
}
