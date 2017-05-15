package main.java.pane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import main.java.main.Vector2;
import main.java.pane.base.StyledLabel;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledRadioButton;
import main.java.pane.simulation.BaseSimulation;

public class SimulationControls extends BaseSimulation
{
	String[] algorithmNames;
	private String chkState;

	public SimulationControls(String[] algorithmNames)
	{
		this.algorithmNames = algorithmNames;

		CreateRadioButtons();
	}

	private void CreateRadioButtons()
	{
		StyledPane pane = new StyledPane();
		ToggleGroup radioGroup = new ToggleGroup();
		
		// Label algorithm
		StyledLabel lblChooseAlgorithm = new StyledLabel("lbl.algorithms", new Vector2(15, 105), 20);
		pane.getChildren().add(lblChooseAlgorithm);

		//Create all the algorithm checkboxes
		for (int i = 0; i < algorithmNames.length; i++)
		{
			RadioButton chkAlgorithm = new StyledRadioButton(algorithmNames[i], new Vector2(15, 140));
			chkAlgorithm.setUserData("Algorithm" + i);
			pane.getChildren().add(chkAlgorithm);
			chkAlgorithm.setToggleGroup(radioGroup);
		}

		
		radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
		{
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle)
			{
				if (radioGroup.getSelectedToggle() != null)
				{
					chkState = radioGroup.getSelectedToggle().getUserData().toString();
				}
			}
		});
	}
}
