/*--------------------------------------------------------------------------------------*/
/*  Fibonnaci.java  -  Find the value of the nth position in the fibonnaci series using recursion       */
/*                                                                                      */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                            */
/*  Date:                                                                               */
/*--------------------------------------------------------------------------------------*/
/*  Input:                                                                              */
/*  Output:                                                                             */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class FibonacciR
{
    public static int fibonacci (int n)
    {
	if (n == 1)
	{
	    return 0;
	}
	else if (n == 2)
	{
	    return 1;
	}
	else
	{
	    return (fibonacci (n - 1)) + (fibonacci (n - 2));
	}
    }


    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");
	int n;

	System.out.print ("Enter a position in the fibonnaci series to find the value of: ");
	n = Integer.parseInt (stdin.readLine ());

	System.out.println ("The value of the number in that position in the fibonacci series is: " + fibonacci (n));
    }
}

