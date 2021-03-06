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
/** Program, which can be run as an applet or an application */

import java.applet.*;
import java.awt.*;     
import java.awt.event.*;

public class TestAppletApplication extends Applet implements Runnable {
    // Thread variables
    Thread current,calcThread;
    static TestAppletApplication prg;

    // AWT Variables
    static int XSize=500, YSize=500;
    static Frame f;

    /** Constructor: set up the Window/Panel with the GUI here */
    public TestAppletApplication() { } // empty constructor

    /** this is called by the application directly or by the browser 
        of the applet */
    public void start() {
        calcThread.start();  // calls the run() method !!
    }

    /** stops the calculation thread, if applet is stopped */
    public void stop() {
	// Calling stop is depreciated !!!!
        //calcThread.stop();   // call the stop() method !!
    }

    /** Main wrapper to run as application: display window and 
        start calc thread */
    public static void main(String[] args) {
        f = new Frame("Test Program");
        f.setSize(XSize,YSize);
        f.show();

        prg = new TestAppletApplication();
        // Close Window event
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
                { prg.stop(); System.exit(0);} });
        f.add("Center",prg);
        f.repaint();

        // start program = Applet
        prg.init();
        prg.start();
    }

    /** Starts the calculation in the first place.
        Gets called by the main program or directly by the browser. */
    public void init() {
        if (prg==null) {
            prg = this; // do not call the constructor again in the applet
        }
        Setup(); // setup the screen/GUI layout

        current = Thread.currentThread();
        calcThread = new Thread(prg);
        calcThread.setPriority(current.getPriority()-1);
    }

    /** The Screen/GUI setup method */
    public void Setup() {
        // here we would put the GUI, event handlers for buttons, etc. 
    }


    /** this is the run() method of the calculation thread */
    public void run() {
        // here we start with the caluclations
    }

}
