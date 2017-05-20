package main.java.algorithms.tsp;

import java.util.ArrayList;
import java.util.Collections;

import main.java.algorithms.Algorithm;
import main.java.main.Vector2;

public class ScissorEdge extends Algorithm
{
	private int gridSize;
	private ArrayList<Vector2> remainingPoints;

	public ScissorEdge(int gridSize)
	{
		this.gridSize = gridSize;
	}

	@Override
	public ArrayList<Vector2> GetShortestPath(ArrayList<Vector2> points)
	{
		remainingPoints = points;
		ArrayList<Vector2> leftSideList = new ArrayList<Vector2>();
		ArrayList<Vector2> rightSideList = new ArrayList<Vector2>();

		int currentIndex = 0;

		while (remainingPoints.size() > 0)
		{
			if ((currentIndex % 2) == 0)
			{
				leftSideList.add(AddNewPoint(true));
			}
			else
			{
				rightSideList.add(AddNewPoint(false));
			}
			currentIndex++;
		}

		ArrayList<Vector2> shortestPath = new ArrayList<Vector2>();
		shortestPath.addAll(rightSideList);
		Collections.reverse(leftSideList);
		shortestPath.addAll(leftSideList);

		return shortestPath;
	}

	private Vector2 AddNewPoint(boolean isLeft)
	{
		double shortestDistance = gridSize * gridSize;
		Vector2 closestPoint = new Vector2(0, 0);

		for (int i = 0; i < remainingPoints.size(); i++)
		{

			double distance = isLeft ? CalculateLeftDistance(remainingPoints.get(i))
					: CalculateRightDistance(remainingPoints.get(i));

			if (distance < shortestDistance)
			{
				shortestDistance = distance;
				closestPoint = remainingPoints.get(i);
			}
		}
		remainingPoints.remove(closestPoint);

		return closestPoint;
	}

	private double CalculateLeftDistance(Vector2 point)
	{
		// a^2 + b^2 = c^2
		double a = Math.pow(point.getX() - 1, 2);

		double b1 = Math.pow(point.getY() - 1, 2);
		double b2 = Math.pow((gridSize - point.getY()) * 0.8, 2);

		double c1 = Math.sqrt(a + b1);
		double c2 = Math.sqrt(a + b2);

		return c1 + c2;
	}

	private double CalculateRightDistance(Vector2 point)
	{
		// a^2 + b^2 = c^2
		double a1 = Math.pow(point.getX(), 2);
		double a2 = Math.pow(gridSize - point.getX(), 2);

		double b = Math.pow(point.getY(), 2);

		double c1 = Math.sqrt(a1 + b);
		double c2 = Math.sqrt(a2 + b);

		return c1 + c2;
	}
}
