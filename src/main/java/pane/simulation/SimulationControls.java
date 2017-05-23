package main.java.pane.simulation;

import javafx.scene.control.ToggleGroup;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledRadioButton;

public class SimulationControls extends StyledPane
{
	String[] algorithmNames;
	String chkState;
	BaseSimulation simulation;

	public SimulationControls(String[] algorithmNames, BaseSimulation simulation)
	{
		this.algorithmNames = algorithmNames;
		this.simulation = simulation;

		setLayoutX(0);
		setLayoutY(100);

		CreateRadioButtons();
		CreateControlButtons();
	}

	private void CreateRadioButtons()
	{
		ToggleGroup radioGroup = new ToggleGroup();

		for (int i = 1; i <= algorithmNames.length; i++)
		{
			StyledRadioButton chkAlgorithm = new StyledRadioButton(algorithmNames[i - 1], new Vector2(15, i * 25));
			chkAlgorithm.setUserData("Algorithm" + i);
			chkAlgorithm.setToggleGroup(radioGroup);

			getChildren().add(chkAlgorithm);
		}

		radioGroup.selectedToggleProperty().addListener(listener ->
		{
			if (radioGroup.getSelectedToggle() != null)
			{
				chkState = radioGroup.getSelectedToggle().getUserData().toString();
			}
		});
	}

	private void CreateControlButtons()
	{
		StyledButton startButton = new StyledButton("btn.start", new Vector2(15, 150), new Vector2(60, 20));
		startButton.setOnAction(event ->
		{
			CheckAndDoALgorithm();
		});
		getChildren().add(startButton);

		StyledButton pauseButton = new StyledButton("btn.pause", new Vector2(80, 150), new Vector2(60, 20));
		pauseButton.setOnAction(event ->
		{
			simulation.pauseAlgorithm();
		});
		getChildren().add(pauseButton);

		StyledButton stopButton = new StyledButton("btn.stop", new Vector2(145, 150), new Vector2(60, 20));
		stopButton.setOnAction(event ->
		{
			simulation.stopAlgorithm();
		});
		getChildren().add(stopButton);

	}

	private void CheckAndDoALgorithm()
	{
		if (chkState != null && chkState != "")
		{
			switch (chkState)
			{
			case "Algorithm1":
				simulation.ExecuteAlgorithmOne();
				break;
			case "Algorithm2":
				simulation.ExecuteAlgorithmTwo();
				break;
			case "Algorithm3":
				simulation.ExecuteAlgorithmThree();
				break;
			case "Algorithm4":
				simulation.ExecuteAlgorithmFour();
				break;
			default:
				break;
			}
		}
	}

}
