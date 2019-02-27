/*--------------------------------------------------------------------------------------*/
/*  LinkedListTest.java  -  A program that creates a linked list                        */
/*                                                                                      */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                   */
/*  Date: October 27, 2018                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input: names to create a list                                                       */
/*  Output: the entire list                                                             */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class LinkedListTest
{
    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");
	String user; // variable to handle user input
	Node head = null;
	Node node = head;



	// loop to create the list
	do
	{
	    System.out.print ("Enter a name or (quit) to exit: ");
	    user = stdin.readLine ();

	    // if user enters a new item into the list
	    if (!user.equals ("quit"))
	    {
		node = new Node ();
		node.data = user;
		node.next = head;
		head = node;
	    }
	}
	while (!user.equals ("quit"));

	System.out.println ();

	if (node == null) // if the user did not create a list
	{
	    System.out.println ("The user exited the program before creating a list");
	}
	else
	{
	    // loop to display the entire list
	    do
	    {
		System.out.println (node.data);
		node = node.next;
	    }
	    while (node != null);
	}
    }
}

