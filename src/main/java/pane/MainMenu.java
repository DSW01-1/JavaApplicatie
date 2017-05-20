package main.java.pane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import main.java.main.Language;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledChoiceBox;
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
		getStyleClass().add("background");
		// New Order Button
		StyledButton newOrderButton = new StyledButton("btn.newOrder",
				new Vector2((int) (getPrefWidth() / 2 - 100), (int) (getPrefHeight() / 4)), new Vector2(200, 50));
		newOrderButton.setOnAction(event ->
		{
			Main.SwitchPane(new NewOrder());
		});

		getChildren().add(newOrderButton);

		// Order History Button
		StyledButton orderHistoryButton = new StyledButton("btn.orderHistory",
				new Vector2((int) (getPrefWidth() / 2 - 100), (int) (getPrefHeight() / 4 + 100)), new Vector2(200, 50));
		orderHistoryButton.setOnAction(event ->
		{
			// TODO
		});
		getChildren().add(orderHistoryButton);

		// Simulations Button
		StyledButton simulationsButton = new StyledButton("btn.simulations",
				new Vector2((int) (getPrefWidth() / 2 - 100), (int) (getPrefHeight() / 4 + 200)), new Vector2(200, 50));
		simulationsButton.setOnAction(event ->
		{
			StyledPane[] tabArray =
			{ new TspSimulation(), new BppSimulation(), new RobotSimulation() };

			String[] nameArray =
			{ "TSP", "BPP", "Robot" };

			Main.SwitchPane(new BaseTabPane(nameArray, tabArray));
		});
		getChildren().add(simulationsButton);

		// Database Button
		StyledButton databaseButton = new StyledButton("btn.dtbsmgmnt",
				new Vector2((int) (getPrefWidth() / 2 - 100), (int) (getPrefHeight() / 4 + 300)), new Vector2(200, 50));
		databaseButton.setOnAction(event ->
		{
			Main.SwitchPane(new DatabaseManagePane());
		});
		getChildren().add(databaseButton);

		// Exit Button
		StyledButton exitButton = new StyledButton("btn.exit",
				new Vector2((int) (getPrefWidth() / 2 - 100), (int) (getPrefHeight() / 4 + 450)), new Vector2(200, 50));
		exitButton.setOnAction(event ->
		{
			// Exits the application
			Stage stage = (Stage) exitButton.getScene().getWindow();
			stage.close();
		});
		getChildren().add(exitButton);

		// Language Choice Box
		String[] languages =
		{ "Nederlands", "English", "Deutsch" };

		StyledChoiceBox choiceBox = new StyledChoiceBox(languages,
				new Vector2(ScreenProperties.getScreenWidth() - 100, 0));

		for (int i = 0; i < languages.length; i++)
		{
			if (languages[i].equals(Language.GetCurrentLanguage()))
			{
				choiceBox.getSelectionModel().select(languages[i]);
			}
		}
		choiceBox.setPrefWidth(100);
		choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2)
			{
				Language.ChangeLanguage(choiceBox.getItems().get((Integer) number2));
				Main.SwitchPane(new MainMenu());
			}
		});
		getChildren().add(choiceBox);
	}
}
