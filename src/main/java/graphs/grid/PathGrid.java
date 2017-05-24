package main.java.graphs.grid;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import main.java.algorithms.ITspAlgorithm;
import main.java.algorithms.tsp.ScissorEdge;
import main.java.constant.Constants;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void Redraw()
	{
		super.Redraw();
	}

	/**
	 * Draw lines between the given points
	 * 
	 * @param points
	 */
	public void DrawPathLines(ArrayList<Vector2> points)
	{
		gc.setLineWidth(5);
		gc.setStroke(Constants.GetBlzitNumber(pathLength) ? Constants.standardBlzitColor : lineColor);

		if (usedAlgorithm instanceof ScissorEdge)
		{
			gc.setStroke(Constants.GetBlzitNumber(pathLength) ? Constants.standardBlzitColor : Color.RED);
			for (int i = 0; i <= ((points.size() - 2) / 2); i++)
			{
				double[] pathCoords = GetPathLine(points.get(i), points.get(i + 1));
				gc.strokeLine(pathCoords[0], pathCoords[2], pathCoords[1], pathCoords[3]);
			}

			gc.setStroke(Constants.GetBlzitNumber(pathLength) ? Constants.standardBlzitColor : Color.PURPLE);
			double[] pathCoords1 = GetPathLine(points.get((points.size() / 2) - 1), points.get((points.size() / 2)));
			gc.strokeLine(pathCoords1[0], pathCoords1[2], pathCoords1[1], pathCoords1[3]);

			gc.setStroke(Constants.GetBlzitNumber(pathLength) ? Constants.standardBlzitColor : Color.BLUE);
			for (int i = (points.size() / 2); i < points.size() - 1; i++)
			{
				double[] pathCoords = GetPathLine(points.get(i), points.get(i + 1));
				gc.strokeLine(pathCoords[0], pathCoords[2], pathCoords[1], pathCoords[3]);
			}
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
		line[0] = (point1.getX() * tileSize) - tileSize + (tileSize / 2);
		line[1] = (point2.getX() * tileSize) - tileSize + (tileSize / 2);

		line[2] = (point1.getY() * tileSize) - tileSize + (tileSize / 2);
		line[3] = (point2.getY() * tileSize) - tileSize + (tileSize / 2);
		return line;
	}

	/**
	 * Set the current Position of the robot
	 * 
	 * @param transPos
	 */
	public void SetRobotPos(Vector2 transPos)
	{
		if (robotPos == null)
		{
			robotPos = new Vector2(0, 0);
		}

		if (robotPos.getX() + transPos.getX() >= 0 && robotPos.getX() + transPos.getX() < tileAmount)
		{
			robotPos.setX((int) (robotPos.getX() + transPos.getX()));
		}

		if (robotPos.getY() > transPos.getY())
		{
			robotPos.setY((int) (robotPos.getY() + transPos.getY()));
		}

		Redraw();
	}

}
