/*--------------------------------------------------------------------------------------*/
/*  PalindromePrac.java  -  Code for practising for the unit 2 test                                                      */
/*                                                                                      */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                            */
/*  Date: November 5, 2018                                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input:                                                                              */
/*  Output:                                                                             */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class PalindromePrac
{

    public static boolean palindrome (String word)
    {
	int len = word.length ();
	if (len == 0 || len == 1)
	{
	    return true;
	}
	else if (word.charAt (0) != word.charAt (len - 1))
	{
	    return false;
	}
	else
	{
	    word = word.substring (1, len - 1);
	    return palindrome (word);
	}
    }


    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");
	BufferedReader reader = new BufferedReader (new FileReader ("WordList.txt"));

	Node head = null;
	Node node = head;
	String line = null;
	boolean check;
	String user;

	while ((line = reader.readLine ()) != null)
	{
	    node = new Node ();
	    node.data = line;
	    node.next = head;
	    head = node;
	}

	reader.close ();

	System.out.print ("Enter a word: ");
	user = stdin.readLine ();

	do
	{
	    if (node.data.equals (user))
	    {
		if (palindrome (node.data))
		{
		    System.out.println (node.data + " is a palindrome");
		    break;
		}
		else
		{
		    System.out.println (node.data + " is not a palindrome");
		    break;
		}
	    }
	    else if (node.next == (null))
	    {
		System.out.println (user + " is not in the list");
		break;
	    }
	    else
	    {
		node = node.next;
	    }
	}
	while (node != null);

	// for step 1
	/*do
	{
	    check = palindrome (node.data);
	    if (check)
	    {
		System.out.println (node.data + " is a palindrome");
	    }
	    else
	    {
		System.out.println (node.data + " is not a palindrome");
	    }
	    node = node.next;
	}
	while (node != null); */
    }
}


