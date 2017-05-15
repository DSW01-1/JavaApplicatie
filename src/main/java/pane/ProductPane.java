package main.java.pane;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.java.constant.Constants;
import main.java.graphs.Product;
import main.java.handler.LogHandler;

public class ProductPane extends Pane
{
	private NewOrder parentScript;
	private ProductPane pane;
	private Product product;
	

	public ProductPane(Product product, String columnSide, NewOrder parentScript)
	{
		this.product = product;
		this.parentScript = parentScript;
		pane = this;

		setId("product");
		setPrefSize(200, 50);

		AddText(product.GetName());

		if ("left".equals(columnSide))
		{
			AddShoppingCart();
		}
		else
		{
			AddRemoveButton();
		}

	}

	private void AddRemoveButton()
	{
		ImageView imageView = new ImageView();
		
		try
		{
			Image image = new Image(Constants.xImage);
			imageView.setImage(image);
		}
		catch (IllegalArgumentException e)
		{
			LogHandler.WriteErrorToLogFile(e, "Image string not correct");
		}

		imageView.setFitWidth(30);
		imageView.setPreserveRatio(true);
		imageView.setLayoutX(160);
		imageView.setLayoutY(5);
		getChildren().add(imageView);

		Pane clickPane = new Pane();
		clickPane.setPrefSize(50, 45);
		clickPane.setLayoutX(150);
		clickPane.setLayoutY(0);
		clickPane.setCursor(Cursor.HAND);
		clickPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0);");
		getChildren().add(clickPane);

		clickPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				parentScript.MoveProduct("right", pane);
			}
		});
	}

	private void AddText(String name)
	{
		Text text = new Text(0, 20, name);
		getChildren().add(text);
	}

	private void AddShoppingCart()
	{
		ImageView imageView = new ImageView();
		
		try
		{
			Image image = new Image(Constants.shoppingCartImage);
			imageView.setImage(image);
		}
		catch (IllegalArgumentException e)
		{
			LogHandler.WriteErrorToLogFile(e, "Image string not correct");
		}
		
		imageView.setFitWidth(30);
		imageView.setPreserveRatio(true);
		imageView.setLayoutX(160);
		imageView.setLayoutY(5);
		getChildren().add(imageView);

		Pane clickPane = new Pane();
		clickPane.setPrefSize(50, 45);
		clickPane.setLayoutX(150);
		clickPane.setLayoutY(0);
		clickPane.setCursor(Cursor.HAND);
		clickPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0);");
		getChildren().add(clickPane);

		clickPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				parentScript.MoveProduct("left", pane);
			}
		});
	}
	
	public Product GetProduct()
	{
		return product;
	}
}
