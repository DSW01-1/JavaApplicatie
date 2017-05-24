package main.java.main.product;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order
{
	@SerializedName("orderid")
	@Expose
	private String orderid;
	@SerializedName("customerinfo")
	@Expose
	private CustomerInfo customerinfo;
	@SerializedName("date")
	@Expose
	private String date;
	@SerializedName("productnumber")
	@Expose
	private ArrayList<String> productnumber = null;

	public String getOrderid()
	{
		return orderid;
	}

	public void setOrderid(String orderid)
	{
		this.orderid = orderid;
	}

	public CustomerInfo getCustomerinfo()
	{
		return customerinfo;
	}

	public void setCustomerinfo(CustomerInfo customerinfo)
	{
		this.customerinfo = customerinfo;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public ArrayList<String> getProducts()
	{
		return productnumber;
	}

	public void setProductnumber(ArrayList<String> productnumber)
	{
		this.productnumber = productnumber;
	}

	public String[] getCustomerinfoArray()
	{
		CustomerInfo cusInfo = getCustomerinfo();

		String[] cusInfoArray =
		{ cusInfo.getFirstname(), cusInfo.getLastname(), cusInfo.getAddress(), cusInfo.getZipcode(),
				cusInfo.getCity() };
		return cusInfoArray;
	}
}