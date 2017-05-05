package main.java.pane;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.java.database.DatabaseConnection;
import main.java.main.Language;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledScrollPane;

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

		CreateLeftColumn();
		CreateRightColumn();
	}

	private void CreateLeftColumn()
	{
		VBox vb = new VBox();
		vb.setMaxWidth(200);

		ArrayList<String> productList = DatabaseConnection.GetAvailableProducts();

		if (productList.size() > 0)
		{
			for (int i = 0; i < productList.size(); i++)
			{
				vb.getChildren().add(new Product(productList.get(i)));
			}
			getChildren().add(new StyledScrollPane(vb, new Vector2(200, 400),
					new Vector2(ScreenProperties.getScreenWidth() / 4, ScreenProperties.getScreenHeight() / 3 - 200)));
		}
	}

	private void CreateRightColumn()
	{
		VBox vb = new VBox();
		vb.setMaxWidth(200);

		getChildren().add(new StyledScrollPane(vb, new Vector2(200, 400), new Vector2(
				ScreenProperties.getScreenWidth() / 4 * 3 - 200, ScreenProperties.getScreenHeight() / 3 - 200)));

	}
}
