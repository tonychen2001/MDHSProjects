import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

/*
    This program was made using applet. It allows to the user to
    select a color, shape and input the size of an object to be displayed.
    The user can also press a 'draw' button to render an object or
    press an 'animate' button to make the object move from left to right.
    The graphic interface is designed using flow, grid, and border layouts.

*/

public class Applet1 extends Applet implements ItemListener, ActionListener
{
    // declare variable to store the start positions of the shapes
    int startX = 50;
    int startY = 50;
    // declare variable to store the maximum size of the shape to be drawn
    int shapeSizeLimit = 200;

    // variables to tell the program what shape and colour to display
    int colourNum, shapeNum;

    String curColor;    // variable to store current color of the shape
    Font textFont;
    int width;          // variable to store the width of the shape
    boolean animate;    // variable to keep track of animating the object or not

    // panel for displaying and animating the shape
    Panel graphicPanel = new Panel ();
    // entire top panel
    Panel northPanel = new Panel ();

    Panel colorPanel = new Panel ();    // panel for the color
    Panel shapePanel = new Panel ();    // panel for the shape
    Panel shapeSizePanel = new Panel ();    // panel for the size input
    Panel shapeAttributesPanel = new Panel ();  // panel to display the current attributes
    Panel actionsPanel = new Panel ();      // panel for the draw and animate buttons

    // declare radio buttons to keep track of the colour
    CheckboxGroup colour = new CheckboxGroup ();
    Checkbox radioBlue = new Checkbox ("Blue", colour, false);
    Checkbox radioYellow = new Checkbox ("Yellow", colour, false);
    Checkbox radioRed = new Checkbox ("Red", colour, false);
    Checkbox radioMagenta = new Checkbox ("Magenta", colour, false);

    // declare radio buttons to keep track of the shape
    CheckboxGroup shape = new CheckboxGroup ();
    Checkbox radioSquare = new Checkbox ("Square", shape, false);
    Checkbox radioRoundSq = new Checkbox ("Round square", shape, false);
    Checkbox radioCircle = new Checkbox ("Circle", shape, false);
    Checkbox radioSemiCircle = new Checkbox ("Semi circle", shape, false);

    // text field to allow the user to enter the size of the object
    TextField size = new TextField (3);

    // label to display the current attributes of the object
    Label attributes = new Label ("Shape Attributes:", Label.LEFT);

    // button to allow the user to render the object
    Button drawBttn = new Button ("Draw");
    // button to allow the user to start an animation, moving the object from left to right
    Button animateBttn = new Button ("Animate");

    public void init ()
    {

	// initialize the variables so the program will not draw an object when launching
	// the program
	colourNum = -1;
	shapeNum = -1;

	setBackground (Color.LIGHT_GRAY);
	graphicPanel.setBackground (Color.GRAY);
	textFont = new Font ("SERIF", Font.BOLD, 24);

	animate = false;

	// register a listener to the radio buttons that control the color
	radioBlue.addItemListener (this);
	radioYellow.addItemListener (this);
	radioRed.addItemListener (this);
	radioMagenta.addItemListener (this);

	// label for the color panel
	Label lColor = new Label ("Choose one of the following colors");
	// add the label and radio buttons to the color panel
	colorPanel.add (lColor);
	colorPanel.add (radioBlue);
	colorPanel.add (radioYellow);
	colorPanel.add (radioRed);
	colorPanel.add (radioMagenta);
	// set the layout of the color panels to 5 rows and 1 column
	colorPanel.setLayout (new GridLayout (5, 1));

	// register a listener to the radio buttons that control the shape
	radioSquare.addItemListener (this);
	radioRoundSq.addItemListener (this);
	radioCircle.addItemListener (this);
	radioSemiCircle.addItemListener (this);

	// label for the shape panel
	Label lShape = new Label ("Choose one of the following shapes");
	// add the label and radio buttons to the shape panel
	shapePanel.add (lShape);
	shapePanel.add (radioSquare);
	shapePanel.add (radioRoundSq);
	shapePanel.add (radioCircle);
	shapePanel.add (radioSemiCircle);
	// set the layout of the shape panel to 5 rows and 1 column
	shapePanel.setLayout (new GridLayout (5, 1));

	// label for the shape size panel
	Label sSize = new Label ("Please input shape size: ");
	// add the label and text field to the size panel
	shapeSizePanel.add (sSize);
	shapeSizePanel.add (size);
	shapeSizePanel.setLayout (new GridLayout (1, 2));

	// panel to store the size panel and label displaying the attributes
	Panel sizeAttrPanel = new Panel ();
	sizeAttrPanel.setLayout (new GridLayout (5, 1));
	sizeAttrPanel.add (shapeSizePanel);
	sizeAttrPanel.add (new Label (""));
	sizeAttrPanel.add (attributes);

	// register a listener to the draw and animate button
	drawBttn.addActionListener (this);
	animateBttn.addActionListener (this);

	// add the 'draw' and 'animate' buttons to the 'actionsPanel'
	actionsPanel.add (drawBttn);
	actionsPanel.add (animateBttn);
	actionsPanel.setLayout (new FlowLayout (2));

	// set the entire top panel layout to 4 sections
	northPanel.setLayout (new FlowLayout (5));

	// add panels to the top panel
	northPanel.add (colorPanel);
	northPanel.add (shapePanel);
	northPanel.add (sizeAttrPanel);
	northPanel.add (actionsPanel);

	// set the layout and display all the panels
	setLayout (new BorderLayout ());
	add (graphicPanel, BorderLayout.CENTER);
	add (northPanel, BorderLayout.NORTH);
    }


    public void paint (Graphics gc)
    {
	// get graphic from 'graphicsPanel' to display shapes on it
	Graphics g = graphicPanel.getGraphics ();
	// make sure that the 'graphicsPanel' is cleared before displaying anything new
	g.clearRect (startX, startY, width, width);

	// switch statement to determine the colour based
	// on 'colourNum'
	switch (colourNum)
	{
		// if colourNum == 1, colour is blue
	    case 1:
		g.setColor (Color.blue);
		curColor = "Blue";
		break;
		// if colourNum == 2, colour is yellow
	    case 2:
		g.setColor (Color.yellow);
		curColor = "Yellow";
		break;
		// if colourNum == 1, colour is red
	    case 3:
		g.setColor (Color.red);
		curColor = "Red";
		break;
		// if colourNum == 1, colour is magenta
	    case 4:
		g.setColor (Color.magenta);
		curColor = "Magenta";
		break;
	}

	// switch statement to determine the shape based
	// on 'shapeNum'
	switch (shapeNum)
	{
	    case 1:     // if shapeNum == 1, shape is a square
		// update current attributes
		attributes.setText ("Shape Attributes: " + curColor + ", Square, " + " W: " + width);

		// animate if the user has pressed the 'animate' button
		if (animate)
		{
		    for (int i = startX ; i < 680 ; i++)
		    {
			g.fillRect (i, startY, width, width);
			try
			{
			    Thread.sleep (10);
			}
			catch (Throwable e)
			{
			    JOptionPane.showMessageDialog (null, "Exception or error " + e + " has occured!", "Exception or Error", 0);
			}
			g.clearRect (i, startY, width, width);
		    }
		}
		g.fillRect (startX, startY, width, width);
		break;
	    case 2:     // if shapeNum == 2, shape is a round-square
		// update current attributes
		attributes.setText ("Shape Attributes: " + curColor + ", Round square, " + " W: " + width);

		// animate if the user has pressed the 'animate' button
		if (animate)
		{
		    for (int i = startX ; i < 680 ; i++)
		    {
			g.fillRoundRect (i, startY, width, width, 25, 25);
			try
			{
			    Thread.sleep (10);
			}
			catch (Throwable e)
			{
			    JOptionPane.showMessageDialog (null, "Exception or error " + e + " has occured!", "Exception or Error", 0);
			}
			g.clearRect (i, startY, width, width);
		    }

		}
		g.fillRoundRect (startX, startY, width, width, 25, 25);
		break;
	    case 3:     // if shapeNum == 3, shape is a circle
		// update current attributes
		attributes.setText ("Shape Attributes: " + curColor + ", Circle, " + " R: " + width);

		// animate if the user has pressed the 'animate' button
		if (animate)
		{
		    for (int i = startX ; i < 680 ; i++)
		    {
			g.fillOval (i, startY, width, width);
			try
			{
			    Thread.sleep (10);
			}
			catch (Throwable e)
			{
			    JOptionPane.showMessageDialog (null, "Exception or error " + e + " has occured!", "Exception or Error", 0);
			}
			g.clearRect (i, startY, width, width);
		    }
		}
		g.fillOval (startX, startY, width, width);
		break;
	    case 4:     // if shapeNum == 4, shape is a semi-circle
		// update current attributes
		attributes.setText ("Shape Attributes: " + curColor + ", Semi-Circle, " + " R: " + width);

		// animate if the user has pressed the 'animate' button
		if (animate)
		{
		    for (int i = startX ; i < 680 ; i++)
		    {
			g.fillArc (i, startY, width, width, 0, 180);
			try
			{
			    Thread.sleep (10);
			}
			catch (Throwable e)
			{
			    JOptionPane.showMessageDialog (null, "Exception or error " + e + " has occured!", "Exception or Error", 0);
			}
			g.clearRect (i, startY, width, width);
		    }
		}
		g.fillArc (startX, startY, width, width, 0, 180);
		break;
	}

	animate = false;
    }


    // to keep track of the attributes by checking the states of the radio buttons
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
	else
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
	else
	{
	    shapeNum = 4;
	}
    }


    // to track if the user pressed the 'draw' or 'animate' button
    public void actionPerformed (ActionEvent evt)
    {
	// tell the program to animate the shape if the user has
	// pressed the 'animate' button
	if (evt.getSource () == animateBttn)
	{
	    animate = true;
	}

	// validate if user does not choose a color
	if (colour.getSelectedCheckbox () == null)
	{
	    JOptionPane.showMessageDialog (null, "No colour is selected, default colour 'Blue' will be used", "Exception or Error", 0);
	    colourNum = 1;
	    radioBlue.setState (true);
	}

	// validate if user does not choose a shape
	if (shape.getSelectedCheckbox () == null)
	{
	    JOptionPane.showMessageDialog (null, "No shape is selected, default shape 'Square' will be used", "Exception or Error", 0);
	    shapeNum = 1;
	    radioSquare.setState (true);
	}

	try
	{
	    width = Integer.parseInt (size.getText ());
	    // validate if user enters a size that's not within the limit
	    if (width > shapeSizeLimit || width <= 0)
	    {
		JOptionPane.showMessageDialog (null, "Shape Size must be between 1 and " + shapeSizeLimit + "! Default size '50' is used", "Exception or Error", 0);
		width = 50;
		size.setText ("" + width);
	    }


	}
	// validate if user does not enter a size properly
	catch (Throwable e)
	{
	    JOptionPane.showMessageDialog (null, "Exception or error " + e + " has occured! Default size '50' is used", "Exception or Error", 0);
	    width = 50;
	    size.setText ("" + width);
	}
	repaint ();
    }
}
