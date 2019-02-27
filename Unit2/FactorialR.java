/*--------------------------------------------------------------------------------------*/
/*  FactorialR.java  -  Find factorial of N using recursion                                                      */
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

public class FactorialR
{
    public static int Factorial (int n)
    {
	if (n <= 1)
	{
	    return 1;
	}
	else
	{
	    return (n * Factorial (n - 1));
	}
    }


    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");
	int n;

	System.out.print ("Enter a number to find the factorial of: ");
	n = Integer.parseInt (stdin.readLine ());

	System.out.println ("The factorial of " + n + " is " + Factorial (n));

    }
}

