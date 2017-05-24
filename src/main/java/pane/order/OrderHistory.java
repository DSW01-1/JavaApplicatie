package main.java.pane.order;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.VBox;
import main.java.constant.Constants;
import main.java.database.DatabaseOrder;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.main.product.CustomerInfo;
import main.java.pane.MainMenu;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledScrollPane;

public class OrderHistory extends StyledPane
{
	public OrderHistory()
	{
		super();
	}

	@Override
	public void InitPane()
	{
		// Back to menu button
		StyledButton goBackToMenu = new StyledButton("btn.backToMainMenu", Constants.backTMMBP, Constants.backTMMBS);
		goBackToMenu.setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});
		getChildren().add(goBackToMenu);
		createPersonVBox();
	}

	private void createPersonVBox(){
		ArrayList <CustomerInfo> customers = DatabaseOrder.getAllCustomers();
		VBox vbox = new VBox();
		vbox.maxWidth(ScreenProperties.getScreenWidth()/4);
		vbox.setLayoutX(160);
		vbox.setLayoutY(270);
		Vector2 pos = new Vector2(160,270);
		Vector2 size = new Vector2(160,500);
		for(CustomerInfo info:customers){
			vbox.getChildren().add(new PersonPane(info,this));
		}
		getChildren().add(new StyledScrollPane(vbox,pos,size));
	}

	public void createOrderVBox(int Customerid){
		VBox vboxOrder = new VBox();
		vboxOrder.maxWidth(ScreenProperties.getScreenWidth()/4);
		vboxOrder.setLayoutX(500);
		vboxOrder.setLayoutY(270);
		List<Integer> orders;
		orders = DatabaseOrder.getOrdersFromId(Customerid);
		for(Integer ordernr:orders){
			vboxOrder.getChildren().add(new OrderPane(ordernr,this));
		}
		Vector2 pos = new Vector2(500,270);
		Vector2 size = new Vector2(160, 500);
		getChildren().add(new StyledScrollPane(vboxOrder,pos,size));
	}

	public void createProductVBox(int Ordernr){
		VBox vboxProduct = new VBox();
		vboxProduct.maxWidth(ScreenProperties.getScreenWidth()/4);
		vboxProduct.setLayoutX(1000);
		vboxProduct.setLayoutY(270);
	}
}
