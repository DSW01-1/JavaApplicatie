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
import main.java.pane.base.StyledLabel;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledScrollPane;

import javax.xml.crypto.Data;

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
		Vector2 personPos = new Vector2(180,240);
		Vector2 orderPos = new Vector2(520,240);
		Vector2 productPos = new Vector2(860,240);
		Vector2 headerPos = new Vector2(800,20);
		StyledLabel headerLabel = new StyledLabel("lbl.history",headerPos);
		StyledLabel personLabel = new StyledLabel("lbl.persons",personPos);
		StyledLabel orderLabel = new StyledLabel("lbl.orders",orderPos);
		StyledLabel productLabel = new StyledLabel("lbl.products",productPos);
		getChildren().addAll(personLabel,orderLabel,productLabel,goBackToMenu,headerLabel);
		createPersonVBox();
		createEmptyOrderVBox();
		createEmptyProductVBox();
	}

	private void createPersonVBox(){
		ArrayList <CustomerInfo> customers = DatabaseOrder.getAllCustomers();
		VBox vbox = new VBox();
		vbox.maxWidth(ScreenProperties.getScreenWidth()/3);
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
		vboxOrder.maxWidth(ScreenProperties.getScreenWidth()/3);
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
		vboxProduct.maxWidth(ScreenProperties.getScreenWidth()/3);
		vboxProduct.setLayoutX(840);
		vboxProduct.setLayoutY(270);
		List<Integer> products;
		products = DatabaseOrder.getProductsIDFromOrder(Ordernr);
		for(Integer productid:products){
			vboxProduct.getChildren().add(new ProductPaneHistory(DatabaseOrder.getProductByID(productid),this));
		}
		Vector2 pos = new Vector2(840,270);
		Vector2 size = new Vector2(160,500);
		getChildren().add(new StyledScrollPane(vboxProduct,pos,size));
	}

	//ik weet dat dit beter kan :P
	private void createEmptyOrderVBox(){
		VBox vboxOrder = new VBox();
		vboxOrder.maxWidth(ScreenProperties.getScreenWidth()/3);
		vboxOrder.setLayoutX(500);
		vboxOrder.setLayoutY(270);
		Vector2 pos = new Vector2(500,270);
		Vector2 size = new Vector2(160, 500);
		getChildren().add(new StyledScrollPane(vboxOrder,pos,size));
	}

	//ik weet dat dit beter kan :P
	private void createEmptyProductVBox(){
		VBox vboxProduct = new VBox();
		vboxProduct.maxWidth(ScreenProperties.getScreenWidth()/3);
		vboxProduct.setLayoutX(840);
		vboxProduct.setLayoutY(270);
		Vector2 pos = new Vector2(840,270);
		Vector2 size = new Vector2(160,500);
		getChildren().add(new StyledScrollPane(vboxProduct,pos,size));
	}
}
