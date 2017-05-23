package main.java.pane.simulation;

import main.java.pane.base.BackToMainMenuButton;
import main.java.pane.base.StyledPane;

public abstract class BaseSimulation extends StyledPane
{
	public BaseSimulation()
	{
		super();
		getChildren().add(new BackToMainMenuButton());
	}

	public abstract void ExecuteAlgorithmOne();

	public abstract void ExecuteAlgorithmTwo();

	public abstract void ExecuteAlgorithmThree();

	public abstract void ExecuteAlgorithmFour();

	public void pauseAlgorithm()
	{

	}

	public void stopAlgorithm()
	{

	}

	public void clearField()
	{
	}
}
