package main.java.algorithms.tsp;

import java.util.ArrayList;

import main.java.algorithms.Algorithm;
import main.java.main.Vector2;

public class TwoWayEdgeNearestNeighbour extends Algorithm
{

	public TwoWayEdgeNearestNeighbour(int gridSize)
	{
		
	}

	@Override
	public ArrayList<Vector2> GetShortestPath(ArrayList<Vector2> points)
	{
		ArrayList<Vector2> leftSideList = new ArrayList<Vector2>();
		ArrayList<Vector2> RightSideList = new ArrayList<Vector2>();

		for (Vector2 point : points)
		{

		}

		return null;
	}

	private boolean DecideSide(Vector2 point)
	{

		return false;
	}
}
