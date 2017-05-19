package main.java.algorithms.tsp;

import main.java.graphs.grid.DistanceGrid;
import main.java.graphs.grid.GridTile;
import main.java.main.Vector2;

import java.util.ArrayList;

public class HungarianAssignment
{
    private ArrayList<GridTile> coordList;                          //Punten lijst
    private ArrayList<Vector2> shortestPath = new ArrayList<>();    //Route om te tekenen
    private DistanceGrid dG;                                        //Distance Grid klasse
    public int[][] routeTSP;                                        // uitgerekende route's tussen punten
    private int berekend;                                           // aantal berkende route's
    public double[][] penaltyZero;                                  // penalty van zero tabel

    public HungarianAssignment(ArrayList<GridTile> selectedTiles)   //Constructor
    {
        coordList = selectedTiles;                                  //Zet de geselecteerde vakjes in een lokale arraylist
        dG = new DistanceGrid(coordList);                           //Nieuw DistanceGrid berekenen aan de hand van de coordinaten lijst
        routeTSP = new int[dG.distanceGrid.length][2];              //RouteTSP array net zo lang als het aantal punten
    }

    public double[] getColumn(double[][] grid, int colNumber) {     //Pakt de kolom van een 2D Array
        int rowNumber;
        double[] colArray = new double[grid.length];
        for (rowNumber = 0; rowNumber < grid.length; rowNumber++) {
            colArray[rowNumber] = grid[rowNumber][colNumber];       //Zet het kolomnummer in de kolom array
        }
        return colArray;
    }

    public double min(double[] numbers, boolean ignoreFirstZero) {  //Pakt het laagste getal in een rij, en krijgt te horen of hij de eerste nul moet negeren
        double lowest = 999999999;
        for (double num : numbers) {
            if (num < lowest && num != -999.0&&!(ignoreFirstZero&&num==0)) {
                lowest = num;
            }else if (ignoreFirstZero&&num==0){
                ignoreFirstZero=false;
            }
        }
        return lowest;
    }

    public void rowMin() {                                                      //Rij minimalisatie
        int rowCount = 0;
        for (double[] row : dG.distanceGrid) {
            int columnCount = 0;
            double lowest = min(row,false);
            for (double column : row) {
                if (column == -999.0) {
                } else {
                    dG.distanceGrid[rowCount][columnCount] = column - lowest;
                }
                columnCount++;
            }
            rowCount++;
        }
    }

    public void columnMin() {
        for (int colNumber = 0; colNumber < dG.distanceGrid.length; colNumber++) {
            double[] colArray = getColumn(dG.distanceGrid,colNumber);
            int rowNumber = 0;
            double lowest = min(colArray,false);
            for (double num : colArray) {
                if (num == -999.0) {
                } else {
                    dG.distanceGrid[rowNumber][colNumber] = num - lowest;
                }
                rowNumber++;
            }
        }
    }

    public void penaltyZero() {
        int rowNumber=0;
        double[][] zeroGrid=new double[dG.distanceGrid.length][dG.distanceGrid[0].length];

        for (double[] rowPlace : dG.distanceGrid) {
            int columnNumber=0;
            for (double columnPlace : rowPlace) {
                if (columnPlace == 0.0) {
                    zeroGrid[rowNumber][columnNumber]=min(dG.distanceGrid[rowNumber],true)+min(getColumn(dG.distanceGrid,columnNumber),true);
                }
                columnNumber++;
            }
            rowNumber++;
        }
        penaltyZero = zeroGrid;
    }

    public void reducegrid() {
        double[][] newDistanceGrid;
        int from=999;
        int to=999;
        double lowestNumber =999999;

        int rowNumber = 0;
        for (double[] rowPlace : dG.distanceGrid) {
            int columnNumber = 0;
            for (double columnPlace : rowPlace) {
                System.out.println(columnNumber);
                if(columnPlace==0.0&&penaltyZero[rowNumber][columnNumber]<lowestNumber){
                    lowestNumber=penaltyZero[rowNumber][columnNumber];
                    from=columnNumber;
                    to=rowNumber;
                }
                columnNumber++;
            }
            rowNumber++;
        }

        rowNumber = 0;
        newDistanceGrid= new double[dG.distanceGrid.length][dG.distanceGrid.length];
        for (double[] rowPlace : dG.distanceGrid) {
            int columnNumber = 0;
            for (double columnPlace : rowPlace) {
                if((columnNumber==from||rowNumber==to)||(columnNumber==to&&rowNumber==from)){
                    newDistanceGrid[rowNumber][columnNumber] = -999;
                    columnNumber++;
                }else {
                    newDistanceGrid[rowNumber][columnNumber] = columnPlace;
                    columnNumber++;
                }
            }
            rowNumber++;
        }
        //from is de index van het begin punt
        routeTSP[berekend][0]=from;
        routeTSP[berekend][1]=to;

        berekend++;
        dG.distanceGrid=newDistanceGrid;
    }

    public ArrayList<Vector2> runHungarian(){
        for(int i=0; i<dG.distanceGrid.length-1;i++){
            rowMin();
            columnMin();
            penaltyZero();
            reducegrid();
            printDGrid();
            System.out.println();
            System.out.println("----------------------------");
        }
        return shortestPath;
    }

    private void printDGrid() {
        for(double[] row:dG.distanceGrid){
            for (double column:row){
                System.out.print(column);
            }
            System.out.println();
        }
    }


}

