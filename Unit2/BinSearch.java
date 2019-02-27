/*--------------------------------------------------------------------------------------*/
/*  BinSearch.java  -  A program that reads a sorted list of telephone numbers and      */
/*                     loads the stored data into an array. When a user enters a        */
/*                     telephone, a recursive binary search and a linear search method  */
/*                     is used to locate the telephone number in the list (if it exists)*/
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                   */
/*  Date: October 10, 2018                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input: Sorted list of telephone numbers, and a telephone number inputted by the user*/
/*  Output: Both the location of the phone number (index in the array) and the number   */
/*          of comparison operations used to locate the telephone number                */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class BinSearch
{
    static int counter = 0;

    // a recursive binary search to locate the telephone number in the list
    public static int binary (String[] numbers, String userNum, int start, int end)
    {
	counter++;
	if (end <= start)   // base case is when the search interval is cut down to a size of 1
	{

	    if (numbers [start].equals (userNum))
	    {
		return start;
	    }
	    else
	    {
		return -1;
	    }
	}
	int midPoint = (start + (end + 1)) / 2;  // find the midpoint of the array
	if (numbers [midPoint].equals (userNum)) // if the telephone number is the midpoint
	{
	    return midPoint;
	}
	else if ((numbers [midPoint].compareTo (userNum)) > 0) // if the telephone number is before the midpoint
	{
	    return binary (numbers, userNum, start, midPoint - 1);
	}
	else // if the telephone number is after the midpoint
	{
	    return binary (numbers, userNum, midPoint + 1, end);
	}

    }


    // a linear search to locate the telephone number in the list
    public static void linear (String[] numbers, String userNum)
    {
	boolean found = false;
	for (int i = 0 ; i < numbers.length ; i++)
	{
	    if (numbers [i].equalsIgnoreCase (userNum)) // if statement to indicate the telephone number was found
	    {
		found = true;
		System.out.println ("The number was found in index " + i + " in the list");
		System.out.println ((i + 1) + " comparison operations were used to locate the telephone number");
	    }
	}
	if (found == false) // print a message if the telephone number was not found
	{
	    System.out.println ("The telephone number was not found");
	    System.out.println (numbers.length + " comparison operations were used to locate the telephone number");
	}
    }


    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");
	BufferedReader reader = new BufferedReader (new FileReader ("phone.txt"));

	// declare variables
	String userNum;
	String line = null;
	String[] numbers = new String [1500]; // array to store telephone numbers
	int numberCount = 0;

	// read telephone numbers into array
	while ((line = reader.readLine ()) != null)
	{
	    numbers [numberCount] = (line);
	    numberCount++;
	}

	reader.close ();

	// get user input for a telephone number
	System.out.print ("Enter a phone number to search for: ");
	userNum = stdin.readLine ();

	// Call the recursive binary search method
	System.out.println ("\t\t\tRECURSIVE BINARY SEARCH");
	// assign the value returned by the method to a variable
	int index = binary (numbers, userNum, 0, numbers.length - 1);
	if (index >= 0) // output an appropriate message if the telephone number was found in the list
	{
	    System.out.print ("The telephone number was found in index " + index);
	    System.out.println (" in the list");
	    System.out.println (counter + " comparison operations were used to locate the telephone number");
	    System.out.println ();
	}
	else // output an appropriate message if the telephone number was not found in the list
	{
	    System.out.println ("The telephone number was not found in the list");
	    System.out.println (counter + " comparison operations were used to locate the telephone number");
	    System.out.println ();
	}

	// Call the linear search method
	System.out.println ("\t\t\t\tLinear Search");
	linear (numbers, userNum);
    }
}


