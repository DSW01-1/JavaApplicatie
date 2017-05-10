package main.java.algorithms.tsp;

import main.java.constant.Constants;
import main.java.graphs.GridTile;
import main.java.main.Vector2;
import main.java.pane.simulation.TspSimulation;

import java.util.ArrayList;

public class NearestNeighbour
{
    ArrayList<GridTile> tileOrder;
    ArrayList<GridTile> CoordList;
    TspSimulation simulatie;

    public NearestNeighbour(ArrayList<GridTile> selectedTiles, TspSimulation simulatie){

        // DEFINING ARRAYLISTS
        tileOrder = new ArrayList<GridTile>();
        CoordList = selectedTiles;
        this.simulatie = simulatie;


        // SEARCH FOR FIRST TILE
        int indexFirstTile = findFirstTile();

    }

    private int findFirstTile(){
        int index = 0;
        int x = 0;
        int y = 0;
        boolean found = false;

        simulatie.addConsoleItem("Searching for first coordinate", "DEBUG");
        simulatie.addConsoleItem("Moving to first coordinate (0, 0)", "DEBUG");

        while (!found){
            int i = 0;
            for(GridTile tile : CoordList){
                boolean xOk = (tile.getXcoord() == x);
                boolean yOk = (tile.getYcoord() == y);

                System.out.printf("%s - %s \n", tile.getXcoord(), tile.getYcoord());

                if(xOk && yOk){
                    found = true;
                    simulatie.addConsoleItem(String.format("Coordinate %s is correct!, moving to the next step",i), "DEBUG");
                    //index = i;
                    break;
                }else{
                    simulatie.addConsoleItem(String.format("Coordinate %s is incorrect!, moving on",i), "DEBUG");
                }
                i++;
            }
            if(!found){
                if(x == y && x < Constants.gridSize){
                    x++;
                    simulatie.addConsoleItem(String.format("Moving to new Coordinate, (%s, %s)", x, y), "DEBUG");
                }else if(y < Constants.gridSize){
                    y++;
                    simulatie.addConsoleItem(String.format("Moving to new Coordinate, (%s, %s)", x, y), "DEBUG");
                }else{
                    simulatie.addConsoleItem("Coordinate not found", "DEBUG");
                    found = true;
                }
            }

        }

        return index;
    }
}
