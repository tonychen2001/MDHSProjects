/*--------------------------------------------------------------------------------------*/
/*  Ethan.java  -  Description                                                          */
/*                                                                                      */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                   */
/*  Date: December 14, 2018                                                             */
/*--------------------------------------------------------------------------------------*/


import java.io.*;
import java.util.*;
import java.text.*;

public class Ethan
{

    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));

	int numOfEthan = Integer.parseInt (stdin.readLine ());


	String[] namesForEthan = new String [numOfEthan];
	for (int i = 0 ; i < numOfEthan ; i++)
	{

	    namesForEthan [i] = stdin.readLine ();
	}

	int numOfBenji = Integer.parseInt (stdin.readLine ());

	String[] namesForBenji = new String [numOfBenji];
	for (int i = 0 ; i < numOfBenji ; i++)
	{
	    namesForBenji [i] = stdin.readLine ();
	}

	int dupNameNum = 0;

	for (int i = 0 ; i < numOfEthan ; i++)
	{
	    for (int j = 0 ; j < numOfBenji ; j++)
	    {
		if (namesForBenji [j].equals (namesForEthan [i]))
		{
		    dupNameNum++;
		    break;
		}
	    }
	}
	System.out.println (dupNameNum);
    }
}
