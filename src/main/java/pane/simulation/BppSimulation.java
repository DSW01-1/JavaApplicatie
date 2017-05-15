package main.java.pane.simulation;

import main.java.pane.SimulationControls;

public class BppSimulation extends BaseSimulation
{
	public BppSimulation()
	{
		super();
		AddControls();
	}

	public void AddControls()
	{
		String[] algorithmNames =
		{ "btn.firstFit", "btn.nextFit", "btn.totalEnumeration", "btn.ownAlgorithm" };

		getChildren().add(new SimulationControls(algorithmNames, this));
	}

	public void addConsoleItem(String string, String string2)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void ExecuteAlgorithmOne()
	{
	}

	@Override
	public void ExecuteAlgorithmTwo()
	{

	}

	@Override
	public void ExecuteAlgorithmThree()
	{

	}

	@Override
	public void ExecuteAlgorithmFour()
	{

	}
}
