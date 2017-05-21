package main.java.algorithms.tsp;

import java.util.ArrayList;
import java.util.Collections;

import main.java.algorithms.Algorithm;
import main.java.main.Vector2;

public class ScissorEdge extends Algorithm
{
	private int gridSize;
	private ArrayList<Vector2> remainingPoints;
	private ArrayList<Vector2> leftSideList, rightSideList;

	public ScissorEdge(int gridSize)
	{
		this.gridSize = gridSize;
	}

	@Override
	public ArrayList<Vector2> GetShortestPath(ArrayList<Vector2> points)
	{
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		remainingPoints = points;
		leftSideList = new ArrayList<Vector2>();
		rightSideList = new ArrayList<Vector2>();
		rightSideList.add(new Vector2(1, 1));

		int currentIndex = 0;

		while (remainingPoints.size() > 0)
		{
			System.out.println(remainingPoints.size());

			if ((currentIndex % 2) == 0)
			{
				rightSideList.add(AddNewPoint(false));
			}
			else
			{
				leftSideList.add(AddNewPoint(true));
			}
			currentIndex++;
		}

		ArrayList<Vector2> shortestPath = new ArrayList<Vector2>();
		shortestPath.addAll(rightSideList);
		Collections.reverse(leftSideList);
		shortestPath.addAll(leftSideList);
		shortestPath.add(new Vector2(1, 1));

		return shortestPath;
	}

	private Vector2 AddNewPoint(boolean isLeft)
	{
		double shortestDistance = gridSize * gridSize;
		Vector2 closestPoint = new Vector2(100, 100);

		for (Vector2 remainingPoint : remainingPoints)
		{
			double distance = isLeft ? CalculateLeftDistance(remainingPoint) : CalculateRightDistance(remainingPoint);

			if (distance < shortestDistance)
			{
				shortestDistance = distance;
				closestPoint = remainingPoint;
			}
		}
		remainingPoints.remove(closestPoint);

		return closestPoint;
	}

	private double CalculateLeftDistance(Vector2 point)
	{
		// a^2 + b^2 = c^2
		double a = Math.pow(point.getX() - 1, 2);

		double b1 = Math.pow((point.getY() - 1) * 1.4f, 2);
		double b2 = Math.pow(gridSize - point.getY(), 2);

		double c3 = 0;

		if (leftSideList.size() > 0)
		{
			double x = (leftSideList.get(leftSideList.size() - 1).getX() - point.getX());
			double y = (leftSideList.get(leftSideList.size() - 1).getY() - point.getY());
			c3 = Math.sqrt(x * x + y * y);
		}

		double c1 = Math.sqrt(a + b1);
		double c2 = Math.sqrt(a + b2);

		return c1 + c2 + (c3 * 0.5f);
	}

	private double CalculateRightDistance(Vector2 point)
	{
		// a^2 + b^2 = c^2
		double a1 = Math.pow(point.getX() - 1, 2);
		double a2 = Math.pow((gridSize - point.getX()) * 1.4f, 2);

		double b = Math.pow(point.getY() - 1, 2);

		double c3 = 0;

		if (rightSideList.size() > 0)
		{
			double x = (rightSideList.get(rightSideList.size() - 1).getX() - point.getX());
			double y = (rightSideList.get(rightSideList.size() - 1).getY() - point.getY());
			c3 = Math.sqrt(x * x + y * y);
		}

		double c1 = Math.sqrt(a1 + b);
		double c2 = Math.sqrt(a2 + b);

		return c1 + c2 + (c3 * 0.5f);
	}
}
