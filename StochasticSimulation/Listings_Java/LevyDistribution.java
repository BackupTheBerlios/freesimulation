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
import ptplot.*;

public class LevyDistribution extends PlotApplet {
    private static int width=500,height=400;

   
    private double dt=0.01;         // time increment for saving
    private double t_end=100.;
  //private int steps=100;
    public double[] C_simu;  // the array for saving the realization

    /** The main routine for running the program as an application */
    public static void main(String[] args) {
	Applet applet = new LevyDistribution();
	Frame frame = new Frame("LevyDistribution Process");
	frame.addWindowListener(new WindowAdapter() {  // Handle window close requests
	    public void windowClosing(WindowEvent e) { System.exit(0); } // exit ?!
	});
	frame.setSize(width,height);   // set size of window
	frame.add("Center",applet);    // add applet to the window
	frame.show();                  // display window on screen
	applet.init();                 // start applet
    }

    /** The actual main program, started by a browser or by the main method 
	Calculate a radioactive decay and plot the resulting points using
	the PTPlot classes. Compare with the exact result. */
    public void init() {
	int steps=(int) (t_end/dt) +1;
	double gamma;
        double W;
        double Help_1;
        double Help_2;
        double Help_3;
        L = new double[steps];
	
	// Advance : time steps
        for (int t=0; t<t_end; t++) {

         gamma = (Math.random() -0.5)*Math.Pi;
         W    = -Math.log(1.0 -Math.random());
         Help_1 = (Math.sin(alpha*gamma))/Math.pow(Math.cos(gamma),1./alpha));
         Help_2 = Math.cos((1-alpha)*gamma);
         Help_3 = Math.pow(Help_2/W,(1.-alpha)/2.);
         L[t] = Help_1*Help_3;
          }

  // Calculate Histogram
       int bins=20;
       int number = nsteps;
      int[] histo = new int[bins];
    for (int i=0; i<number; i++) {
      int flag=0;
      for (int j=0; j<bins-1; j++) {
        if (data[i] < points[j]) {
          histo[j]++;
          flag=1;
          break;
        }
      }
      if (flag==0) histo[bins-1]++;
    }                                 


        
	// start anew plot and plot the points
	super.newPlot();
	super.init();
	int t_max=(int) t_end;
	plot().setTitle("Levy Distribution");	// Title of plot
	plot().setMarksStyle("none"); // dots, points or various
	plot().setXLabel("time t");   // set the labels of the axes
	plot().setYLabel("x");
	plot().setXRange(0,t_max);    // set the x range
	plot().setGrid(true);  // Grid or not ?
	plot().setYLog(false); // logarithmis plot ?
	plot().setBars(false); // should I use bars ?
	/* // Create the ticks for the axis 
	for (int i=0; i<=t_max; i+=50) {
	    plot().addXTick(Integer.toString(i),i);
	}
	for (int i=0; i<=N_0; i+=100) {
	    plot().addYTick(Integer.toString(i),i);
	    }   */
	// plot the points and connect them
	boolean connect=false;
	for (int t=0; t<t_end; t++) {	    
	    plot().addPoint(0,t,C_simu[t],connect);
	    if (connect==false) connect=true;
	}
    }
}






