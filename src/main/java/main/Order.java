package main.java.main;

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
	private Customerinfo customerinfo;
	@SerializedName("date")
	@Expose
	private String date;
	@SerializedName("productnumber")
	@Expose
	private List<String> productnumber = null;

	public String getOrderid()
	{
		return orderid;
	}

	public void setOrderid(String orderid)
	{
		this.orderid = orderid;
	}

	public Customerinfo getCustomerinfo()
	{
		return customerinfo;
	}

	public void setCustomerinfo(Customerinfo customerinfo)
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

	public List<String> getProductnumber()
	{
		return productnumber;
	}

	public void setProductnumber(List<String> productnumber)
	{
		this.productnumber = productnumber;
	}
}

class Customerinfo
{

	@SerializedName("firstname")
	@Expose
	private String firstname;
	@SerializedName("lastname")
	@Expose
	private String lastname;
	@SerializedName("aadress")
	@Expose
	private String aadress;
	@SerializedName("zipcode")
	@Expose
	private String zipcode;
	@SerializedName("city")
	@Expose
	private String city;

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getAadress()
	{
		return aadress;
	}

	public void setAadress(String aadress)
	{
		this.aadress = aadress;
	}

	public String getZipcode()
	{
		return zipcode;
	}

	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}
}