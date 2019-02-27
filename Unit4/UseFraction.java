/*--------------------------------------------------------------------------------------*/
/*  UseFraction.java  -  Description                                                       */
/*                                                                                      */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                            */
/*  Date: November 18, 2018                                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input:                                                                              */
/*  Output:                                                                             */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class UseFraction
{

    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");
	Fraction x = new Fraction (5, 7);
	Fraction y = new Fraction (3, 0);
	Fraction test = new Fraction ();

	System.out.println (test.numerator + "/" + test.denominator);

	System.out.println ("Sum = " + ((x.numerator / x.denominator) + (y.numerator / y.denominator)));
	System.out.println ("Product = " + ((x.numerator / x.denominator) * (y.numerator / y.denominator)));


    }
}

public class Fraction
{
    double numerator;
    double denominator;

    // override default constructor method
    public Fraction ()
    {
	numerator = 0;
	denominator = 1;
    }


    // constructor method that initializes both numerator and denominator
    public Fraction (double numerator, double denominator)
    {
	this.numerator = numerator;
	// default denom to '1' if '0' is passed in
	if (denominator == 0)
	{
	    denominator = 1;
	}
	this.denominator = denominator;
    }
}

