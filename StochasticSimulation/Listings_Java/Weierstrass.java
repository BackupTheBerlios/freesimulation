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
/**
 * Weierstrass.java
 *
 *
 * demonstrate plotting with ptplot, AWT, Events
 * and using our own simulation package
 *
 * Created: Sun Nov 22 19:13:21 1998
 *
 * @author Francesco Petruccione
 * @author Peter Biechele
 * @version 1.0
 *
 */
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import ptolemy.plot.*;
import simulation.*;

// To use Ptplot write a subclass of PlotApplet
public class Weierstrass extends PlotApplet {
  public static int Nsteps = 1000;
  public final static int JMAX=10000;
  public static int position=0;
  public static Random rand = new Random();
  public static int[] walk,time;
  public static int m;
  public static int jump;
  public static double sum;
  public static double prob0;
  public static double probM;
  public static double U;  
  public static double a=2.;
  public static double b=1.5;
  public static double alpha=Math.log(a)/Math.log(b);
  public static double am;
  // define necessary streams and readers for input
  public static Reader reader_buffer;
  public static BufferedReader input;
  public static String instring;
  
  // File Handling variables
  public static final String filename="RW1D.dat";
  public static FileWriter fileoutput;

  // Needed for Frame
  public static Frame frame;

  public Weierstrass() {
  }

  public static void main(String[] args) throws IOException {
    // instantiate the keyboard stream
    reader_buffer = new InputStreamReader(System.in);
    // instantiate the buffered reader
    input = new BufferedReader(reader_buffer);

    Nsteps=0;
    // read the input, until a correct number is input
    while (Nsteps<=10) {
      // tell the user to input a number
      System.out.print(" How many steps ? ");
      // read a whole line of input
      instring = input.readLine();
      // convert String to Integer
      Nsteps=Integer.parseInt(instring);
    }

    


    // Here the actual simulation --------------------------
    time = new int[Nsteps+1];
    walk = new int[Nsteps+1];
    
    m=-1;
    prob0=(a-1)/(a);
        

    for (int i=0; i<Nsteps; i++) {
	time[i]=i;
	walk[i]=position;
	
	m=m-1;
	sum=1;
	U=rand.nextDouble();
	probM=1;
	jump=1; 
	
	if (U <= prob0) {
	    m=0;
	    jump=1;
	}
	else {
	    am=1/a;
	    for (int j=1; j<JMAX; j++){
		probM+=am;
		if (U <= (prob0*probM) ) {
		    m=j;
		    jump *= b;
		    break;
		}
		am/=a;
	    }
	}
	
	//  System.out.println(jump);
	
	if (rand.nextDouble() < 0.5) {
	    position=position-jump; 
	}
	else {
	    position=position+jump;
	}
    }
    walk[Nsteps]=position;
    time[Nsteps]=Nsteps;
    // end of simulation -------------------------------------

    // create a frame = container
    frame = new Frame("Weierstrass Random Walk"); 
    frame.setSize(600,600);
    // this calls the constructor
    Applet applet = new Weierstrass();
    // add it to the frame: BorderLayout is defualt Layout Manager
    frame.add("Center",applet);
    frame.show();
    // the quit button should work now
    frame.addWindowListener(new WindowAdapter() {  // Handle window close requests
      public void windowClosing(WindowEvent e) { System.exit(0); } // exit ?!
    });
    // call the init method of this class for plotting
    applet.init();
    
    // print the plot
    //printScreen();

    // save result to file for further treatment
    //saveToFile();
  }

  /** Plot the actual data */
  public void init() {

    super.init();
    Plot plot = (Plot)plot();

    plot.setTitle("Weierstrass Random Walk in 1D (alpha="+alpha+")");
    // use our plot routine
    Plotting.plot2D(plot,0,
		    ArrayConversion.int2Double1D(time),
    		    ArrayConversion.int2Double1D(walk));

  }

  /** Method saves an array to a file :: ASCII */
  public static void saveToFile() throws IOException {
    System.out.println(" Writing to file "+filename);    
    // instantiate an output stream for file writing:
    // here it is a FileWriter, which is a subclass of FileOutputStream
    // the writer obeys locale settings !!
    boolean append = false;
    fileoutput = new FileWriter(filename,append);
    for (int i=0; i<Nsteps+1; i++) {
      // convert the numbers to human readable Strings
      String dummy = Integer.toString(i)+" ";
      dummy += Integer.toString(walk[i]);
      // add a newline character
      dummy+="\n";
      // write to file
      fileoutput.write(dummy,0,dummy.length());
    }
    // close the file
    fileoutput.close();
  }

  /** Printing into a file or to a printer */
  public static void printScreen() {
    // disply a dialog for the printing
    Toolkit toolkit = frame.getToolkit();
    PrintJob job = toolkit.getPrintJob(frame,"Random Walk Printing",(Properties)null);
    // if user clicked cancel, do nothing
    if (job==null) return;
    Graphics gprint = job.getGraphics();
    // plot/paint the page
    frame.printAll(gprint);
    // send it to printer/file    
    gprint.dispose();
    // end the printjob
    job.end();    
  }

    // Has to be overwritten for some unknown reason !
    public String getParameter(String name) {
	return null;
    }

} // RandomWalk

