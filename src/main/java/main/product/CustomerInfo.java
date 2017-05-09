package main.java.main.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerInfo
{
	@SerializedName("firstname")
	@Expose
	private String firstname;
	@SerializedName("lastname")
	@Expose
	private String lastname;
	@SerializedName("adress")
	@Expose
	private String adress;
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

	public String getAdress()
	{
		return adress;
	}

	public void setAdress(String adress)
	{
		this.adress = adress;
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
