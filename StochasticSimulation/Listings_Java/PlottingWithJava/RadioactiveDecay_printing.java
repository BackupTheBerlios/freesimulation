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
/** Demonstrate printing in Java 1.1 */
 
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import ptolemy.plot.*;

public class RadioactiveDecay_printing extends PlotApplet {
    private static int width=500,height=400;

    private int N_0=1000;        // initial number of part.
    private double t_end=350;    // end time of simulation
    private double decay_const=0.01; // decay constant lambda
    private double dt=1;         // time increment for saving
    private double prob=decay_const*dt; // decay probability
    public int[] N_simu,N_exact; // the array for saving results
    static Plot plot;

    /** Teh main routine for running the program as an application */
    public static void main(String[] args) {
	Applet applet = new RadioactiveDecay_printing();
	Frame frame = new Frame("Radioactice Decay using PTPlot");
	frame.addWindowListener(new WindowAdapter() {  // Handle window close requests
	    public void windowClosing(WindowEvent e) { System.exit(0); } // exit ?!
	});
	frame.setSize(width,height);   // set size of window
	frame.add("Center",applet);    // add applet to the window
	frame.show();                  // display window on screen
	applet.init();                 // start applet

        /* Print the plot in a file or an printer */
        // 1.) get a "connection" to the printer
        Toolkit toolkit = plot.getToolkit();
        // 2.) get a dialog box for the print job
        PrintJob job = toolkit.getPrintJob
            (frame,"Radioactive Decay",(Properties)null);
        if (job != null) {
            // 3.) get the graphics handle
            Graphics pg = job.getGraphics();
            // print all components contained in com
            plot.printAll(pg);
            // send it to printer
            pg.dispose();
            // 4.) close all necessary stuff
            job.end();
        }
         
    }

    /** The actual main program, started by a browser or by the main method 
	Calculate a radioactive decay and plot the resulting points using
	the PTPlot classes. Compare with the exact result. */
    public void init() {
	int steps=(int)(t_end/dt)+1;
	N_simu=new int[steps];
	N_exact=new int[steps];
	N_simu[0]=N_0;
	N_exact[0]=N_0;

	int N_save=N_0;
	int N=N_0;
	// Advance : time steps
	for (int t=0; t<t_end; t++) {
	    // transitions until the next measure point (dt=1 intervals)
	    for (int i=0; i<N_save; i++) {
		double jump=Math.random();
		if (jump < prob) N--;
	    }
	    N_save=N;
	    // save the number of particles in an array
	    N_simu[t+1]=N;
	    N_exact[t+1]=(int)(N_0*Math.exp(-decay_const*t));
	}

	// start anew plot and plot the points
	super.init();
        plot = (Plot)plot();

	int t_max=350;
	plot.setTitle("Radioactive Decay");	// Title of plot
	plot.setMarksStyle("none"); // dots, points or various
	plot.setXLabel("time t");   // set the labels of the axes
	plot.setYLabel("Number of Particles");
	plot.setXRange(0,t_max);    // set the x range
	plot.setGrid(true);  // Grid or not ?
	plot.setYLog(false); // logarithmis plot ?
	plot.setBars(false); // should I use bars ?
	// Create the ticks for the axis 
	for (int i=0; i<=t_max; i+=50) {
	    plot.addXTick(Integer.toString(i),i);
	}
	for (int i=0; i<=N_0; i+=100) {
	    plot.addYTick(Integer.toString(i),i);
	}
	// plot the points and connect them
	boolean connect=false;
	for (int t=0; t<t_end; t++) {	    
	    plot.addPoint(0,t,N_simu[t],connect);
	    plot.addPoint(1,t,N_exact[t],connect);
	    if (connect==false) connect=true;
	}
    }
}
