public class Weather extends Temperature
{
    private int windSpeed;
    private char windDirection;
    private String visibility;

    public void setWindSpeed (int windSpeed)
    {
	this.windSpeed = windSpeed;
    }


    public int getWindSpeed ()
    {
	return windSpeed;
    }


    public void setWindDirection (char windDirection)
    {
	this.windDirection = windDirection;
    }


    public char getWindDirection ()
    {
	return windDirection;
    }


    public void setVisibility (String visibility)
    {
	this.visibility = visibility;
    }


    public String getVisibility ()
    {
	return visibility;
    }


    public String getScale ()
    {
	if (getScaleReally ().equals ("F"))
	{
	    return ("Fahrenheit");
	}
	else if (getScaleReally ().equals ("C"))
	{
	    return ("Celcius");
	}
	else
	{
	    return ("Kelvin");
	}
    }
}
