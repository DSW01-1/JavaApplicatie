package main.java.pane.simulation;

import main.java.constant.Constants;
import main.java.main.Main;
import main.java.main.product.Product;
import main.java.pane.MainMenu;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;

import java.util.ArrayList;

public abstract class BaseSimulation extends StyledPane
{
	public BaseSimulation()
	{
		super();

		// back to menu button
		StyledButton goBackToMenu = new StyledButton("btn.backToMainMenu", Constants.backTMMBP, Constants.backTMMBS);
		goBackToMenu.setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});
		getChildren().add(goBackToMenu);
	}

	public void ExecuteAlgorithmOne()
	{

	}

	public void ExecuteAlgorithmTwo()
	{

	}

	public void ExecuteAlgorithmThree()
	{

	}

	public void ExecuteAlgorithmFour()
	{

	}

	public void ExecuteAlgorithmFive()
	{

	}

	public void pauseAlgorithm()
	{

	}

	public void stopAlgorithm()
	{

	}

	public void clearField(){  }
}
