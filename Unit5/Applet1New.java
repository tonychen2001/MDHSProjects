import java.applet.Applet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Applet1New extends Applet implements ItemListener, ActionListener
{
    int startX;
    int startY;
    int colourNum, shapeNum;
    String curColor;
    Font textFont;
    int width;
    boolean animate;

    Panel graphic = new Panel ();
    Panel color = new Panel ();

    Panel shapeP = new Panel ();

    Panel shapeSize = new Panel ();

    Panel shapeAttributes = new Panel ();

    Panel actions = new Panel ();

    Button drawBttn = new Button ("Draw");
    Button animateBttn = new Button ("Animate");

    CheckboxGroup colour = new CheckboxGroup ();
    CheckboxGroup shape = new CheckboxGroup ();

    Checkbox radioBlue = new Checkbox ("Blue", colour, false);
    Checkbox radioYellow = new Checkbox ("Yellow", colour, false);
    Checkbox radioRed = new Checkbox ("Red", colour, false);
    Checkbox radioMagenta = new Checkbox ("Magenta", colour, false);

    Checkbox radioSquare = new Checkbox ("Square", shape, false);
    Checkbox radioRoundSq = new Checkbox ("Round square", shape, false);
    Checkbox radioCircle = new Checkbox ("Circle", shape, false);
    Checkbox radioSemiCircle = new Checkbox ("Half circle", shape, false);

    TextField size = new TextField (3);

    Label attributes = new Label ("rectangle, blue, W: 100, L: 50");

    public void init ()
    {
	colourNum = 0;
	shapeNum = 0;
	startX = 10;
	startY = 150;

	setBackground (Color.lightGray);
	textFont = new Font ("SERIF", Font.BOLD, 24);

	width = 50;
	animate = false;


	//graphic.add(l);
	// set the radio buttons that control the color
	radioBlue.addItemListener (this);
	radioYellow.addItemListener (this);
	radioRed.addItemListener (this);
	radioMagenta.addItemListener (this);

	Label lColor = new Label ("Choose one of the following colors");
	color.add (lColor);
	color.add (radioBlue);
	color.add (radioYellow);
	color.add (radioRed);
	color.add (radioMagenta);
	color.setLayout (new GridLayout (5, 1));

	// set the radio buttons that control the shape
	radioSquare.addItemListener (this);
	radioRoundSq.addItemListener (this);
	radioCircle.addItemListener (this);
	radioSemiCircle.addItemListener (this);

	//Label blank = new Label(" ");
	Label lShape = new Label ("Choose one of the following shapes");
	//shapeP.add(blank);
	shapeP.add (lShape);
	shapeP.add (radioSquare);
	shapeP.add (radioRoundSq);
	shapeP.add (radioCircle);
	shapeP.add (radioSemiCircle);
	shapeP.setLayout (new GridLayout (5, 1));

	Label sSize = new Label ("Please input shape size: ");
	shapeSize.add (sSize);
	shapeSize.add (size);
	shapeSize.setLayout (new GridLayout (1, 2));

	shapeAttributes.add (attributes);
	shapeAttributes.setLayout (new GridLayout (1, 1));

	drawBttn.addActionListener (this);
	actions.add (drawBttn);

	animateBttn.addActionListener (this);
	actions.add (animateBttn);

	actions.setLayout (new GridLayout (1, 2));

	GridBagLayout gridbag = new GridBagLayout ();
	GridBagConstraints c = new GridBagConstraints ();


	c.fill = GridBagConstraints.BOTH;
	c.weightx = 2.0;

	c.gridwidth = 2; // reset to the default
	c.gridheight = 5;
	c.weighty = 1.0;
	gridbag.setConstraints (graphic, c);
	add (graphic);

	c.weightx = 1.0;
	c.gridheight = 1;
	c.gridwidth = GridBagConstraints.REMAINDER;
	c.weighty = 0.0;

	gridbag.setConstraints (color, c);
	add (color);

	// Panel shape = new Panel();
	gridbag.setConstraints (shapeP, c);
	add (shapeP);

	// Panel shapeSize = new Panel();
	gridbag.setConstraints (shapeSize, c);
	add (shapeSize);

	// Panel shapeAttributes = new Panel();
	gridbag.setConstraints (shapeAttributes, c);
	add (shapeAttributes);

	// Panel actions = new Panel();
	gridbag.setConstraints (actions, c);
	add (actions);

	setLayout (gridbag);
	setSize (new Dimension (800, 340));
	setVisible (true);

    }


    public void paint (Graphics gc)
    {

	Graphics g = graphic.getGraphics ();
	g.clearRect (0, 0, 1000, 1000);

	switch (colourNum)
	{
	    case 1:
		g.setColor (Color.blue);
		curColor = "Blue";
		break;
	    case 2:
		g.setColor (Color.yellow);
		curColor = "Yellow";
		break;
	    case 3:
		g.setColor (Color.red);
		curColor = "Red";
		break;
	    case 4:
		g.setColor (Color.magenta);
		curColor = "Magenta";
		break;
	}

	switch (shapeNum)
	{
	    case 1:
		attributes.setText (curColor + ", Square, " + " W: " + width);
		if (animate)
		{
		    for (int i = startX ; i < 500 ; i++)
		    {
			g.fillRect (i, startY, width, width);
			try
			{
			    Thread.sleep (10);
			}
			catch (Exception e)
			{
			    System.out.println ("Error");
			}
			g.clearRect (i, startY, width, width);
		    }
		}
		g.fillRect (startX, startY, width, width);
		break;
	    case 2:
		attributes.setText (curColor + ", Round square, " + " W: " + width);
		if (animate)
		{
		    for (int i = startX ; i < 500 ; i++)
		    {
			g.fillRoundRect (i, startY, width, width, 25, 25);
			try
			{
			    Thread.sleep (10);
			}
			catch (Exception e)
			{
			    System.out.println ("Error");
			}
			g.clearRect (i, startY, width, width);
		    }

		}
		g.fillRoundRect (startX, startY, width, width, 25, 25);
		break;
	    case 3:
		attributes.setText (curColor + ", Circle, " + " R: " + width);
		if (animate)
		{
		    for (int i = startX ; i < 500 ; i++)
		    {
			g.fillOval (i, startY, width, width);
			try
			{
			    Thread.sleep (10);
			}
			catch (Exception e)
			{
			    System.out.println ("Error");
			}
			g.clearRect (i, startY, width, width);
		    }
		}
		g.fillOval (startX, startY, width, width);
		break;
	    case 4:
		attributes.setText (curColor + ", Semi-Circle, " + " R: " + width);
		if (animate)
		{
		    for (int i = startX ; i < 500 ; i++)
		    {
			g.fillArc (i, startY, width, width, 0, 180);
			try
			{
			    Thread.sleep (10);
			}
			catch (Exception e)
			{
			    System.out.println ("Error");
			}
			g.clearRect (i, startY, width, width);
		    }
		}
		g.fillArc (startX, startY, width, width, 0, 180);
		break;
	}
	animate = false;

    }


    public void itemStateChanged (ItemEvent e)
    {
	if (radioBlue.getState () == true)
	{
	    colourNum = 1;
	}
	else if (radioYellow.getState () == true)
	{
	    colourNum = 2;
	}
	else if (radioRed.getState () == true)
	{
	    colourNum = 3;
	}
	else if (radioMagenta.getState () == true)
	{
	    colourNum = 4;
	}

	if (radioSquare.getState () == true)
	{
	    shapeNum = 1;
	}
	else if (radioRoundSq.getState () == true)
	{
	    shapeNum = 2;
	}
	else if (radioCircle.getState () == true)
	{
	    shapeNum = 3;
	}
	else if (radioSemiCircle.getState () == true)
	{
	    shapeNum = 4;
	}
    }


    public void actionPerformed (ActionEvent evt)
    {
	if (evt.getSource () == animateBttn)
	{
	    animate = true;
	}

	try
	{
	    width = Integer.parseInt (size.getText ());
	}
	catch (Exception e)
	{
	    width = 50;
	    System.out.println ("Error in entering an integer number, default 50 is used");
	}
	repaint ();
    }
}
