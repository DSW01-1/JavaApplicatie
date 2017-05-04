package pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Language;
import main.ScreenProperties;
import main.Vector2;

public class MainMenu extends Pane
{
	public MainMenu()
	{
		CreateButtons();
	}

	private void CreateButtons()
	{
		setPrefSize(ScreenProperties.getScreenWidth(), ScreenProperties.getScreenHeight());
		setStyle("-fx-background-color: #0F0");

		// New Order
		StyledButton newOrderButton = new StyledButton(Language.getTranslation("newOrderButton"),
				new Vector2(getPrefWidth() / 2 - 100, getPrefHeight() / 4), new Vector2(200, 50));
		newOrderButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent aEvent)
			{
				System.out.println("test");
			}
		});
		getChildren().add(newOrderButton);

		// Order History
		StyledButton orderHistoryButton = new StyledButton(Language.getTranslation("orderHistoryButton"),
				new Vector2(getPrefWidth() / 2 - 100, getPrefHeight() / 4 + 100), new Vector2(200, 50));
		newOrderButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent arg0)
			{

			}
		});
		getChildren().add(orderHistoryButton);

		// Simulations
		StyledButton simulationsButton = new StyledButton(Language.getTranslation("simulationsButton"),
				new Vector2(getPrefWidth() / 2 - 100, getPrefHeight() / 4 + 200), new Vector2(200, 50));
		newOrderButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent arg0)
			{

			}
		});
		getChildren().add(simulationsButton);

		// Exit
		final StyledButton exitButton = new StyledButton(Language.getTranslation("exitButton"),
				new Vector2(getPrefWidth() / 2 - 100, getPrefHeight() / 4 + 300), new Vector2(200, 50));
		newOrderButton.setOnAction(new EventHandler<ActionEvent>()
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
