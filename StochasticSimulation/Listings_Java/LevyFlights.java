
/**
 * LevyFlights.java
 *
 *
 * Created: Thu Dec  3 10:25:35 1998
 *
 * @author Peter Biechele
 * @version
 */

import simulation.*;
import java.applet.*;
import ptplot.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class LevyFlights extends PlotApplet {
    final int N = 20000;
    final double alpha = 0.8;
    double[] process = new  double[N];

    public void init() {

	super.newPlot();
	super.init();
	plot().setTitle("Levy Process");
	Plotting.plot2D(this,0,process,N);

    }

    public LevyFlights() {
	Random rand = new Random();
	
	process[0]=Distribution.nextLevy(rand,alpha);
	for (int i=1; i<N; i++) {
	    process[i]=process[i-1]+Distribution.nextLevy(rand,alpha);
	}
    }
    
    public static void main(String[] args) {
	Frame myframe = new Frame("LevyFlights - Pebi");
	WindowListener listen = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        };
	myframe.addWindowListener(listen);
	myframe.setSize(500,600);
	myframe.setBackground(Color.white);
 	Applet a = new LevyFlights();
	myframe.add(a);
	myframe.show();
	a.init();
    }
    
} // LevyFlights
