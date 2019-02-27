import java.applet.Applet;
import java.awt.*;

public class MyName extends Applet
{
    public void paint (Graphics name)
    {
	name.setFont (new Font ("Dialog", Font.BOLD, 24));
	name.drawString ("TONY CHEN", 250, 120);
    }
}


