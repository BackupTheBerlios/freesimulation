/**
 * 
 *     Copyright (C) 2002  P. Biechele, F. Petruccione
 *
 *
 *     This program is free software; you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation; either version 2 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program; if not, write to the Free Software
 *     Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *
 *     
 */
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


