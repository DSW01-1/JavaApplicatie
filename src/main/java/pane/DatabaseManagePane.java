package main.java.pane;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.java.constant.Constants;
import main.java.database.DatabaseConnection;
import main.java.graphs.Product;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledChoiceBox;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledScrollPane;

public class DatabaseManagePane extends StyledPane
{
	public DatabaseManagePane()
	{
		super();

		// Back to menu button
		StyledButton goBackToMenu = new StyledButton("btn.backToMainMenu", Constants.backTMMBP, Constants.backTMMBS);
		goBackToMenu.setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});
		getChildren().add(goBackToMenu);

		AddScrollPane();
		AddProductEditor();
	}

	public void AddScrollPane()
	{
		int paneWidth = ScreenProperties.getScreenWidth() / 2;
		int paneHeight = ScreenProperties.getScreenHeight() / 2;

		VBox column = new VBox();
		column.setPrefWidth(paneWidth);

		ArrayList<Product> products = DatabaseConnection.GetAvailableProducts();

		for (Product product : products)
		{
			AdvancedProductPane productPane = new AdvancedProductPane(product, new Vector2(paneWidth, 100));
			column.getChildren().add(productPane);
		}

		Vector2 pos = new Vector2(paneWidth - paneWidth / 2, paneHeight - (paneHeight / 3) * 2);
		Vector2 size = new Vector2(paneWidth, paneHeight);

		StyledScrollPane scrollPane = new StyledScrollPane(column, pos, size);
		getChildren().add(scrollPane);
	}

	public void AddProductEditor()
	{
		int paneHeight = ScreenProperties.getScreenHeight() / 2;

		StyledPane pane = new StyledPane();
		pane.setLayoutX(ScreenProperties.getScreenWidth() - 300);
		pane.setLayoutY(paneHeight - (paneHeight / 3) * 2);

		String[] editableLabels =
		{ "X", "Y", "Size" };

		for (int i = 0; i < editableLabels.length; i++)
		{
			Label label = new Label(editableLabels[i]);
			label.setLayoutX(30);
			label.setLayoutY(i * 60);
			pane.getChildren().add(label);

			String[] options = null;

			switch (i)
			{
			case 0:
			case 1:
				options = GetChoiceOptions(Constants.baseWareHouseSize);
				break;
			case 2:
				options = GetChoiceOptions(Constants.baseBoxSize);
				break;
			default:
				break;
			}

			StyledChoiceBox choiceBox = new StyledChoiceBox(options, new Vector2(60, i * 60), new Vector2(50, 30));
			pane.getChildren().add(choiceBox);
		}

		getChildren().add(pane);
	}

	private String[] GetChoiceOptions(int theOptions)
	{
		String[] options = new String[theOptions];

		for (int i = 1; i <= theOptions; i++)
		{
			options[i - 1] = Integer.toString(i);
		}

		return options;
	}
}
