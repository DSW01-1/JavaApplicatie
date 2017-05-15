
package main.java.algorithms.tsp;

import java.util.ArrayList;
import java.util.List;

import main.java.constant.Constants;
import main.java.graphs.GridTile;
import main.java.main.Vector2;

public class NearestNeighbour
{
	private ArrayList<GridTile> CoordList;
	private List<Integer> usedIndex = new ArrayList<>();
	private ArrayList<Vector2> shortestPath = new ArrayList<>();

	public NearestNeighbour(ArrayList<GridTile> selectedTiles /* TspSimulation simulatie */ )
	{
		ArrayList<GridTile> tileOrder = new ArrayList<GridTile>();
		CoordList = selectedTiles;

		// SEARCH FOR FIRST TILE
		int currentIndex = findFirstTileOptimized();
		usedIndex.add(currentIndex);
		shortestPath.add(new Vector2(CoordList.get(currentIndex).getXcoord(), CoordList.get(currentIndex).getYcoord()));

		// LOOP THROUGH ITEMS
		for (int cnt = 0; cnt < (CoordList.size() - 1); cnt++)
		{
			currentIndex = findNext(currentIndex);
		}
	}

	private int findFirstTileOptimized(){
		double closestDot = 0;
		int index = 0;

		int cnt = 0;
		for (GridTile tile : CoordList)
		{
			if(closestDot == 0){
				closestDot = Math.hypot(tile.getXcoord(), tile.getYcoord());
				index = cnt;
			}else{
				double xyLength = Math.hypot(tile.getXcoord(), tile.getYcoord());
				if(xyLength < closestDot){
					closestDot = xyLength;
					index = cnt;
				}
			}
			cnt++;
		}

		return index;
	}

	private int findNext(int index)
	{
		int cnt = 0;
		Double[] shortestRoute =
		{ (double) 0, (double) 0 };
		// simulatie.addConsoleItem(" ==========| Starting Loop
		// |==========","DEBUG");

		for (GridTile tile : CoordList)
		{
			if (!usedIndex.contains(cnt) && cnt != index)
			{
				double xDiff = tile.getXcoord() - CoordList.get(index).getXcoord();
				double yDiff = tile.getYcoord() - CoordList.get(index).getYcoord();
				double xyDiff = Math.hypot(xDiff, yDiff);

				if (shortestRoute[1] == 0 || shortestRoute[1] > xyDiff)
				{
					shortestRoute[0] = (double) cnt;
					shortestRoute[1] = xyDiff;
				}
			}

			cnt++;
		}

		int FinalInt = (int) Math.round(shortestRoute[0]); // Line replaced
		usedIndex.add(FinalInt);
		shortestPath.add(new Vector2(CoordList.get(FinalInt).getXcoord(), CoordList.get(FinalInt).getYcoord()));
		return FinalInt;
	}

	public ArrayList<Vector2> getShortestPath()
	{
		return shortestPath;
	}
}
