package main.java.algorithms.tsp;

import java.util.ArrayList;

import main.java.graphs.DistanceGrid;
import main.java.graphs.grid.GridTile;
import main.java.main.Vector2;

//Hungarian not functional AS OF YET

public class HungarianAssignment
{
	// Punten lijst
	private ArrayList<GridTile> coordList;
	// Route om te tekenen
	private ArrayList<Vector2> shortestPath = new ArrayList<>();
	// Distance Grid klasse
	private DistanceGrid dG;
	// uitgerekende route's tussen punten
	public int[][][] routeTSP;
	// aantal berkende route's
	private int berekend;
	// penalty van zero tabel
	public double[][] penaltyZero;

	public HungarianAssignment(ArrayList<GridTile> selectedTiles)
	{
		// Zet de geselecteerde vakjes in een lokale arraylist
		coordList = selectedTiles;
		// Nieuw DistanceGrid berekenen aan de hand van de coordinaten lijst
		dG = new DistanceGrid(coordList);
		// RouteTSP array net zo lang als het aantal punten
		routeTSP = new int[dG.distanceGrid.length][2][2];
	}

	public double[] getColumn(double[][] grid, int colNumber)
	{
		// Pakt de kolom van een 2D Array
		int rowNumber;
		double[] colArray = new double[grid.length];
		for (rowNumber = 0; rowNumber < grid.length; rowNumber++)
		{
			// Zet het kolomnummer in de kolom array
			colArray[rowNumber] = grid[rowNumber][colNumber];
		}
		return colArray;
	}

	public double min(double[] numbers, boolean ignoreFirstZero)
	{
		// Pakt het laagste getal in een rij, en krijgt te horen of hij de
		// eerste nul moet negeren
		double lowest = 999999999;
		for (double num : numbers)
		{
			if (num < lowest && num != -999.0 && !(ignoreFirstZero && num == 0))
			{
				lowest = num;
			}
			else if (ignoreFirstZero && num == 0)
			{
				ignoreFirstZero = false;
			}
		}
		return lowest;
	}

	public void rowMin()
	{
		// Rij minimalisatie
		int rowCount = 0;
		for (double[] row : dG.distanceGrid)
		{
			int columnCount = 0;
			double lowest = min(row, false);
			for (double column : row)
			{
				if (column == -999.0)
				{
					// TODO Wat als column -999.0 is?
				}
				else
				{
					dG.distanceGrid[rowCount][columnCount] = column - lowest;
				}
				columnCount++;
			}
			rowCount++;
		}
	}

	public void columnMin()
	{
		for (int colNumber = 0; colNumber < dG.distanceGrid.length; colNumber++)
		{
			double[] colArray = getColumn(dG.distanceGrid, colNumber);
			int rowNumber = 0;
			double lowest = min(colArray, false);
			for (double num : colArray)
			{
				if (num == -999.0)
				{
					// TODO Wat als column -999.0 is?
				}
				else
				{
					dG.distanceGrid[rowNumber][colNumber] = num - lowest;
				}
				rowNumber++;
			}
		}
	}

	public void penaltyZero()
	{
		int rowNumber = 0;
		double[][] zeroGrid = new double[dG.distanceGrid.length][dG.distanceGrid[0].length];

		for (double[] rowPlace : dG.distanceGrid)
		{
			int columnNumber = 0;
			for (double columnPlace : rowPlace)
			{
				if (columnPlace == 0.0)
				{
					zeroGrid[rowNumber][columnNumber] = min(dG.distanceGrid[rowNumber], true)
							+ min(getColumn(dG.distanceGrid, columnNumber), true);
				}
				columnNumber++;
			}
			rowNumber++;
		}
		penaltyZero = zeroGrid;
	}

	public void reducegrid()
	{
		double[][] newDistanceGrid;
		int from = 999;
		int to = 999;
		double lowestNumber = 999999;

		int rowNumber = 0;
		for (double[] rowPlace : dG.distanceGrid)
		{
			int columnNumber = 0;
			for (double columnPlace : rowPlace)
			{
				// TODO Geen System.out.println! alleen voor testen
				if (columnPlace == 0.0 && penaltyZero[rowNumber][columnNumber] < lowestNumber)
				{
					lowestNumber = penaltyZero[rowNumber][columnNumber];
					from = columnNumber;
					to = rowNumber;
				}
				columnNumber++;
			}
			rowNumber++;
		}

		rowNumber = 0;
		newDistanceGrid = new double[dG.distanceGrid.length][dG.distanceGrid.length];
		for (double[] rowPlace : dG.distanceGrid)
		{
			int columnNumber = 0;
			for (double columnPlace : rowPlace)
			{
				if ((columnNumber == from || rowNumber == to) || (columnNumber == to && rowNumber == from))
				{
					newDistanceGrid[rowNumber][columnNumber] = -999;
					columnNumber++;
				}
				else
				{
					newDistanceGrid[rowNumber][columnNumber] = columnPlace;
					columnNumber++;
				}
			}
			rowNumber++;
		}
		// from is de index van het begin punt
		if(from==999||to==999) {}else {
			routeTSP[berekend][0][0] = coordList.get(from).getXcoord();
			routeTSP[berekend][0][1] = coordList.get(from).getYcoord();
			routeTSP[berekend][1][0] = coordList.get(to).getXcoord();
			routeTSP[berekend][1][1] = coordList.get(to).getYcoord();
		}
		if(from==999||to==999) {
		}else{
			shortestPath.add(new Vector2(coordList.get(from).getXcoord(),coordList.get(from).getYcoord()));
			shortestPath.add(new Vector2(coordList.get(to).getXcoord(), coordList.get(to).getYcoord()));
		}
		berekend++;
		dG.distanceGrid = newDistanceGrid;
	}

	public ArrayList<Vector2> runHungarian()
	{
		for (int i = 0; i < dG.distanceGrid.length; i++)
		{
			rowMin();
			columnMin();
			penaltyZero();
			reducegrid();
			printPath();
			printDGrid();
			System.out.println();
			System.out.println("----------------------------");
		}
		System.out.println(shortestPath.toString());
		return shortestPath;
	}

	private void printDGrid()
	{
		for (double[] row : dG.distanceGrid)
		{
			for (double column : row)
			{
				// TODO Geen System.out.println! alleen voor testen
				System.out.print(column);
			}
			System.out.println();
		}
	}

	private void printPath(){
		int puntC=0;
		for(int[][] punt:routeTSP){
			puntC++;
			int fromToC=0;
			for(int[] fromTo:punt){
				fromToC++;
				int xyC = 0;
				for(int xy: fromTo) {
					xyC++;
					System.out.println(String.format("%s | %s | %s",puntC,fromToC,xy));
				}
			}
		}
	}
}
