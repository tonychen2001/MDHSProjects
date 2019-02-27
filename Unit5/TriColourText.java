import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

// 1: Green Light
// 2: Yellow Light
// 3: Red Light
// 4: Left Turn Light
// 5: Advance Light

public class TriColourText extends Applet
    implements ActionListener
{
    int colourNum;
    Font textFont;
    Thread t = null;

    TextField light = new TextField (10);
    Button enter = new Button ("Enter");

    Image redLight;
    Image yellowLight;
    Image greenLight;
    Image leftLight;
    Image greenOff;
    Image greenOn;

    public void init ()
    {
	setBackground (Color.WHITE);
	colourNum = 1;
	textFont = new Font ("SERIF", Font.BOLD, 24);

	// set buttons
	add (light);
	enter.addActionListener (this);
	add (enter);

	greenLight = getImage (getDocumentBase (), "greenlight.png");
	yellowLight = getImage (getDocumentBase (), "yellowlight.png");
	redLight = getImage (getDocumentBase (), "redlight.png");
	leftLight = getImage (getDocumentBase (), "leftturn.jpg");
	greenOff = getImage (getDocumentBase (), "greenoff.jpg");
	greenOn = getImage (getDocumentBase (), "greenon.jpg");
    }


    public void paint (Graphics g)
    {
	switch (colourNum)
	{
	    case 1:
		g.drawImage (greenLight, 120, 50, this);
		break;
	    case 2:
		g.drawImage (yellowLight, 120, 50, this);
		break;
	    case 3:
		g.drawImage (redLight, 120, 50, this);
		break;
	    case 4:
		g.drawImage (leftLight, 120, 50, this);
		break;
	    case 5:
		for (int i = 0 ; i < 5 ; i++)
		{
		    g.drawImage (greenOff, 120, 50, this);
		    try
		    {
			Thread.sleep (500);
		    }
		    catch (Exception e)
		    {
			System.out.println ("WOAHHHH WEVE GOT A PROBLEM HERE BUDDY");
		    }
		    g.drawImage (greenOn, 120, 50, this);
		    try
		    {
			Thread.sleep (500);
		    }
		    catch (Exception e)
		    {
			System.out.println ("WOAHHHH WEVE GOT A PROBLEM HERE BUDDY");
		    }
		}
		break;

	}
    }


    public void actionPerformed (ActionEvent evt)
    {

	String userIn = light.getText ();

	if (Integer.parseInt (userIn) == 1)
	{
	    colourNum = 1;
	}
	else if (Integer.parseInt (userIn) == 2)
	{
	    colourNum = 2;
	}
	else if (Integer.parseInt (userIn) == 3)
	{
	    colourNum = 3;
	}
	else if (Integer.parseInt (userIn) == 4)
	{
	    colourNum = 4;
	}
	else
	{
	    colourNum = 5;
	}

	repaint ();
    }
}


