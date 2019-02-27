/*--------------------------------------------------------------------------------------*/
/*  WoburnInsp.java                                                                     */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                   */
/*  Date: November 16, 2018                                                             */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class WoburnInsp
{

    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");
	String input, dState;

	// counter to keep track of total type-2 students inspired
	int numInspired = 0;

	// get user input for R, C, K
	input = stdin.readLine ();

	StringTokenizer st = new StringTokenizer (input, " ");
	int row = Integer.parseInt (st.nextToken ());
	int col = Integer.parseInt (st.nextToken ());
	int k = Integer.parseInt (st.nextToken ());

	int[] [] desk = new int [row] [col];

	// nested loop to load desk type into 2-d array
	for (int i = 0 ; i < row ; i++)
	{
	    dState = stdin.readLine ();
	    st = new StringTokenizer (dState, " ");
	    for (int j = 0 ; j < col ; j++)
	    {
		desk [i] [j] = Integer.parseInt (st.nextToken ());
	    }
	}

	// nested for loop to find the number of type-2 student inspired
	// loop to keep track of the row
	// start at second row because type-2 student at first row cannot be inspired
	for (int i = 1 ; i < row ; i++)
	{
	    // loop to keep track of the column
	    for (int j = 0 ; j < col ; j++)
	    {
		// if type-2 student is found
		if (desk [i] [j] == 2)
		{
		    // loop to find a type-1 student "k" rows infront
		    for (int r = (i - 1) ; r >= (i - k) && r >= 0 ; r--)
		    {
			// if found, add 1 to the "numInspired" counter
			if (desk [r] [j] == 1)
			{
			    numInspired++;
			    break;
			}
		    }
		}
	    }
	}

	// display the number of type-2 students inspired
	System.out.println (numInspired);


    }
}


