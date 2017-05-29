package main.java.pane.order;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.java.main.product.Product;

public class ProductPaneHistory extends Pane
{

	public ProductPaneHistory(Product product, OrderHistory parentScript)
	{
		setPrefSize(200, 90);
		setId("customer");
		AddText("Product ID: " + product.GetId(), 0, 0);
		AddText("Productname: " + product.GetName(), 0, 20);
		AddText("Productsize: " + product.GetSize(), 0, 40);
		AddText("Coordinates: (" + product.GetCoords().toString() + ")", 0, 60);
	}

	private void AddText(String name, int x, int y)
	{
		Text text = new Text(0, 20, name);
		text.setLayoutX(x);
		text.setLayoutY(y);
		getChildren().add(text);
	}
}
