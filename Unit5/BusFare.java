import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class BusFare extends Applet
    implements ActionListener
{
    Font textFont = new Font ("Times New Roman", Font.BOLD, 14);
    Button bttn1 = new Button ("Calculate");
    TextField age = new TextField (2);
    String cost = "";

    public void init ()
    {
	setBackground (Color.cyan);
	textFont = new Font ("Serif", Font.BOLD, 48);

	add (age);

	bttn1.addActionListener (this);
	add (bttn1);
    }


    public void paint (Graphics g)
    {
	g.setFont (textFont);
	g.drawString (cost, 120, 90);
    }


    public void actionPerformed (ActionEvent evt)
    {
	try
	{
	    String userAge = age.getText ();

	    if (Integer.parseInt (userAge) < 2)
	    {
		cost = "Free";
	    }
	    else if (Integer.parseInt (userAge) >= 2 && Integer.parseInt (userAge) <= 11)
	    {
		cost = "50¢";
	    }
	    else if (Integer.parseInt (userAge) >= 12 && Integer.parseInt (userAge) <= 17)
	    {
		cost = "$1";
	    }
	    else if (Integer.parseInt (userAge) >= 18 && Integer.parseInt (userAge) <= 65)
	    {
		cost = "$2";
	    }
	    else
	    {
		cost = "$1";
	    }
	}
	catch (Exception e)
	{
	    System.out.println ("ERROR ERROR");
	}
	repaint ();
    }
}


