import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

import ptolemy.plot.*;

/** Simple class to show the use of PTPlot 
    It just displays an empty plot area with tickmarks */
public class Ptplot_Demo2 extends Applet {
  private static int width=400,height=600;  // size of window or applet

  public static void main (String[] args) {

    Frame frame = new Frame("Make Plots using PtPlot"); // create frame
    frame.addWindowListener(new WindowAdapter() {  
        // Handle window close requests
        public void windowClosing(WindowEvent e) { System.exit(0); } });
    frame.setSize(width,height);   // set size of window

    Applet applet = new Ptplot_Demo2(); // Create a panel for the applet
    frame.add("Center",applet);    // add applet to the window
    frame.show();                  // display window on screen
    applet.init();                 // start applet
  }

  /** The actual plot routine */
  public void init() {
    // Create a new Plot    
    Plot plot = new Plot();
    // initialize the new plot
    this.add(plot);
    // set title of plot
    plot.setTitle(" Test Plot");      // set plot title
  }
}


