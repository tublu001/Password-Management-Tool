package com.epam.password_mngr;

import java.util.ArrayList;
import java.util.Collections;

public class Maize 
{
	private String maizeType;
	private String maizeName;
	private double protien;
	private int price;

	public Maize() 
	{	}
	
	
	public Maize(String maizeType, String maizeName, double protien, int price) {
//		super();
		this.maizeType = maizeType;
		this.maizeName = maizeName;
		this.protien = protien;
		this.price = price;
	}


	public String getMaizeType() {
		return maizeType;
	}


	public void setMaizeType(String maizeType) {
		this.maizeType = maizeType;
	}


	public String getMaizeName() {
		return maizeName;
	}


	public void setMaizeName(String maizeName) {
		this.maizeName = maizeName;
	}


	public double getProtien() {
		return protien;
	}


	public void setProtien(double protien) {
		this.protien = protien;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
	public static double averageProtienByType(Maize[] mz, String val)
	{
		double sum=0;
		int count=0;
		val=val.toLowerCase();
		for(int i = 0; i < mz.length; i++)
		{
			if(val.equals(mz[i].getMaizeType().toLowerCase()))
			{
				count++;
				sum += mz[i].getProtien();
			}
		}
		
		if(sum > 0.0)
			return sum/count;
		
		return 0.0;
	}
	
	public static Maize[] sortMaizeByPrice(Maize[] mz, int val)
	{
		ArrayList<Integer> prices = new ArrayList<Integer>();
		ArrayList<Maize> newMz = new ArrayList<Maize>();
		
		for(int i = 0; i < mz.length; i++)
		{
			if(mz[i].getPrice() < val)
			{
				prices.add(mz[i].getPrice());
			}
			if(prices.size()>0)
			{
				Collections.sort(prices);
			}
		}
		for(int z : prices)
		{
			for(int j=0; j < mz.length; j++)
			{
				if(z == mz[j].getPrice())
				{
					newMz.add(mz[j]);
					break;
				}
			}
		}
			
		return newMz.toArray(new Maize[0]);
	}
	

}
