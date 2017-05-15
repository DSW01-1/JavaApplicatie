package main.java.graphs;

import java.util.ArrayList;

public class Box
{
	private ArrayList<Product> products = new ArrayList<Product>();
	private int volume;

	public Box(int volume)
	{
		this.volume = volume;
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

	public String printNameAtLocation(int i){
		return products.get(i).GetName();
	}

	public String printSizeAtLocation(int i){
		return String.valueOf(products.get(i).GetSize());
	}

	public int getSize(){
		return products.size();
	}
}