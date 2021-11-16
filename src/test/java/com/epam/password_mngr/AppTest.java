package com.epam.password_mngr;

import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
//    @Test
//    public void shouldAnswerWithTrue()
//    {
//        assertTrue( true );
//    }
	
	
	
    public static void main(String args[])
    {    	
    	Maize [] mz = null;
    	
    	Scanner sc = new Scanner(System.in);
    	for(int i=0; i < 4 ; i++)
    	{
	    	String type = sc.nextLine();
	    	String name = sc.nextLine();
	    	double protien = sc.nextDouble();
	    	int price = sc.nextInt();
	    	
	    	mz[i] = new Maize(type, name, protien, price);
    	}
    	
    	String type = sc.nextLine();
    	int price = sc.nextInt();
    	
    	Maize.averageProtienByType(mz, type);
    	Maize.sortMaizeByPrice(mz, price);
    }
}
