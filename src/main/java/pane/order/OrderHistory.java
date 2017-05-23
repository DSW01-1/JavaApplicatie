package main.java.pane.order;

import java.util.ArrayList;

import javafx.scene.layout.VBox;
import main.java.database.DatabaseOrder;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.main.product.Order;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledScrollPane;

public class OrderHistory extends StyledPane
{

	@Override
	public void InitPane()
	{
		VBox vbox = new VBox();

		ArrayList<Order> products = DatabaseOrder.GetAllOrders();

		for (int i = 0; i < products.size(); i++)
		{
			System.out.println(products.get(i).getOrderid());
		}

		Vector2 panePos = new Vector2(ScreenProperties.getScreenWidth() / 4, ScreenProperties.getScreenHeight() / 2);
		Vector2 paneSize = new Vector2(ScreenProperties.getScreenWidth() / 2, ScreenProperties.getScreenHeight() / 2);

		getChildren().add(new StyledScrollPane(vbox, panePos, paneSize));
	}
}
