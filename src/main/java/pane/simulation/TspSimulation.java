package main.java.pane.simulation;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import main.java.algorithms.tsp.NearestNeighbour;
import main.java.graphs.Grid;
import main.java.graphs.GridTile;
import main.java.main.Language;
import main.java.main.Main;
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
		int xPoint = 15;
		int yPoint = 15;

		tspSimulation = this;

		// back to menu button
		StyledButton goBackToMenu = new StyledButton("Go back to menu", new Vector2(15, 15), new Vector2(250, 50));
		goBackToMenu.setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});

		getChildren().add(goBackToMenu);

		Grid newGrid = new Grid(true);
		getChildren().add(newGrid);
		newGrid.setLayoutX(300);
		newGrid.setLayoutY(15);

		consoleList.setLayoutX(15);
		consoleList.setLayoutY(570);
		consoleList.setPrefWidth(825);
		consoleList.setPrefHeight(250);
		getChildren().add(consoleList);

		// RADIO BUTTONS
		Label lblChooseAlgorithm = new Label("Algoritmes");
		lblChooseAlgorithm.setLayoutX(15);
		lblChooseAlgorithm.setLayoutY(105);
		lblChooseAlgorithm.setFont(Font.font("Century Gothic", 20));
		getChildren().add(lblChooseAlgorithm);

		// RADIOBUTTONS
		RadioButton chkAlgorithm1 = new StyledRadioButton(Language.getTranslation("btn.nearestNeighbour"),
				new Vector2(15, 140));
		getChildren().add(chkAlgorithm1);

		RadioButton chkAlgorithm2 = new StyledRadioButton(Language.getTranslation("btn.multipleFragment"),
				new Vector2(15, 165));
		getChildren().add(chkAlgorithm2);

		RadioButton chkAlgorithm3 = new StyledRadioButton(Language.getTranslation("btn.totalEnumeration"),
				new Vector2(15, 190));
		getChildren().add(chkAlgorithm3);

		RadioButton chkAlgorithm4 = new StyledRadioButton(Language.getTranslation("btn.ownAlgorithm"),
				new Vector2(15, 215));
		getChildren().add(chkAlgorithm4);

		ToggleGroup radioGroup = new ToggleGroup();
		chkAlgorithm1.setToggleGroup(radioGroup);
		chkAlgorithm2.setToggleGroup(radioGroup);
		chkAlgorithm3.setToggleGroup(radioGroup);
		chkAlgorithm4.setToggleGroup(radioGroup);

		// START + STOP BUTTON
		StyledButton startButton = new StyledButton("Play", new Vector2(15, 245), new Vector2(115, 30));
		startButton.setOnAction(event ->
		{
			consoleList.getItems().clear();
			addConsoleItem("Starting Algorithm 'nearest neighbour'", "DEBUG");
			addConsoleItem("Searching for Coordinates", "DEBUG");

			ArrayList<GridTile> activeTiles = newGrid.getSelectedTiles();
			if (activeTiles.size() > 0)
			{
				addConsoleItem(String.format("%s coordinates found, starting algorithm", activeTiles.size()), "DEBUG");
				NearestNeighbour algoritme = new NearestNeighbour(activeTiles, this);
			}
			else
			{
				addConsoleItem(String.format("%s coordinates found, algorithm has been cancelled", activeTiles.size()),
						"DEBUG");
			}

		});
		getChildren().add(startButton);

		StyledButton stopButton = new StyledButton(Language.getTranslation("btn.stop"), new Vector2(150, 245),
				new Vector2(115, 30));
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
		StyledLabel lblResults = new StyledLabel(Language.getTranslation("btn.results"), new Vector2(15, 340));
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
		{ "Nearest Neighbour", "Multiple Fragment", "Volledige Enumeratie", "Eigen Algoritme" };

		for (int cnt = 0; cnt < 4; cnt++)
		{
			StyledLabel lblResAlg = new StyledLabel(resultNames[cnt], new Vector2(23, resultY[cnt]));
			getChildren().add(lblResAlg);
		}

		// RESULTS IN MS
		StyledLabel lblResAlg1 = new StyledLabel("1 ms ", new Vector2(210, 380));
		getChildren().add(lblResAlg1);

		StyledLabel lblResAlg2 = new StyledLabel("3 ms ", new Vector2(210, 410));
		getChildren().add(lblResAlg2);

		StyledLabel lblResAlg3 = new StyledLabel("2.5 ms ", new Vector2(210, 440));
		getChildren().add(lblResAlg3);

		StyledLabel lblResAlg4 = new StyledLabel("10 ms", new Vector2(210, 470));
		getChildren().add(lblResAlg4);

	}

	public void addConsoleItem(String Message, String msgType)
	{
		consoleList.getItems().add(consoleList.getItems().size(), String.format("[%s] %s", msgType, Message));
	}

}
