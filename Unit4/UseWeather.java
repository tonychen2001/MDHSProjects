/*--------------------------------------------------------------------------------------*/
/*  UseWeather.java  -  Description                                                     */
/*                                                                                      */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                   */
/*  Date: November 21, 2018                                                             */
/*--------------------------------------------------------------------------------------*/
/*  Input:                                                                              */
/*  Output:                                                                             */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class UseWeather
{
    public static void main (String str[]) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");
	Weather thursday = new Weather ();

	thursday.setNumber (6);
	thursday.setScale ("C");
	thursday.setWindSpeed (15);
	thursday.setWindDirection ('E');
	thursday.setVisibility ("Foggy");

	System.out.println (thursday.getNumber () + " " + thursday.getScale ());
	System.out.println (thursday.getWindSpeed () + "km " + thursday.getWindDirection ());
	System.out.println (thursday.getVisibility ());



    }
}

