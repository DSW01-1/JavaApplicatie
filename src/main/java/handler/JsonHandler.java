package main.java.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.constant.ErrorConstants;
import main.java.main.product.CustomerInfo;
import main.java.main.product.Order;

public class JsonHandler
{

	public static void SaveOrderToJSON(String[] userData, ArrayList<String> productID)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

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

		order.setProductnumber(productID);

		WriteOrderToJSON(gson.toJson(order));
	}

	public static void WriteOrderToJSON(String json)
	{
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd-HH-mm-ss-SSS");
			Date date = new Date();

			PrintWriter writer = new PrintWriter(
					new FileOutputStream(new File("[" + dateFormat.format(date) + "]jsonFile.json"), true));
			writer.print(json);
			writer.close();
		}
		catch (FileNotFoundException e)
		{
			LogHandler.WriteErrorToLogFile(e, "JSON File Not Found");
		}
	}
}