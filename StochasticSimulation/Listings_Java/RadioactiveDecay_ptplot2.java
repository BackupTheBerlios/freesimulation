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
/* <applet code="RadioactiveDecay_ptplot2.class" width=800 height=450> 
   Run Applet </applet> */
import java.awt.*;
import java.awt.event.*;
import ptolemy.plot.*;
import simulation.*;

public class RadioactiveDecay_ptplot2 extends java.applet.Applet {
    private static int width=800,height=450;

    private int nreal = 1000;     // number of realizations

    private int N_0=100;        // initial number of part.
    private double t_end=100;    // end time of simulation
    private double decay_const=0.001; // decay constant lambda
    private double dt=1;         // time increment for saving
    private double prob=decay_const*dt; // decay probability
    public int[] N_simu, N_exact, N_decay;// the array for saving results
    public static Plot plot1, plot2;

    /** Teh main routine for running the program as an application */
    public static void main(String[] args) {
	java.applet.Applet applet = new RadioactiveDecay_ptplot2();
	Frame frame = new Frame("Radioactice Decay using PTPlot");
	frame.addWindowListener(new WindowAdapter() {  
            // Handle window close requests
	    public void windowClosing(WindowEvent e) { System.exit(0); } });
	frame.setSize(width,height);   // set size of window
	frame.add("Center",applet);    // add applet to the window
	frame.show();                  // display window on screen
	applet.init();                 // start applet
    }

    /** The actual main program, started by a browser or by the main method 
	Calculate a radioactive decay and plot the resulting points using
	the PTPlot classes. Compare with the exact result. */
    public void init() {
	int steps, N, N_save;
        double jump;

        steps = (int)(t_end/dt)+1;
	N_simu=new int[steps];
        N_decay=new int[nreal];
        N_exact=new int[steps];
	N_exact[0]=N_0;

        for (int r=0; r<nreal; r++) {
            N_simu[0]=N_0;
            N=N_0;
            N_save=N_0;
            // Advance : time steps
            for (int t=0; t<steps-1; t++) {
                // transitions until the next measure point (dt intervals) 
                jump=Math.random();
                if (jump < prob*N_0) N--; 
                // save the number of particles in an array
                N_simu[t+1] += N;
                if (r==0) N_exact[t+1]=(int)(N_0*Math.exp(-decay_const*t));
            }
            N_decay[r]=N_0-N;
        }

        // compute mean values
        for (int t=1; t<steps-1; t++) {
            N_simu[t]/=nreal; }

        // start a new plot and plot the points
        plot1 = new Plot();
       
        int t_max=(int)t_end; // for plotting tickmarks !!
        // set the size of the fonts for title and labels
        plot1.setLabelFont("Serif-bold-18");
        plot1.setTitleFont("Serif-bold-24");
 
  	plot1.setTitle("Radioactive Decay");	// Title of plot
  	plot1.setMarksStyle("none"); // dots, points or various
	plot1.setXLabel("time t");   // set the labels of the axes
	plot1.setYLabel("Number of Particles");
	plot1.setXRange(0,t_max);    // set the x range
	plot1.setGrid(true);  // Grid or not ?
	plot1.setYLog(false); // logarithmis plot ?
	plot1.setBars(false); // should I use bars ?
	plot1.setButtons(true);
       // Create the ticks for the axis 
	for (int i=0; i<=t_max; i+=50) {
	    plot1.addXTick(Integer.toString(i),i); }
	for (int i=0; i<=N_0; i+=100) {
	    plot1.addYTick(Integer.toString(i),i); }
	// plot the points and connect them
	boolean connect=false;
	for (int t=0; t<t_max; t++) { 
	    plot1.addPoint(0,t,N_simu[t],connect);
            plot1.addPoint(1,t,N_exact[t],connect);
	    if (connect==false) connect=true; }
        add(plot1);	

        /* Compute the decay distribution */
        Histogram histo;
        histo = new Histogram(N_decay);
        histo.setPointsUniform(0,50);
        histo.estimate();
        plot2 = histo.plot();
	plot2.setBars(false);
        plot2.setImpulses(true,1);
        add(plot2);
        // plot the exact probability distribution: Poisson
        double y;
        connect=false;        
        double dummy = simulation.DataAnalysis.arraySum(histo.getHistogram());
        for (int i=0; i<steps/2; i++) {
            y=simulation.Distribution.Poisson(i,prob*N_0*N_0)*dummy;
            plot2.addPoint(0,i,y,connect);
	    if (connect==false) connect=true; } 
    }
}




