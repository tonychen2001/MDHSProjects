public class Temperature
{
    private double number;
    private String scale;

    public void setNumber (double number)
    {
	this.number = number;
    }


    public double getNumber ()
    {
	return number;
    }


    public void setScale (String scale)
    {
	this.scale = scale;
    }


    public String getScaleReally ()
    {
	return scale;
    }


    public String getScale ()
    {
	return getScaleReally ();
    }
}
