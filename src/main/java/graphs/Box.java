package main.java.graphs;

import java.util.ArrayList;

public class Box
{
	private ArrayList<Product> products = new ArrayList<Product>();
	private float volume;

	public Box(float volume)
	{
		this.volume = volume;
	}

	public Box(Product[] productArray)
	{
		this(10);

		for (Product product : productArray)
		{
			products.add(product);
		}
	}

	public boolean checkFit(int size)
	{
		return size <= volume;
	}

	public void addProduct(Product product)
	{
		// not necessary to check fit, done in algorithm
		this.products.add(product);
		this.volume = this.volume - product.GetSize();
	}

	public String printNameAtLocation(int i)
	{
		return products.get(i).GetName();
	}

	public String printSizeAtLocation(int i)
	{
		return String.valueOf(products.get(i).GetSize());
	}

	public int getSize()
	{
		return products.size();
	}
	
	public float GetVolume()
	{
		return volume;
	}

	public ArrayList<Product> GetProductArray()
	{
		return products;
	}
}