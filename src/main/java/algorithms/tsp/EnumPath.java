package main.java.algorithms.tsp;

import java.util.ArrayList;

import main.java.main.Vector2;

public class EnumPath
{
	private double pathLength;
	private ArrayList<Vector2> coordinates = new ArrayList<Vector2>();

	public EnumPath(double PathLength, ArrayList<Vector2> coords)
	{
		this.pathLength = PathLength;

		coordinates.add(new Vector2(1, 1));
		coordinates.addAll(coords);
		coordinates.add(new Vector2(1, 1));
	}

	public double getPathLength()
	{
		return pathLength;
	}

	public ArrayList<Vector2> getTiles()
	{
		return coordinates;
	}

}
