package main.java.pane;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.java.constant.Constants;
import main.java.database.DatabaseManagement;
import main.java.graphs.grid.ProductGrid;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.main.product.Product;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledChoiceBox;
import main.java.pane.base.StyledLabel;
import main.java.pane.base.StyledPane;

public class DatabaseEditorPane extends StyledPane
{
	private static int paneHeight = ScreenProperties.getScreenHeight() / 2;
	private Product product;
	private ProductGrid grid;
	private TextField nameField;
	private StyledChoiceBox sizeChoiceBox;

	public DatabaseEditorPane(Product product, ProductGrid grid)
	{
		super(new Vector2(ScreenProperties.getScreenWidth() - 500, paneHeight - (paneHeight / 3) * 2));
		this.product = product;
		this.grid = grid;

		AddName();
		AddCoord();
		AddSize();
		AddConfirmButton();
	}

	private void AddName()
	{
		StyledLabel nameLabel = new StyledLabel("lbl.name", new Vector2(0, 0));
		getChildren().add(nameLabel);

		nameField = new TextField(product.GetName());
		nameField.setLayoutX(100);
		nameField.setLayoutY(0);
		getChildren().add(nameField);
	}

	private void AddCoord()
	{
		StyledLabel coordNameLabel = new StyledLabel("lbl.coord", new Vector2(0, 50));
		getChildren().add(coordNameLabel);

		Label coordLabel = new Label(
				"(" + (int) product.GetCoords().getX() + "," + (int) product.GetCoords().getY() + ")");
		coordLabel.setLayoutX(100);
		coordLabel.setLayoutY(50);
		getChildren().add(coordLabel);
	}

	private void AddSize()
	{
		sizeChoiceBox = new StyledChoiceBox(GetChoiceOptions(Constants.baseBoxSize), new Vector2(100, 100));
		sizeChoiceBox.getSelectionModel().select(product.GetSizeInInt());
		getChildren().add(sizeChoiceBox);
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

	private void AddConfirmButton()
	{
		String buttonName = product.GetId() == 0 ? "btn.addProduct" : "btn.confirm";

		StyledButton confirmButton = new StyledButton(buttonName, new Vector2(280, 200));
		confirmButton.setOnAction(event ->
		{
			product.SetName(nameField.getText());
			product.SetSize(sizeChoiceBox.getSelectionModel().getSelectedIndex());

			if (product.GetId() == 0)
			{
				DatabaseManagement.CreateNewProduct(product);
			}
			else
			{
				DatabaseManagement.UpdateProduct(product);
			}
			grid.UpdateProductArray();
		});
		getChildren().add(confirmButton);
	}
}
