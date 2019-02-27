import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class TriColour extends Applet
    implements ActionListener
{
    int colourNum;
    Font textFont;
    Thread t = null;

    Button bttn1 = new Button ("Green");
    Button bttn2 = new Button ("Yellow");
    Button bttn3 = new Button ("Red");
    Button bttn4 = new Button ("Left");
    Button bttn5 = new Button ("Advance");

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
	bttn1.addActionListener (this);
	bttn2.addActionListener (this);
	bttn3.addActionListener (this);
	bttn4.addActionListener (this);
	bttn5.addActionListener (this);
	add (bttn1);
	add (bttn2);
	add (bttn3);
	add (bttn4);
	add (bttn5);

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
	if (evt.getSource () == bttn1)
	{
	    colourNum = 1;
	}
	else if (evt.getSource () == bttn2)
	{
	    colourNum = 2;
	}
	else if (evt.getSource () == bttn3)
	{
	    colourNum = 3;
	}
	else if (evt.getSource () == bttn4)
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


