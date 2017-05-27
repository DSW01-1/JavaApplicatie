package main.java.pane.database;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.java.constant.Constants;
import main.java.database.DatabaseManagement;
import main.java.graphs.grid.ProductGrid;
import main.java.handler.LogHandler;
import main.java.main.Language;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.main.product.Product;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledChoiceBox;
import main.java.pane.base.StyledLabel;

public class DatabaseEditorPane extends GridPane
{
	private static int paneHeight = ScreenProperties.getScreenHeight() / 2;
	private Product product;
	private ProductGrid grid;
	private TextField nameField;
	private StyledChoiceBox sizeChoiceBox;

	public DatabaseEditorPane(Product product, ProductGrid grid)
	{
		this.product = product;
		this.grid = grid;
		setLayoutX(ScreenProperties.getScreenWidth() - 450);
		setLayoutY(paneHeight - (paneHeight / 3) * 2);
		setHgap(20);
		setVgap(10);

		AddName();
		AddCoord();
		AddSize();
		AddConfirmButton();
		AddDeleteButton();
	}

	private void AddName()
	{
		StyledLabel nameLabel = new StyledLabel("lbl.name", new Vector2(0, 0));
		add(nameLabel, 0, 0);

		nameField = new TextField(product.GetName());
		nameField.setLayoutX(100);
		nameField.setLayoutY(0);
		add(nameField, 1, 0);
	}

	private void AddCoord()
	{
		StyledLabel coordNameLabel = new StyledLabel("lbl.coord", new Vector2(0, 50));
		add(coordNameLabel, 0, 1);

		Label coordLabel = new Label(
				"(" + (int) product.GetCoords().getX() + "," + (int) product.GetCoords().getY() + ")");
		coordLabel.setLayoutX(100);
		coordLabel.setLayoutY(50);
		add(coordLabel, 1, 1);
	}

	private void AddSize()
	{
		StyledLabel coordNameLabel = new StyledLabel("lbl.size", new Vector2(0, 50));
		add(coordNameLabel, 0, 2);

		sizeChoiceBox = new StyledChoiceBox(GetChoiceOptions(Constants.baseBoxSize), new Vector2(100, 100));
		sizeChoiceBox.getSelectionModel().select(product.GetSizeInInt());
		add(sizeChoiceBox, 1, 2);
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
			LogHandler.ShowWarning(Language.getTranslation("warning.productAdded"), "Ok");
		});
		add(confirmButton, 1, 4);
	}

	private void AddDeleteButton()
	{
		String buttonName = product.GetId() == 0 ? "btn.cancel" : "btn.delete";

		StyledButton deleteButton = new StyledButton(buttonName, new Vector2(280, 200));
		deleteButton.setOnAction(event ->
		{
			if (product.GetId() != 0)
			{
				DatabaseManagement.DeleteProduct(product.GetCoords());
			}
			grid.UpdateProductArray();
		});
		add(deleteButton, 2, 4);
	}
}
