/* Applet version of Hello World */

import java.applet.*;
import java.awt.*;

public class HelloWorld_Applet extends Applet {
  public void paint(Graphics g) {
    g.drawString("Hello World",25,50); // write text in window
  }
}

