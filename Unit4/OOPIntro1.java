/*--------------------------------------------------------------------------------------*/
/*  OOPIntro1.java  -  Description                                                       */
/*                                                                                      */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                            */
/*  Date: November 5, 2018                                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input:                                                                              */
/*  Output:                                                                             */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;


public class OOPIntro1
{

    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");
	// declare car objects
	Car myCar = new Car ();
	Car notMyCar = new Car ();

	// assign attributes to object
	myCar.setBrand ("Lambourghini");
	myCar.setModel ("Huracan");
	myCar.setCost (750000.00);

	// assign attributes to object
	notMyCar.setBrand ("Toyota");
	notMyCar.setModel ("Prius");
	notMyCar.setCost (20000.00);

	// use the build in display method to display all of the attributes of both cars
	myCar.display ();
	System.out.println ();
	notMyCar.display ();
    }
}

// class that defines the car's attributes
public class Car
{
    private String brand;
    private String model;
    private double cost;

    // built in display method to display all of the car's attributes
    public void display ()
    {
	System.out.print ("Brand: ");
	System.out.println (getBrand ());
	System.out.print ("Model: ");
	System.out.println (getModel ());
	System.out.print ("Cost: $");
	System.out.println (getCost ());
    }


    public void setBrand (String brand)
    {
	this.brand = brand;
    }


    public String getBrand ()
    {
	return brand;
    }


    public void setModel (String model)
    {
	this.model = model;
    }


    public String getModel ()
    {
	return model;
    }


    public void setCost (double cost)
    {
	this.cost = cost;
    }


    public double getCost ()
    {
	return cost;
    }
}
