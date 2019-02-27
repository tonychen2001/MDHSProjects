/*--------------------------------------------------------------------------------------*/
/*  SurvivorList.java  -  Description                                                       */
/*                                                                                      */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                            */
/*  Date: October 29, 2018                                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input:                                                                              */
/*  Output:                                                                             */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class SurvivorList
{
    public static Node load (Node node)
    {
	String names;
	do
	{
	    System.out.print ("Please enter a name: (\"fin\" to stop) ");
	    names = stdin.readLine ();
	    if (!names.equals ("fin"))
	    {
		if (node == null)
		{
		    node = new Node ();
		node.data = names;
		node.next = head;
		head = node;
	    }
	}
	while (!names.equals ("fin"));
	return head;
    }


    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");
	Node head = null;
	Node node = head;

	head = load (node, head);
	bHead = backup (head);

	if (head == null)
	{
	    System.out.println ("No contestants were entered into list");
	}
	else
	{
	    do
	    {
		System.out.println (node.data);
		node = node.next;
	    }
	    while (node != null);
	}

    }
}

