package main.java.pane;

import java.util.ArrayList;

import javafx.scene.layout.VBox;
import main.java.database.DatabaseConnection;
import main.java.graphs.Product;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledScrollPane;

public class DatabaseManagePane extends StyledPane
{
	public DatabaseManagePane()
	{
		super();

		AddScrollPane();
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
			AdvancedProductPane productPane = new AdvancedProductPane(product, new Vector2(paneWidth, 200));
			column.getChildren().add(productPane);
		}

		Vector2 pos = new Vector2(paneWidth - paneWidth / 2, paneHeight - (paneHeight / 3) * 2);
		Vector2 size = new Vector2(paneWidth, paneHeight);

		StyledScrollPane scrollPane = new StyledScrollPane(column, pos, size);
		getChildren().add(scrollPane);
	}
}
