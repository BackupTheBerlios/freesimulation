
/**
 * DogFlea.java
 *
 *
 * Created: Thu Jan 21 11:30:31 1999
 *
 * @author Peter Biechele
 * @version
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;
import ptolemy.plot.*;
import simulation.*;

public class DogFlea extends Applet {
        
    // Input values for Symphony
    public double Nd = 1000;
    public double n0d = 10;

    public int N = (int)Nd;  // number of fleas
    public int n0 = (int)n0d; // initial number of fleas on dog 1

    public int steps = 10000; // How many time steps to be done
    int[] flea; // to which dog belongs the flea ? (1 or 0)

    // Output value for Symphony
    public int na, nb;

    // Parallel FLAG: 0 No parallel job, 1 use Symphony
    int SYMPHONY=1;

    Random rand = new Random();
    Plot plot;

    public void run() {
	int choose;
	boolean connect=false;

	instVars();
	initial();
	na = calck();
	
	if (SYMPHONY==0) {
	    System.out.println("s=0:  "+na); 
	    plot = new Plot();
	    plot.setTitle("Difference of fleas versus time");
	    this.add(plot);
	    this.repaint();
	}

	for (int s=1; s<steps; s++) {
	    choose=Distribution.nextInteger(rand,N-1);
	    flea[choose]=1-flea[choose];
	    na = calck();

	    if (SYMPHONY==0) {
		plot.addPoint(0,s,na,connect);
		if (connect==false) connect=true;
		if (s%100==0) plot.repaint();
	    }
	} 
	nb=N-na;
	if (SYMPHONY==0) plot.repaint();
    }

    int calck() {
	int sum=0;
	for (int i=0; i<N; i++) {
	    sum+=flea[i]; }
	return sum;
    }

    void initial() {
	for (int i=0; i<N; i++) {
	    if (i<n0) flea[i]=1;
	}
    }

    void instVars() {
	flea = new int[N];
    }

    public DogFlea() {
    }
    
    public static void main(String[] args) {
	Frame f = new Frame("Dog-Flea Model");
	f.setSize(600,600);

	DogFlea prg=new DogFlea();
	f.add(prg);
	f.show();

        // Close Window event
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
                { System.exit(0);}
        });

	prg.init();
	prg.start();
    }

    public void init() {
    }
    
    public void start() {
	run();
    }

    public void stop() {
    }
    
} // DogFlea
