/*--------------------------------------------------------------------------------------*/
/*  Circle.java  -  Description                                                       */
/*                                                                                      */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                            */
/*  Date: November 18, 2018                                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input:                                                                              */
/*  Output:                                                                             */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class Circle
{
    public double x;      // x-coordinate of centre
    public double y;      // y-coordinate of centre
    public double r;      // radius

    // override of the default constructor method
    public Circle ()
    {
	x = 0;
	y = 0;
	r = 1;
    }


    // constructor method that initializes all object attributes
    public Circle (double x, double y, double r)
    {
	this.x = x;
	this.y = y;
	this.r = r;
    }


    // constructor method that copies the passed in objects attributes
    public Circle (Circle circle)
    {
	x = circle.x;
	y = circle.y;
	r = circle.r;
    }
}


