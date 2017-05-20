package main.java.algorithms.tsp;

import java.util.ArrayList;

import main.java.algorithms.Algorithm;
import main.java.main.Vector2;

public class TwoWayEdgeNearestNeighbour extends Algorithm
{
	private int gridSize;

	public TwoWayEdgeNearestNeighbour(int gridSize)
	{
		this.gridSize = gridSize;
	}

	@Override
	public ArrayList<Vector2> GetShortestPath(ArrayList<Vector2> points)
	{
		ArrayList<Vector2> leftSideList = new ArrayList<Vector2>();
		ArrayList<Vector2> rightSideList = new ArrayList<Vector2>();

		for (Vector2 point : points)
		{
			if (DecideSide(point))
			{
				leftSideList.add(point);
			}
			else
			{
				rightSideList.add(point);
			}
		}

		return null;
	}

	private boolean DecideSide(Vector2 point)
	{
		// a^2 + b^2 = c^2
		int a1 = (int) Math.pow(point.getX(), 2);
		int a2 = (int) Math.pow(gridSize - point.getX(), 2);

		int b1 = (int) Math.pow(gridSize - point.getY(), 2);
		int b2 = (int) Math.pow(point.getY(), 2);

		double c1 = Math.sqrt(a1 + b1);
		double c2 = Math.sqrt(a2 + b2);

		return c1 <= c2 ? true : false;
	}
}
