package main.java.main.product;

import java.util.ArrayList;

public class Box
{
	private ArrayList<Product> products = new ArrayList<Product>();
	private int totalSpace;
	private int remainingSpace;

	public Box(int totalSpace)
	{
		this.totalSpace = totalSpace;
		remainingSpace = totalSpace;
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
		return size <= remainingSpace;
	}

	public void addProduct(Product product)
	{
		// not necessary to check fit, done in algorithm
		this.products.add(product);
		remainingSpace = (int) (remainingSpace - product.GetSize());
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

	public float GetTotalSpace()
	{
		return totalSpace;
	}

	public ArrayList<Product> GetProductArray()
	{
		return products;
	}
}