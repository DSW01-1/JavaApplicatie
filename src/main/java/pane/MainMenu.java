package main.java.pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import main.java.main.Language;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;
import main.java.pane.simulation.BppSimulation;
import main.java.pane.simulation.RobotSimulation;
import main.java.pane.simulation.TspSimulation;

public class MainMenu extends StyledPane
{

	@Override
	public void InitPane()
	{
		CreateButtons();
	}

	private void CreateButtons()
	{
		setPrefSize(ScreenProperties.getScreenWidth(), ScreenProperties.getScreenHeight());
		setId("background");

		// New Order Button
		StyledButton newOrderButton = new StyledButton("btn.newOrder",
				new Vector2(getPrefWidth() / 2 - 100, getPrefHeight() / 4), new Vector2(200, 50));
		newOrderButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent aEvent)
			{
				Main.SwitchPane(new NewOrder());
			}
		});

		getChildren().add(newOrderButton);

		// Order History Button
		StyledButton orderHistoryButton = new StyledButton("btn.orderHistory",
				new Vector2(getPrefWidth() / 2 - 100, getPrefHeight() / 4 + 100), new Vector2(200, 50));
		orderHistoryButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent arg0)
			{
				// TODO
			}
		});
		getChildren().add(orderHistoryButton);

		// Simulations Button
		StyledButton simulationsButton = new StyledButton("btn.simulations",
				new Vector2(getPrefWidth() / 2 - 100, getPrefHeight() / 4 + 200), new Vector2(200, 50));
		simulationsButton.setOnAction(event ->
		{
			StyledPane[] tabArray =
			{ new TspSimulation(), new BppSimulation(), new RobotSimulation() };

			String[] nameArray =
			{ "TSP", "BPP", "Robot" };

			Main.SwitchPane(new BaseTabPane(nameArray, tabArray));
		});

		getChildren().add(simulationsButton);

		// Exit Button
		final StyledButton exitButton = new StyledButton("btn.exit",
				new Vector2(getPrefWidth() / 2 - 100, getPrefHeight() / 4 + 300), new Vector2(200, 50));
		exitButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent arg0)
			{
				// Exits the application
				Stage stage = (Stage) exitButton.getScene().getWindow();
				stage.close();
			}
		});
		getChildren().add(exitButton);
	}
}
