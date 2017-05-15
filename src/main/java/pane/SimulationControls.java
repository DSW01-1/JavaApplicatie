package main.java.pane;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import main.java.graphs.Grid;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledLabel;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledRadioButton;

public class SimulationControls extends StyledPane
{
	String[] algorithmNames;
	Grid grid;
	Runnable[] codeToRun;

	public SimulationControls(String[] algorithmNames, Runnable[] codeToRun)
	{
		super();

		this.algorithmNames = algorithmNames;

		CreateRadioButtons();
		CreateAlgorithmControls();
	}

	private void CreateRadioButtons()
	{
		ToggleGroup radioGroup = new ToggleGroup();

		// RADIO BUTTONS
		StyledLabel lblChooseAlgorithm = new StyledLabel("lbl.algorithms", new Vector2(15, 105), 20);
		getChildren().add(lblChooseAlgorithm);

		for (int i = 0; i < algorithmNames.length; i++)
		{
			RadioButton chkAlgorithm = new StyledRadioButton(algorithmNames[i], new Vector2(15, 140 + (i * 25)));
			getChildren().add(chkAlgorithm);
			chkAlgorithm.setToggleGroup(radioGroup);
		}
	}

	private void CreateAlgorithmControls()
	{
		StyledButton startButton = new StyledButton("btn.play", new Vector2(15, 245), new Vector2(115, 30));
		startButton.setOnAction(event ->
		{
			codeToRun[0].run();
		});
		getChildren().add(startButton);

		StyledButton pauzeButton = new StyledButton("btn.pause", new Vector2(15, 245), new Vector2(115, 30));
		pauzeButton.setOnAction(event ->
		{
			codeToRun[1].run();
		});
		getChildren().add(pauzeButton);

		StyledButton stopButton = new StyledButton("btn.stop", new Vector2(150, 245), new Vector2(115, 30));
		stopButton.setOnAction(event ->
		{
			codeToRun[2].run();
		});
		getChildren().add(stopButton);
	}

}
