/*--------------------------------------------------------------------------------------*/
/*  WoburnGrades.java  -  Description                                                       */
/*                                                                                      */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                            */
/*  Date: November 16, 2018                                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input:                                                                              */
/*  Output:                                                                             */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class WoburnGrades
{

    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");
	String input, dState;
	int numInspired = 0;

	input = stdin.readLine ();

	StringTokenizer st = new StringTokenizer (input, " ");
	int row = Integer.parseInt (st.nextToken ());
	int col = Integer.parseInt (st.nextToken ());
	int k = Integer.parseInt (st.nextToken ());

	int[] [] desk = new int [row] [col];

	for (int i = 0 ; i < row ; i++)
	{
	    dState = stdin.readLine ();
	    for (int j = 0 ; j < col ; j++)
	    {
		st = new StringTokenizer (dState, " ");
		desk [i] [j] = Integer.parseInt (st.nextToken ());
	    }
	}

	for (int i = 1 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		if (desk [i] [j] == 2)
		{
		    for (int r = (i - 1) ; r > (j - k) && r >= 0 ; r--)
		    {
			if (desk [r] [j] == 1)
			{
			    numInspired++;
			    r = -1;
			}
		    }
		}
	    }
	}

	System.out.println (numInspired);


    }
}


