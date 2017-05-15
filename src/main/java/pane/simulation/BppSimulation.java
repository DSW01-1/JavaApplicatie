package main.java.pane.simulation;

import javafx.scene.control.TextField;
import main.java.main.ScreenProperties;
import main.java.pane.ConsolePane;
import main.java.pane.SimulationControls;

public class BppSimulation extends BaseSimulation
{
	ConsolePane consolePane;
	
	public BppSimulation()
	{
		super();
		AddControls();
		AddConsolePane();
		AddInputFields();
	}

	private void AddInputFields()
	{
		TextField boxSizeInput = new TextField();
		boxSizeInput.setLayoutX(ScreenProperties.getScreenWidth() / 2 - (ScreenProperties.getScreenWidth() / 3) / 2);
		boxSizeInput.setLayoutY(ScreenProperties.getScreenHeight() - 200);
		boxSizeInput.setPrefSize(40, 30);
		getChildren().add(boxSizeInput);
		
		TextField productsInput = new TextField();
		productsInput.setLayoutX(ScreenProperties.getScreenWidth() / 2 - (ScreenProperties.getScreenWidth() / 3) / 2);
		productsInput.setLayoutY(ScreenProperties.getScreenHeight() - 150);
		productsInput.setPrefSize(ScreenProperties.getScreenWidth() / 3, 30);
		getChildren().add(productsInput);
	}

	public void AddControls()
	{
		String[] algorithmNames =
		{ "btn.firstFit", "btn.nextFit", "btn.totalEnumeration", "btn.ownAlgorithm" };

		getChildren().add(new SimulationControls(algorithmNames, this));
	}
	
	private void AddConsolePane()
	{
		consolePane = new ConsolePane();
		getChildren().add(consolePane);
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
