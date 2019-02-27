/*--------------------------------------------------------------------------------------*/
/*  PowerR.java  -  Description                                                       */
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

public class PowerR
{
    // exponent must be > 0
    public static int power (int base, int exponent)
    {
	if (exponent == 1)
	{
	    return base;
	}
	else
	{
	    return base * power (base, (exponent - 1));
	}
    }


    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");
	int base, exponent;

	System.out.print ("Enter a base number: ");
	base = Integer.parseInt (stdin.readLine ());

	System.out.print ("Enter the exponent: ");
	exponent = Integer.parseInt (stdin.readLine ());

	System.out.println (base + " to the power of " + exponent + " = " + power (base, exponent));

    }
}

