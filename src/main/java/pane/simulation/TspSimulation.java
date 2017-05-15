package main.java.pane.simulation;

import java.util.ArrayList;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.java.algorithms.tsp.NearestNeighbour;
import main.java.constant.Constants;
import main.java.graphs.Grid;
import main.java.graphs.GridTile;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.ConsolePane;
import main.java.pane.MainMenu;
import main.java.pane.SimulationControls;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledLabel;
import main.java.pane.base.StyledPane;

public class TspSimulation extends StyledPane
{
	private Grid newGrid;

	ConsolePane consolePane;

	public TspSimulation()
	{
		consolePane = new ConsolePane();
		getChildren().add(consolePane);
		// back to menu button
		StyledButton goBackToMenu = new StyledButton("btn.backToMainMenu", Constants.backTMMBP, Constants.backTMMBS);
		goBackToMenu.setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});
		getChildren().add(goBackToMenu);

		CreateGridPane();
		CreateAlgorithmControls();
	}

	public void CreateGridPane()
	{
		newGrid = new Grid(5, true);
		newGrid.setLayoutX(ScreenProperties.getScreenWidth() - Constants.gridSize - 15);
		newGrid.setLayoutY(ScreenProperties.getScreenHeight() - Constants.gridSize - 150);
		getChildren().add(newGrid);

		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setLayoutX(ScreenProperties.getScreenWidth() / 2 + 65);
		colorPicker.setLayoutY(17);
		colorPicker.setOnAction(event ->
		{
			newGrid.SetLineColor(colorPicker.getValue());
		});

		getChildren().add(colorPicker);
		getChildren().add(new StyledLabel("lbl.lineColor", new Vector2(ScreenProperties.getScreenWidth() / 2, 20)));
	}

	public void CreateAlgorithmControls()
	{
		StyledPane pane = new StyledPane();

		String[] algorithmNames =
		{ "btn.nearestNeighbour", "btn.multipleFragment", "btn.totalEnumeration", "btn.ownAlgorithm" };

		Runnable[] controlButtonCode = new Runnable[3];

		controlButtonCode[0] = () ->
		{
			consolePane.getItems().clear();
			consolePane.addConsoleItem("Starting Algorithm 'nearest neighbour'", "DEBUG");
			consolePane.addConsoleItem("Searching for Coordinates", "DEBUG");

			ArrayList<GridTile> activeTiles = newGrid.getSelectedTiles();
			if (activeTiles.size() > 0)
			{
				consolePane.addConsoleItem(
						String.format("%s coordinates found, starting algorithm", activeTiles.size()), "DEBUG");
				long startTime = System.nanoTime();
				NearestNeighbour algoritme = new NearestNeighbour(activeTiles);
				ArrayList<Vector2> shortestPath = algoritme.getShortestPath();
				long stopTime = System.nanoTime();
				consolePane.addConsoleItem("==========| Kortste pad gevonden |==========", "INFO");
				String coordinates = "Coordinates: ";
				for (Vector2 coordinate : shortestPath)
				{
					coordinates += String.format("(%s, %s) ", coordinate.getX(), coordinate.getY());
					// addConsoleItem(String.format("Tile: x=%s,
					// y=%s",coordinate.getX(), coordinate.getY()),"INFO");
				}
				consolePane.addConsoleItem(coordinates, "INFO");
				long duration = (stopTime - startTime) / 100000;
				String showDuration = (duration < 1) ? "duration: less then a ms" : "duration: " + duration + " ms";
				consolePane.addConsoleItem(showDuration, "INFO");
				newGrid.Redraw(shortestPath);
			}
			else
			{
				consolePane.addConsoleItem(
						String.format("%s coordinates found, algorithm has been cancelled", activeTiles.size()),
						"DEBUG");
			}
		};

		getChildren().add(new SimulationControls(algorithmNames, controlButtonCode));

		// RESULTS
		StyledLabel lblResults = new StyledLabel("btn.results", new Vector2(15, 340), 20);
		getChildren().add(lblResults);

		Rectangle rectResults = new Rectangle(15, 370, 250, 130);
		rectResults.setFill(Color.TRANSPARENT);
		rectResults.setStroke(Color.BLACK);
		rectResults.setArcHeight(6);
		rectResults.setArcWidth(6);
		getChildren().add(rectResults);

		// RESULT LABELS
		int[] resultY = new int[]
		{ 380, 410, 440, 470 };
		String[] resultNames = new String[]
		{ "btn.nearestNeighbour", "btn.multipleFragment", "btn.totalEnumeration", "btn.ownAlgorithm" };

		for (int cnt = 0; cnt < 4; cnt++)
		{
			StyledLabel lblResAlg = new StyledLabel(resultNames[cnt], new Vector2(23, resultY[cnt]));
			getChildren().add(lblResAlg);
		}

		// RESULTS IN MS
		for (int i = 0; i < 4; i++)
		{
			Label label = new Label("1 ms");
			label.setLayoutX(210);
			label.setLayoutY(380 + (i * 30));
			getChildren().add(label);
		}

		getChildren().add(pane);
	}

}
