/* <applet code="RadioactiveDecay_ptplot.class" width=500 height=400> 
   Run Applet </applet> */
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import ptolemy.plot.*;

public class RadioactiveDecay_ptplot extends Applet {
    private int N_0=1000;        // initial number of part.
    private double t_end=100;    // end time of simulation
    private double decay_const=0.001; // decay constant lambda
    private double dt=1;         // time increment for saving
    private double prob=decay_const*dt; // decay probability
    public int[] N_simu,N_exact; // the array for saving results
    public static Plot plot1;

    /** The main routine for running the program as an application */
    public static void main(String[] args) {
	Applet applet = new RadioactiveDecay_ptplot();
	Frame frame = new Frame("Radioactice Decay using PTPlot");
	frame.addWindowListener(new WindowAdapter() {  
            // Handle window close requests
	    public void windowClosing(WindowEvent e) { System.exit(0); } });
	frame.add("Center",applet);    // add applet to the window
	applet.init();                 // start applet
	frame.pack();
	frame.show();                  // display window on screen
    }

    /** The actual main program, started by a browser or by the main method 
	Calculate a radioactive decay and plot the resulting points using
	the PTPlot classes. Compare with the exact result. */
    public void init() {
	int steps, N_save, N;
        double jump;

        steps = (int)(t_end/dt)+1;
	N_simu=new int[steps];
	N_exact=new int[steps];
        N_exact[0]=N_0;
        N_simu[0]=N_0;
        N_save=N_0;
        N=N_0;
        // Advance : time steps
        for (int t=0; t<steps-1; t++) {
            // transitions until the next measure point (dt intervals)
            for (int i=0; i<N_save; i++) {
                jump=Math.random();
                if (jump < prob) N--; }
            N_save=N;
            // save the number of particles in an array
            N_simu[t+1]+=N;
            N_exact[t+1]=(int)(N_0*Math.exp(-decay_const*t));           
        }

        // start a new plot and plot the points
        plot1 = new Plot();
        add(plot1);	
       
        int t_max=(int)t_end;	
        // set the size of the fonts for title and labels
        plot1.setLabelFont("Serif-bold-16");
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
	for (int i=0; i<=N_0; i+=(int)(N_0/10)) {
	    plot1.addYTick(Integer.toString(i),i); 
        }
	// plot the points and connect them
	boolean connect=false;
	for (int t=0; t<steps; t++) {	    
	    plot1.addPoint(0,t,N_simu[t],connect);
	    plot1.addPoint(1,t,N_exact[t],connect);
	    if (connect==false) connect=true; }
    }
}


