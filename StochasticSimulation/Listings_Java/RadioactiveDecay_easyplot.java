/* <applet code="RadioactiveDecay_easyplot.class" width=500 height=400> Run Applet </applet> */
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
/** This is a Radioactive decay simulation using only Java 1.1 features. */
public class RadioactiveDecay_easyplot extends Applet {
    private static int width=500,height=400;

    private int N_0=2000;
    private double t_end=400;
    private double decay_const=0.02;
    private double dt=1;
    private double prob=decay_const*dt;
    public int[] N_simu,N_exact;

    /** The main method just calls the init() method of the applet and
	opens a window to host the plots */
    public static void main(String[] args) {
	Applet applet = new RadioactiveDecay_easyplot();
	Frame frame = new Frame("Radioactice Decay");
	frame.addWindowListener(new WindowAdapter() {  
            // Handle window close requests
	    public void windowClosing(WindowEvent e) { 
                System.exit(0); } }); // exit from program ?!
	frame.setSize(width,height);   // set size of window
	frame.add("Center",applet);    // add applet to the window
	frame.show();                  // display window on screen
	applet.init();                 // start applet
    }

    /** The actual program, started by the browser or by the main method */
    public void init() {
	int steps, N_save, N;
        double jump;

        steps = (int)(t_end/dt)+1;
	N_simu=new int[steps];
	N_exact=new int[steps];
	N_simu[0]=N_0;
	N_exact[0]=N_0;

	// Here the actual simulation takes place
	N_save=N_0;
	N=N_0;
	for (int t=0; t<t_end; t++) {
	    for (int i=0; i<N_save; i++) {
		jump=Math.random();
		if (jump < prob) N--; }
	    N_save=N;
	    N_simu[t+1]=N;
	    N_exact[t+1]=(int)(N_0*Math.exp(-decay_const*t)); }
    }

    /** Here we plot the points and repaint it,
        if the window gets resized. */
    public void paint(Graphics g) {
	int x1,x2,y1,y2;
	double scale_x,scale_y;

	// get the size of the available canvas
	int width=this.getSize().width;
	int height=this.getSize().height;

	// Calculate scaling factors to use the whole plotting area
	scale_x=(double)width/t_end;
	scale_y=(double)height/N_0;

	// Plot all the points without axes -- scale it
	for (int t=1; t<t_end-1; t++) {
            // simulation
	    y1=(int)(height-N_simu[t-1]*scale_y); 
	    y2=(int)(height-N_simu[t]*scale_y);
	    x1=(int)((t-1)*scale_x); x2=(int)(t*scale_x);
	    g.setColor(Color.red);  
            g.drawLine(x1,y1,x2,y2);
            // exact result
	    y1=(int)(height-(double)N_exact[t-1]/N_0*height); 
	    y2=(int)(height-(double)N_exact[t]/N_0*height);
	    g.setColor(Color.black);  
            g.drawLine(x1,y1,x2,y2); }
    }
}



