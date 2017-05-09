package main.java.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
					new FileOutputStream(new File("JsonOrders/[" + dateFormat.format(date) + "]jsonFile.json"), true));
			writer.print(json);
			writer.close();
		}
		catch (FileNotFoundException e)
		{
			LogHandler.WriteErrorToLogFile(e, "JSON File Not Found");
		}
	}

	public static Order GetNewestOrder()
	{
		Gson gson = new Gson();
		Order order = null;
		File[] jsonDirectory = new File("JsonOrders").listFiles();

		Arrays.sort(jsonDirectory, new Comparator<File>()
		{
			public int compare(File f1, File f2)
			{
				return ((File) f1).getName().compareTo(((File) f2).getName());
			}
		});

		try
		{
			FileReader reader = new FileReader(
					new File("JsonOrders/" + jsonDirectory[jsonDirectory.length - 1].getName()));
			order = gson.fromJson(reader, Order.class);
			reader.close();
		}
		catch (FileNotFoundException e)
		{
			LogHandler.WriteErrorToLogFile(e, "File Not found");
		}
		catch (IOException e)
		{
			LogHandler.WriteErrorToLogFile(e, "IO Exception");
		}
		return order;
	}
}