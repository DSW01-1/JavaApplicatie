package main.java.pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.main.Language;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;

public class MainMenu extends Pane
{
	public MainMenu()
	{
		CreateButtons();
	}

	private void CreateButtons()
	{
		setPrefSize(ScreenProperties.getScreenWidth(), ScreenProperties.getScreenHeight());
		setId("background");

		// New Order
		StyledButton newOrderButton = new StyledButton(Language.getTranslation("btn.newOrder"),
				new Vector2(getPrefWidth() / 2 - 100, getPrefHeight() / 4), new Vector2(200, 50));
		newOrderButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent aEvent)
			{
				Main.SwitchPane(new NewOrder());
			}
		});
		getChildren().add(newOrderButton);

		// Order History
		StyledButton orderHistoryButton = new StyledButton(Language.getTranslation("btn.orderHistory"),
				new Vector2(getPrefWidth() / 2 - 100, getPrefHeight() / 4 + 100), new Vector2(200, 50));
		orderHistoryButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent arg0)
			{

			}
		});
		getChildren().add(orderHistoryButton);

		// Simulations
		StyledButton simulationsButton = new StyledButton(Language.getTranslation("btn.simulations"),
				new Vector2(getPrefWidth() / 2 - 100, getPrefHeight() / 4 + 200), new Vector2(200, 50));
		simulationsButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent arg0)
			{

			}
		});
		getChildren().add(simulationsButton);

		// Exit
		final StyledButton exitButton = new StyledButton(Language.getTranslation("btn.exit"),
				new Vector2(getPrefWidth() / 2 - 100, getPrefHeight() / 4 + 300), new Vector2(200, 50));
		exitButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent arg0)
			{
				Stage stage = (Stage) exitButton.getScene().getWindow();
				stage.close();
			}
		});
		getChildren().add(exitButton);
	}
}
