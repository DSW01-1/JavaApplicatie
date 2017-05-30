package main.java.graphs.grid;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import main.java.algorithms.ITspAlgorithm;
import main.java.algorithms.tsp.ScissorEdge;
import main.java.constant.ColorConstants;
import main.java.main.Vector2;

public class PathGrid extends BaseGrid
{
	protected ITspAlgorithm usedAlgorithm;
	protected ArrayList<Vector2> pathList;
	protected double pathLength;
	protected int tileAmount;

	public PathGrid(int tileAmount)
	{
		super(tileAmount);
	}

	@Override
	protected void AddMouseEventHandler()
	{
		// TODO
	}

	/**
	 * Draw lines between the given points
	 * 
	 * @param points
	 */
	public void DrawPathLines(ArrayList<Vector2> points)
	{
		gc.setLineWidth(5);
		gc.setStroke(ColorConstants.GetBlzitNumber(pathLength) ? ColorConstants.standardBlzitColor : lineColor);

		if (usedAlgorithm instanceof ScissorEdge)
		{
			DrawScissorPath(points);
		}
		else
		{
			for (int i = 0; i < points.size() - 1; i++)
			{
				double[] pathCoords = GetPathLine(points.get(i), points.get(i + 1));
				gc.strokeLine(pathCoords[0], pathCoords[2], pathCoords[1], pathCoords[3]);
			}
		}
	}

	private void DrawScissorPath(ArrayList<Vector2> points)
	{
		gc.setStroke(ColorConstants.GetBlzitNumber(pathLength) ? ColorConstants.standardBlzitColor : Color.RED);
		for (int i = 0; i <= ((points.size() - 2) / 2); i++)
		{
			double[] pathCoords = GetPathLine(points.get(i), points.get(i + 1));
			gc.strokeLine(pathCoords[0], pathCoords[2], pathCoords[1], pathCoords[3]);
		}

		gc.setStroke(ColorConstants.GetBlzitNumber(pathLength) ? ColorConstants.standardBlzitColor : Color.PURPLE);
		double[] pathCoords1 = GetPathLine(points.get((points.size() / 2) - 1), points.get((points.size() / 2)));
		gc.strokeLine(pathCoords1[0], pathCoords1[2], pathCoords1[1], pathCoords1[3]);

		gc.setStroke(ColorConstants.GetBlzitNumber(pathLength) ? ColorConstants.standardBlzitColor : Color.BLUE);
		for (int i = (points.size() / 2); i < points.size() - 1; i++)
		{
			double[] pathCoords = GetPathLine(points.get(i), points.get(i + 1));
			gc.strokeLine(pathCoords[0], pathCoords[2], pathCoords[1], pathCoords[3]);
		}
	}

	/**
	 * TODO
	 * 
	 * @param point1
	 * @param point2
	 * @return
	 */
	private double[] GetPathLine(Vector2 point1, Vector2 point2)
	{
		double[] line = new double[4];
		double mathThing = (tileSize / 2);
		line[0] = (point1.getX() * tileSize) - mathThing;
		line[1] = (point2.getX() * tileSize) - mathThing;

		line[2] = (point1.getY() * tileSize) - mathThing;
		line[3] = (point2.getY() * tileSize) - mathThing;
		return line;
	}

	@Override
	public void SetRobotPos(Vector2 newPos)
	{
		if (robotPos == null)
		{
			robotPos = new Vector2(0, 0);
		}

		robotPos = newPos;
	}

}
