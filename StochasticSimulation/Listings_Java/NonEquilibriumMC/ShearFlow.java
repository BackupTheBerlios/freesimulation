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
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import de.berlios.StochasticSimulation.*;
import ptolemy.plot.*;

public class ShearFlow extends Applet implements Runnable {

    // Debugging ( = -1, 0, 1 , 2)
    final int DEBUG = -1;

    // Initial Screen Size parameters
    final static int XSize = 400;
    final static int YSize = 400;

    // Parameters
    public double Realparam = 10;
    int Real = (int) Realparam; // number of realizations
    public double L = 1; // distance of walls
    public double Mparam = 22; 
    int M = (int) Mparam;  // number of intervals +1 = numb. of points
    double dl=L/(M-1); // space discretization
    public double deltau = 0.01; // velocity scale
    public double U = 1; // velocity of the walls
    public double Reynolds = 10; // Reynolds number
    double relaxationTime = Reynolds/(4*Math.PI*Math.PI);
    public double Nrelax = 5;
    double tstop = Nrelax*relaxationTime; // endtime
    // time where the velocities get saved in relaxation times
    public double[] tsave = {0.1, 0.5, 1.0, 2.0, 5.0};
    int Nsave=tsave.length;

    // Variables
    double factor = 1/(Reynolds*dl*dl);
    public int[] N;
    public double[][] Nmean;
    Random rand = new Random();
 
    // AWT Variables
    static Frame f;
    Plot plot1, plot2;
    int color=1;
    Label l1,l2;
    Panel p1=new Panel();

    // Thread variables
    Thread current,calcThread;
    static ShearFlow prg;

    public static void main(String[] args) {
        f = new Frame("Shear Flow");
        f.setSize(XSize,YSize);
        f.show();

        prg = new ShearFlow();
        // Close Window event
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
                { prg.stop(); f.dispose(); System.exit(0);} });
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
            prg = new ShearFlow();
        }
        current = Thread.currentThread();
        calcThread = new Thread(prg);
        calcThread.setPriority(current.getPriority()-1);
    }

    public void stop() {
        calcThread.stop();   // call the stop() method !!
    }

    /** this is called by the application directly or
        by the browser of the applet */
    public void start() {
        calcThread.start();  // calls the run() method !!
    }

    /** Instantiate Variables */
    void instantVars() {
	N = new int[M];
	Nmean = new double[M][Nsave];
	initPlots();
    }

    void initPlots() {
	// Plot 1
	plot1.setTitle("Shear Flow time evolution");
	plot1.setButtons(true);
	plot1.setMarksStyle("points");
	plot1.setXLabel("x");
	plot1.setYLabel("N(x,t)");
	plot1.setPointsPersistence(M); 
	// Plot 2
	plot2.setTitle("Shear Flow time evolution");
	plot2.setButtons(true);
	plot2.setMarksStyle("points");
	plot2.setXLabel("x");
	plot2.setYLabel("Nmean(x,t)");
	plot2.setPointsPersistence(M); 
    }
	
    public ShearFlow() {
	// Window Layout
	this.setLayout(new GridLayout(3,1));
	// plots
	plot1 = new Plot();
	this.add(plot1);
	// Labels
	p1.setLayout(new GridLayout(2,1));
	l1=new Label("Time t = 0.0");
	p1.add(l1);
	l2=new Label("Realizations = 0 of "+Real);
	p1.add(l2);
	this.add(p1);
	// final plot
	plot2 = new Plot();
	this.add(plot2);
    }

    /** This is called when the thread is started */
    public void run() {
	double Wtot, t;	
	int cell, Wmax, steps;
	int savecount;

        instantVars();

	for (int r=0; r<Real; r++) {
	    initial();

	    savecount=0; t=0; steps=0;
	    while (t<tstop) {
		steps++;
		Wtot=totalTransitionRate();
		// time increments (exponential distr. time steps)
		t+=-(Math.log(rand.nextDouble())/Wtot);
		if (DEBUG>=1 && steps%100 == 0) 
		    System.out.println("t= "+t/relaxationTime);
		// decide which transition takes place at which cell
		Wmax=Math.abs(N[maxTransitionRate()]);
		cell=Distribution.nextInteger(rand,M-1);
		while ( (Wmax*rand.nextDouble()) > Math.abs(N[cell]) ) {
		    cell=Distribution.nextInteger(rand,M-1); }
		if (DEBUG>=2) System.out.println("Wmax= "+Wmax+"  "+Wtot);
		if (DEBUG>=2) System.out.println("cell= "+cell);
		// jumps of the velocity particle
		if ( (cell>0 && rand.nextDouble() < 0.5) || cell==M-1) {
		    if (N[cell] < 0) {		
			N[cell]++;
			N[cell-1]--; }  
		    else {
			N[cell]--;
			N[cell-1]++; } } 
		else {
		    if (N[cell] < 0) {		
			N[cell]++;
			N[cell+1]--; }
		    else {
			N[cell]--;
			N[cell+1]++; } }
		// correct for the boundary conditions
		boundary();
		// Output (only for the first realization)
		if (r==0) {
		    if (steps%200==0) {
			plotCurve(t); 
			color++;
			if (color>2) color=1; } }
		// save the velocities for building averages over ensemble
		if (t>(tsave[savecount]*relaxationTime)) {
		    for (int i=0; i<M; i++) {
			Nmean[i][savecount]+=N[i]; }
		    savecount++; }
	    } // End time loop
	    l2.setText("Realizations = "+(r+1)+" of "+Real);
	    l2.repaint();
	} // End realizations loop
	color=1;
	for (int j=0; j<Nsave; j++) {
	    if (DEBUG>=0) System.out.println("\nt="+tsave[j]);
	    boolean connect=false;
	    for (int i=0; i<M; i++) {
		Nmean[i][j]/=Real; 
		if (DEBUG>=0) System.out.print(deltau*Nmean[i][j]+" ");
		plot2.addPoint(color,i*dl,deltau*Nmean[i][j],connect);
		if (connect==false) connect=true;
	    }
	    color++;
	}
	plot2.repaint();
    } 

    /** Plotting the curve */
    void plotCurve(double t) {
	boolean connect=false;
	for (int i=0; i<M; i++) {
	    if (DEBUG>=1) {		
		System.out.print(N[i]+" "); }
	    else {
		plot1.addPoint(color,i*dl,N[i]*deltau,connect);
		if (connect==false) connect=true; }
	}
	if (DEBUG>=1) System.out.println(); 
	plotExact(t);
	plot1.repaint();
	l1.setText("Time t = "+t/relaxationTime);
	l1.repaint();
    }

    /** Compute and plot exact results */
    void plotExact(double t) {
	double u,y,suminf;
	boolean connect=false;
	for (int i=0; i<M; i++) {
	    y=i*dl;
	    suminf=0;
	    for (int n=1; n<100; n++) { // infinite sum
		suminf+=2/(Math.PI*n)*Math.sin(2*Math.PI*n*y)*
		    Math.exp(-4*Math.PI*Math.PI*n*n*t/Reynolds); }
	    u=-1+2*y+suminf;
	    plot1.addPoint(0,y,u,connect);
	    if (connect==false) connect=true;
	}
    }

    /** Boundary Conditions: here shear */
    void boundary() {
	N[0]=(int) (-U/deltau);
	N[M-1]=(int) (U/deltau);
    }

    /** initial configuration */
    void initial() {
	for (int i=0; i<M; i++) {
	    N[i]=0;
	}
	boundary();
    }

    /** compute the maximum of the transition rates:
	Do not use the factor "factor" !! */
    int maxTransitionRate() {
	int max=0;
	for (int i=1; i<M; i++) {
	    if (N[i]>N[max]) max=i; }
	return max;
    }

    /** Compute the total transition rate */
    double totalTransitionRate() {
	double sum=0;

	for (int i=0; i<M; i++) {
	    sum+=Math.abs(N[i]); }
	sum*=factor;
	return sum;
    }

}
