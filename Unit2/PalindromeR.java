/*--------------------------------------------------------------------------------------*/
/*  PalindromeR.java  -  Find out if a string is a palindrome or not using recursion    */
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

public class PalindromeR
{
    public static boolean palindrome (String word)
    {
	System.out.println (word);
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
	String word;

	System.out.print ("Enter a string to test if it's a palindrome or not: ");
	word = stdin.readLine ();

	if (palindrome (word) == true)
	{
	    System.out.println (word + " is a palindrome");
	}
	else
	{
	    System.out.println (word + " is not a palindrome");
	}
    }
}

