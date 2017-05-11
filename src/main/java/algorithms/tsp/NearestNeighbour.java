package main.java.algorithms.tsp;

import java.util.ArrayList;
import java.util.List;

import main.java.constant.Constants;
import main.java.graphs.GridTile;
import main.java.pane.simulation.TspSimulation;

public class NearestNeighbour
{
	ArrayList<GridTile> tileOrder;
	ArrayList<GridTile> CoordList;
	TspSimulation simulatie;

	public NearestNeighbour(ArrayList<GridTile> selectedTiles, TspSimulation simulatie)
	{
		// DEFINING ARRAYLISTS
		tileOrder = new ArrayList<GridTile>();
		CoordList = selectedTiles;
		this.simulatie = simulatie;
		List<Integer> usedIndex = new ArrayList<>();


		// SEARCH FOR FIRST TILE
		int indexFirstTile = findFirstTile();
		usedIndex.add(indexFirstTile);

		


	}



    private int findFirstTile(){
        int index = 0;
        int x = 0;
        int y = 0;
        boolean found = false;
        int step = 0;

		simulatie.addConsoleItem("Searching for first coordinate", "DEBUG");
		simulatie.addConsoleItem("Moving to first coordinate (0, 0)", "DEBUG");


        while (!found) {
			//String cnt = (i < 9) ? String.format("0%d",i+1) : String.format("%d",i+1) ;
			//    simulatie.addConsoleItem(String.format("=====| NEXT COORDINATE (x: %s ,y: %s)",x,y), "DEBUG");



            /* ===== COORDLIST LOOP 1 ===== */
			int i = 0;
			for (GridTile tile : CoordList) {
				boolean xOk = (tile.getXcoord() == x);
				boolean yOk = (tile.getYcoord() == y);

				System.out.printf("%s - %s \n", tile.getXcoord(), tile.getYcoord());

				if (xOk && yOk) {
					found = true;
					simulatie.addConsoleItem(String.format("Tile %s is correct!, moving to the next step", i), "DEBUG");
					index = i;
					break;
				} else {
					simulatie.addConsoleItem(String.format("Tile %s is incorrect!,", i), "DEBUG");
				}
				i++;
			}

            /* ===== LOOP 1 (old one) ===== */
			if (!found) {
				simulatie.addConsoleItem(String.format("Current location: (%s, %s)", x, y), "DEBUG");
				if (step < (Constants.gridSize * 2)) {
					if (y > 0 && x < (Constants.gridSize - 1)) {
						y--;
						x++;
					} else {
						if (step < (Constants.gridSize - 1)) {  // 1e 5 stappen
							step++;
							y = step;//Constants.gridSize - 1;
							x = 0;//step - (Constants.gridSize - 1);
						} else {  // 2e 5 stappen
							step++;
							y = (Constants.gridSize - 1);
							x = step - (Constants.gridSize - 1);
						}
					}
				} else {
					found = true;
				}
			}
		}
		return index;
	}
}
