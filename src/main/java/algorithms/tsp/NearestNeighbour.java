package main.java.algorithms.tsp;

import java.util.ArrayList;
import java.util.List;

import main.java.constant.Constants;
import main.java.graphs.GridTile;
import main.java.main.Vector2;
import main.java.pane.simulation.TspSimulation;

public class NearestNeighbour
{
	ArrayList<GridTile> tileOrder;
	ArrayList<GridTile> CoordList;
	TspSimulation simulatie;
	private List<Integer> usedIndex = new ArrayList<>();
    private ArrayList<Vector2> shortestPath = new ArrayList<>();

	public NearestNeighbour(ArrayList<GridTile> selectedTiles /*TspSimulation simulatie */ )
	{
		// DEFINING ARRAYLISTS
		tileOrder = new ArrayList<GridTile>();
		CoordList = selectedTiles;
		this.simulatie = simulatie;


		// SEARCH FOR FIRST TILE
		//simulatie.addConsoleItem("(Step 1/3) =====| LOCATING FIRST |=====", "INFO");
		int currentIndex = findFirstTile();
		usedIndex.add(currentIndex);
        shortestPath.add(new Vector2(CoordList.get(currentIndex).getXcoord(), CoordList.get(currentIndex).getYcoord()));


        // LOOP THROUGH ITEMS
		//simulatie.addConsoleItem("(Step 2/3) =====| DEFINING PATH |=====", "INFO");
		for(int cnt = 0; cnt < (CoordList.size() - 1); cnt++) {
			simulatie.addConsoleItem(String.format("Working on tile %s/%s",cnt+1,CoordList.size()), "DEBUG");
			currentIndex = findNext(currentIndex);
		}
	}



    private int findFirstTile(){
        int index = 0;
        int x = 0;
        int y = 0;
        boolean found = false;
        int step = 0;

        while (!found) {

        	// show location
			//simulatie.addConsoleItem(String.format("Current location: (%s, %s)", x, y), "DEBUG");

			int i = 0;
			for (GridTile tile : CoordList) {
				boolean xOk = (tile.getXcoord() == x);
				boolean yOk = (tile.getYcoord() == y);

				if (xOk && yOk) {
					found = true;
					//simulatie.addConsoleItem(String.format("Tile %s is correct!, moving to the next step", i), "INFO");
					index = i;
					break;
				}
				i++;
			}

            /* ===== LOOP 1 (old one) ===== */
			if (!found) {
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

	private int findNext(int index){
		//List<Double[]> pathLength = new ArrayList<Double[]>();
		int cnt = 0;
		Double[] shortestRoute = {(double)0,(double)0};
        //simulatie.addConsoleItem("    ==========| Starting Loop |==========","DEBUG");

		for(GridTile tile : CoordList){
			if(!usedIndex.contains(cnt) && cnt != index){
				double xDiff = tile.getXcoord() - CoordList.get(index).getXcoord();
				double yDiff = tile.getYcoord() - CoordList.get(index).getYcoord();
				double xyDiff = Math.hypot(xDiff, yDiff);
                //simulatie.addConsoleItem("    ==========| Moving to the next Tile |==========","DEBUG");
				//simulatie.addConsoleItem(String.format("  Tile %s X = %s, Y = %s", index+1, CoordList.get(index).getXcoord(), CoordList.get(index).getYcoord()), "DEBUG");
				//simulatie.addConsoleItem(String.format("  Tile %s X = %s, Y = %s", cnt+1, tile.getXcoord(), tile.getYcoord()), "DEBUG");
				//simulatie.addConsoleItem(String.format("  Difference between Tile %s and Tile %s: X = %s, Y = %s, Combined = %s", index+1, cnt+1, xDiff, yDiff, xyDiff), "DEBUG");

				if(shortestRoute[1] == 0 || shortestRoute[1] > xyDiff){
					shortestRoute[0] = (double)cnt;
					shortestRoute[1] = xyDiff;
					//simulatie.addConsoleItem("  This tile has been determined as the new shortest route", "DEBUG");
				}
			}

			cnt++;
		}

        int FinalInt = (int) Math.round(shortestRoute[0]); // Line replaced
		usedIndex.add( FinalInt);
		shortestPath.add(new Vector2(CoordList.get(FinalInt).getXcoord(), CoordList.get(FinalInt).getYcoord()));
		return FinalInt;
	}

	public ArrayList<Vector2> getShortestPath(){
	    return shortestPath;
    }
}
