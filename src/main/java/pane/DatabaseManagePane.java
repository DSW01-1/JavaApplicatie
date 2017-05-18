package main.java.pane;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import main.java.constant.Constants;
import main.java.graphs.ProductGrid;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;

public class DatabaseManagePane extends StyledPane
{
	int newXValue, newYValue = 0;

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

		AddProductGrid();
		AddProductEditor();
	}

	private void AddProductGrid()
	{
		ProductGrid grid = new ProductGrid(Constants.baseWareHouseSize);

	}

	public void AddProductEditor()
	{
		int paneHeight = ScreenProperties.getScreenHeight() / 2;

		StyledPane pane = new StyledPane();
		pane.setLayoutX(ScreenProperties.getScreenWidth() - 500);
		pane.setLayoutY(paneHeight - (paneHeight / 3) * 2);

		Label sizeLabel = new Label();
		pane.getChildren().add(sizeLabel);

		ChoiceBox<String> sizeChoiceBox = new ChoiceBox<String>();
		pane.getChildren().add(sizeChoiceBox);

		StyledButton confirmButton = new StyledButton("btn.confirm", new Vector2(280, 200));
		pane.getChildren().add(confirmButton);

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
