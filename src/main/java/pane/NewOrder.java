package main.java.pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.java.handler.JsonHandler;
import main.java.main.Language;
import main.java.main.Main;
import main.java.main.Vector2;

public class NewOrder extends Pane
{
	public NewOrder()
	{
		super();
		setId("background");

		StyledButton btnBackToMainMenu = new StyledButton(Language.getTranslation("btn.backToMainMenu"),
				new Vector2(10, 10));
		btnBackToMainMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent aEvent)
			{
				Main.SwitchPane(new MainMenu());
			}
		});
		getChildren().add(btnBackToMainMenu);

		//TODO create columns
		//CreateLeftColumn();
		//CreateRightColumn();
	}

	private void CreateLeftColumn()
	{
		VBox vb = new VBox();

		Product[] productArray = JsonHandler.GetAvailableProducts();

		for (int i = 0; i < productArray.length; i++)
		{
			vb.getChildren().add(productArray[i]);
		}

		ScrollPane scrollPane = new ScrollPane();
	}

	private void CreateRightColumn()
	{
		ScrollPane scrollPane = new ScrollPane();
	}
}
