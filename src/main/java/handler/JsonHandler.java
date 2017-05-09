package main.java.handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;

import main.java.main.product.CustomerInfo;
import main.java.main.product.Order;

public class JsonHandler
{

	public static void SaveOrderToJSON(String[] userData, ArrayList<String> poductnames)
	{
		Gson gson = new Gson();

		Order order = new Order();
		CustomerInfo cusInfo = new CustomerInfo();
		cusInfo.setFirstname(userData[0]);
		cusInfo.setLastname(userData[1]);
		cusInfo.setAdress(userData[2]);
		cusInfo.setZipcode(userData[3]);
		cusInfo.setCity(userData[4]);

		order.setCustomerinfo(cusInfo);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		order.setDate(dateFormat.format(date));

		order.setProductnumber(poductnames);

		System.out.println(gson.toJson(order));
	}
}