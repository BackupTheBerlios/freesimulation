import java.applet.*;
import java.awt.*;

/* Create an Applet */
public class ParamApplet extends Applet {
  public int NumberPoints;
  public String text_param;

  /* Get the parameters from the HTML file */
  public void init() {
    NumberPoints=Integer.parseInt(this.getParameter("NumberofPoints"));
    text_param=this.getParameter("DisplayText");
  } 

  /* Display the parameters in the window */
  public void paint(Graphics g) {
    g.drawString("Parameter NumberofPoints is "+NumberPoints,20,50);
    g.drawString("Parameter DisplayText is \""+text_param+"\"",20,80);
  }

}

