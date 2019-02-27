/*--------------------------------------------------------------------------------------*/
/*  MergeSort.java  -  sort the file unsorted.txt using merge sort                       */
/*                                                                                      */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                   */
/*  Date: October 19, 2018                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input: data from unsorted file                                                      */
/*  Output: sorted data into file                                                       */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class MergeSort
{

    static int mergeCount = 0;
    // method that sorts a list using bubble sort
    public static void bubble (int[] list, int numCount)
    {
	int counter = 0;
	int temp;
	// sort the array
	for (int i = 0 ; i < (numCount - 1) ; i++)
	{
	    for (int j = 0 ; j < ((numCount - 1) - i) ; j++)
	    {
		if (list [j] > list [j + 1])
		{
		    temp = list [j];
		    list [j] = list [j + 1];
		    list [j + 1] = temp;
		}
		counter++;
	    }
	}
	System.out.println ("Number of steps to sort: " + counter);
    }


    // method that sorts arr[low...high] using merge method
    public static void mergeSort (int[] list, int low, int high)
    {
	mergeCount++;
	if (high <= low) // base case when the list contains only 1 element
	{
	    return;
	}
	else
	{
	    int mid = (low + high) / 2; // find midpoint of the list
	    mergeSort (list, low, mid); // sort the first half of the list
	    mergeSort (list, mid + 1, high); // sort the second half of the list
	    merge (list, low, mid, high); // merge the two sorted lists
	}
    }


    // method that merges two lists
    public static void merge (int[] list, int low, int mid, int high)
    {
	// declare helper array
	int[] helper = new int [1000];

	// copy list data onto helper array
	for (int i = low ; i <= high ; i++)
	{
	    helper [i] = list [i];
	}

	int i = low; // to keep track of first half of list
	int j = mid + 1; // to keep track of second half of list
	int k = low; // to keep track of the original list

	// compare values from first and second half of array and copy smallest values
	// back to the original array
	while (i <= mid && j <= high)
	{
	    mergeCount++;
	    if (helper [i] <= helper [j])
	    {
		list [k] = helper [i];
		i++;
	    }
	    else
	    {
		list [k] = helper [j];
		j++;
	    }
	    k++;
	}

	// copy the rest of the first half into the original array
	while (i <= mid)
	{
	    mergeCount++;
	    list [k] = helper [i];
	    i++;
	    k++;
	}

	// copy the rest of the second half into the original array
	while (j <= high)
	{
	    mergeCount++;
	    list [k] = helper [j];
	    j++;
	    k++;
	}
	helper = null;
    }


    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");
	BufferedReader reader = new BufferedReader (new FileReader ("unsorted.txt")); // read from file

	// declare variables
	int[] num = new int [1000];
	String line = null;
	int numCount = 0;
	int userChoice;

	// read data into array
	while ((line = reader.readLine ()) != null)
	{
	    num [numCount] = Integer.parseInt (line);
	    numCount++;
	}

	reader.close ();

	System.out.println ("(1) bubble\n(2) merge");
	userChoice = Integer.parseInt (stdin.readLine ());

	switch (userChoice)
	{
	    case 1:
		bubble (num, numCount);
		BufferedWriter writer = new BufferedWriter (new FileWriter ("sorted.txt"));

		// write sorted data into array
		for (int i = 0 ; i < numCount ; i++)
		{
		    writer.write (num [i] + "");
		    writer.newLine ();
		}

		writer.close ();
		break;
	    case 2:
		mergeSort (num, 0, num.length - 1);
		System.out.println ("Number of steps to sort: " + mergeCount);
		BufferedWriter Writer = new BufferedWriter (new FileWriter ("mergeSorted.txt"));

		// write sorted data into array
		for (int i = 0 ; i < numCount ; i++)
		{
		    Writer.write (num [i] + "");
		    Writer.newLine ();
		}

		Writer.close ();
		break;
	    default:
		System.out.println ("Wrong entry");
		break;
	}
    }
}


