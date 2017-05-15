package main.java.pane.simulation;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.java.algorithms.tsp.NearestNeighbour;
import main.java.constant.Constants;
import main.java.graphs.Grid;
import main.java.graphs.GridTile;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.MainMenu;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledLabel;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledRadioButton;

public class TspSimulation extends StyledPane
{

	public ListView<String> consoleList = new ListView<String>();
	public static TspSimulation tspSimulation;

	public TspSimulation()
	{
		// TODO
		int xPoint = 15;
		int yPoint = 15;

		tspSimulation = this;

		// back to menu button
		StyledButton goBackToMenu = new StyledButton("btn.backToMainMenu", Constants.backTMMBP, Constants.backTMMBS);
		goBackToMenu.setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});
		getChildren().add(goBackToMenu);

		Grid newGrid = new Grid(5, true);
		getChildren().add(newGrid);
		newGrid.setLayoutX(300);
		newGrid.setLayoutY(15);

		consoleList.setLayoutX(15);
		consoleList.setLayoutY(570);
		consoleList.setPrefWidth(825);
		consoleList.setPrefHeight(250);
		getChildren().add(consoleList);

		// RADIO BUTTONS
		StyledLabel lblChooseAlgorithm = new StyledLabel("lbl.algorithms", new Vector2(15, 105), 20);
		getChildren().add(lblChooseAlgorithm);

		// RADIOBUTTONS
		RadioButton chkAlgorithm1 = new StyledRadioButton("btn.nearestNeighbour", new Vector2(15, 140));
		getChildren().add(chkAlgorithm1);

		RadioButton chkAlgorithm2 = new StyledRadioButton("btn.multipleFragment", new Vector2(15, 165));
		getChildren().add(chkAlgorithm2);

		RadioButton chkAlgorithm3 = new StyledRadioButton("btn.totalEnumeration", new Vector2(15, 190));
		getChildren().add(chkAlgorithm3);

		RadioButton chkAlgorithm4 = new StyledRadioButton("btn.ownAlgorithm", new Vector2(15, 215));
		getChildren().add(chkAlgorithm4);

		ToggleGroup radioGroup = new ToggleGroup();
		chkAlgorithm1.setToggleGroup(radioGroup);
		chkAlgorithm2.setToggleGroup(radioGroup);
		chkAlgorithm3.setToggleGroup(radioGroup);
		chkAlgorithm4.setToggleGroup(radioGroup);

		// START + STOP BUTTON
		StyledButton startButton = new StyledButton("btn.play", new Vector2(15, 245), new Vector2(115, 30));
		startButton.setOnAction(event ->
		{
			consoleList.getItems().clear();
			addConsoleItem("Starting Algorithm 'nearest neighbour'", "DEBUG");
			addConsoleItem("Searching for Coordinates", "DEBUG");

			ArrayList<GridTile> activeTiles = newGrid.getSelectedTiles();
			if (activeTiles.size() > 0)
			{
				addConsoleItem(String.format("%s coordinates found, starting algorithm", activeTiles.size()), "DEBUG");
				NearestNeighbour algoritme = new NearestNeighbour(activeTiles);
			}
			else
			{
				addConsoleItem(String.format("%s coordinates found, algorithm has been cancelled", activeTiles.size()),
						"DEBUG");
			}

		});
		getChildren().add(startButton);

		StyledButton stopButton = new StyledButton("btn.stop", new Vector2(150, 245), new Vector2(115, 30));
		stopButton.setOnAction(event ->
		{

		});
		getChildren().add(stopButton);

		// PROGRESS BAR
		ProgressBar progression = new ProgressBar(0.6);
		progression.setLayoutX(15);
		progression.setLayoutY(295);
		progression.setPrefWidth(250);
		getChildren().add(progression);

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

		getChildren().add(new StyledLabel("lbl.lineColor", new Vector2(ScreenProperties.getScreenWidth() / 2, 20)));

		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setLayoutX(ScreenProperties.getScreenWidth() / 2 + 65);
		colorPicker.setLayoutY(17);
		colorPicker.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				newGrid.SetLineColor(colorPicker.getValue());
			}
		});

		getChildren().add(colorPicker);
	}

	public void addConsoleItem(String Message, String msgType)
	{
		consoleList.getItems().add(consoleList.getItems().size(), String.format("[%s] %s", msgType, Message));
	}

}
