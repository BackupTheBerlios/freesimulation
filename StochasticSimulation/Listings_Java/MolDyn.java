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
 * MolDyn.java
 *
 * uses Velocity Verlet Algorithm (Swope, Andersen, Berens, Wilson, 1982)
 *
 * Algorithms used:
 * ----------------
 *  minimum image convention, 
 *  periodic boundary conditions, (or shear boundary conditions in 2D)
 *  initial configuration on a lattice with velocities according to Maxwell-Boltzmann-Dist.
 *  no cut-off.
 *  
 *
 *
 * important variables:
 * --------------------
 *      pos[dimension][particle no.][time step]  -- position of individual particles 
 *      velocity[][][]                           -- velocity
 *      accel[][][]                              -- acceleration
 *      
 * methods:
 * --------
 * MolDyn class:
 * 
 *  main() -- just displays a window and starts the init() method
 *  init() -- calls the constructor of the MolDyn class
 *  MolDyn() -- constructor: sets up the layout of the GUI/Screen
 *  periodicboundary() -- checks if position is still in box, if not corrects position
 *  checkmomentum() -- calculates the total momentum and corrects the velocities
 *  acceleration() -- caluclates the force, given a potential and the positions for a time
 *  distance() -- returns the euclidean distance obeying minimum image convention
 *  InitPlots() -- initializes the plots (axes labels, etc.)
 * 
 *  in class DrawingArea (subclass of canvas):
 *          paint() -- calculates everything and does repainting if window gets resized  
 *
 * System of Units:
 * ----------------
 *               length   sigma   =    1
 *               energy   epsilon =    1
 *               mass     m       =    1
 *               time     sigma(m/epsilon)^1/2
 *               velocity (epsilon/m)^1/2
 *               force    epsilon/sigma
 *               pressure epsilon/sigma^2
 *               temperature  epsilon/k      (k=Boltzmann Constant)
 *               specific heat  1/k
 *               diffusion coefficient 1/k
 *
 *
 * Created: Tue Nov 24 12:06:43 1998
 *
 * @author Peter Biechele
 * @version 2.0
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import ptolemy.plot.*;
import VisualNumerics.math.*;

public class MolDyn extends Applet implements Runnable {
    // Boltzmann Constant
    final double kBoltz=1.38e-23;
    // Initial Screen Size parameters
    final static int XSize = 800;
    final static int YSize = 800;
    /* Debugging Parameters::: 
      -1: NO output at all
       0: very few screen output, 
       1: only timesteps output,
       2: full screen output  */
    final int DEBUG = -1;
    // Flag for Drawing
    int FLAG=0;  // variables already instantiated ?? (necessary for paint method) 
    int SUSPEND=0; // is calculation thread suspended ?
    // PARAMETERS TO SET IN PROGRAM
    int momentumcheck = 1000; // After how many steps a momentum ckecked ?
    int replotcount = 10; // after how many steps repaint of plots
    int colorindex=0; // used for different colors of the g(r) plot 

    // non editable parameters of the simulation
    double mass = 1;    // mass of paarticles
    double sigma = 1.0; // Lennard-Jones Pot. 
    double epsilon = 1.0; // Lennard Jones Pot.

    // in the GUI changeable parameters
    int dimensions = 2; // dimensions
    int N = (int)Math.pow(2,dimensions); /* number of particles: 
					    MUST BE a power of the dimension */
    double density =  0.1; // density of fluid/gas
    double deltat = 0.005; // time increment
    double endtime = 1;   // end time
    int timesteps; 
    int terase = 10; // time after which the points are erased again
    double L=Math.pow(N/density,1./(double)dimensions);
    double Volume; // length of periodic box: Volume V=L^3 or Area = L^2
    int SHEAR=0; // flag for the boundary conditions to be used (0=periodic, 1=shear)
    double gamma=0.1;  // shear rate for shear boundary conditions
    int numberofshells=20; // number of shells for g(r) (pair corr. funct.)
    double dr; // thickness of shells
    int vindex, vautocorreltime=50; // measuring interval for veloc. auto correlation funct.
    double SelfDiffusion;

    // Do NOT change anything below HERE:
    // dummy variables
    double deltatsq,deltat2,dummy;
    int dummyi,part,shell;
    Random rand = new Random();
    double Ashell,r;
   
    // the important variables
    double[][][] pos, velocity, accel, velocitysave; 
    double[] potential, kinetic, virial;
    double pressure, temperature;
    double[] pair, pairsum; // pair correl. function
    double sumpotential, sumkinetic, sumkinetic2, sumvirial, sumpressure;
    double Tmean, Pmean, Emean, cv;

    // loop variables
    double t;
    int i,j,k,itotal;
  
    // Thread variables
    Thread current,calcThread;
    static MolDyn prg;

    // AWT Variables
    Graphics g;
    static Frame f;
    DrawArea canv;  // for 2D drawing
    Plot plot1,plot2,plot3; // all the plots
    boolean connect = false;
    Panel resultpanel = new Panel();
    Label label1,label2,label3,label4,label5,label6; // for info displaying
    Panel inputpanel = new Panel();
    TextField diminput,Ninput,deltatinput,densityinput,endtimeinput,
	teraseinput,numberofshellsinput,gammainput,shearinput; // GUI input fields
    // Labels thereof
    Label tlabel1,tlabel2,tlabel3,tlabel4,tlabel5,tlabel6,tlabel7,tlabel8,tlabel9; 
    FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
    // panels needed for the input fields
    Panel tpanel1=new Panel(fl); Panel tpanel2=new Panel(fl);
    Panel tpanel3=new Panel(fl); Panel tpanel4=new Panel(fl);
    Panel tpanel5=new Panel(fl); Panel tpanel6=new Panel(fl);
    Panel tpanel7=new Panel(fl); Panel tpanel8=new Panel(fl);
    Panel tpanel9=new Panel(fl);
    Panel buttonPanel=new Panel(new GridLayout(1,3));
    Panel button2Panel=new Panel(new GridLayout(1,2));
    Button gobutton, stopbutton, continuebutton, clearP1Button; // the Buttons

    /** this is called by the application directly or
	by the browser of the applet */
    public void start() {
	// program=new Thread(prg); // create a separate thread for the calculation
	// program.setPriority(Thread.MIN_PRIORITY);
	calcThread.start();  // calls the run() method !!
    }

    public void stop() {
	calcThread.stop();   // call the stop() method !!
    }

    /** Main wrapper to run as application */
    public static void main(String[] args) {
	f = new Frame("Molecular Dynamics");
	f.setSize(XSize,YSize);
	f.show();

	prg = new MolDyn();
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
	    prg = new MolDyn();
	}

	current = Thread.currentThread();
	calcThread = new Thread(prg);
	calcThread.setPriority(current.getPriority()-1);
    }

    void instantVars() {
	// L has to be calculate here
	L = Math.pow(N/density,1./(double)dimensions); 
	    
	// display a waiting Cursor while calculating
	prg.setCursor(new Cursor(Cursor.WAIT_CURSOR));

	// Calc derived parameters
	timesteps = (int)(endtime/deltat);
	deltatsq=deltat*deltat;
	deltat2 = 2*deltat;
	Volume=Math.pow(L,dimensions);
		
	// instantiation of all the arrays and Objects
	pos = new double[dimensions][N][terase];
	velocity = new double[dimensions][N][terase];
	accel = new double[dimensions][N][terase];
	potential = new double[terase];
	kinetic = new double[terase];
	virial = new double[terase];
	pair = new double[numberofshells]; 
	pairsum = new double[numberofshells]; 
	velocitysave = new double[dimensions][N][timesteps/vautocorreltime+1];
    }

  /** Set Layout of "screen" and 
      initialize the positions and accelerations.
      Is called by the init method !
  */
  public MolDyn() {      
      // Window Layout
      this.setLayout(new GridLayout(3,2));
      canv=new DrawArea();
      this.add(canv);
      // plots
      plot1 = new Plot();
      this.add(plot1);
      plot2 = new Plot();
      this.add(plot2);
      plot3 = new Plot();
      this.add(plot3);
      InitPlots();

      // prepare output of mean values
      this.add(resultpanel);
      resultpanel.setLayout(new GridLayout(8,1));
      label1=new Label("Mean Temperature :",Label.LEFT);
      label2=new Label("Mean Energy      :",Label.LEFT);
      label3=new Label("Mean Pressure    :",Label.LEFT);
      label6=new Label("Mean spec. heat  :",Label.LEFT);
      label4=new Label("Time t = 0",Label.LEFT);     
      label5=new Label("Box Length L =",Label.LEFT);
      tlabel8=new Label("shear factor gamma :",Label.LEFT);
      tlabel9=new Label("boundary cond. (0=periodic, 1=shear) ? :",Label.LEFT);
      gammainput=new TextField(new Double(gamma).toString(),10);
      shearinput=new TextField(new Integer(SHEAR).toString(),3);
      resultpanel.add(label1);
      resultpanel.add(label2);
      resultpanel.add(label3);
      resultpanel.add(label6);
      resultpanel.add(label4);
      tpanel8.add(tlabel8); tpanel8.add(gammainput);
      resultpanel.add(tpanel8);
      tpanel9.add(tlabel9); tpanel9.add(shearinput);
      resultpanel.add(tpanel9);
      clearP1Button = new Button("Clear g(r)");
      button2Panel.add(label5); button2Panel.add(clearP1Button);
      resultpanel.add(button2Panel);

      // input fields
      this.add(inputpanel);
      inputpanel.setLayout(new GridLayout(8,1));
      tlabel1=new Label("Number of particles   :",Label.LEFT);
      tlabel2=new Label("time increment delta t:",Label.LEFT);
      tlabel3=new Label("density of fluid      :",Label.LEFT);
      tlabel4=new Label("end time of simulation:",Label.LEFT);
      tlabel5=new Label("points to be plotted  :",Label.LEFT);
      tlabel6=new Label("dimensions (2 or 3)   :",Label.LEFT);
      tlabel7=new Label("number of shells for g(r) :",Label.LEFT);
      Ninput=new TextField(new Integer(N).toString(),5);
      deltatinput=new TextField(new Double(deltat).toString(),20);
      densityinput=new TextField(new Double(density).toString(),20);
      endtimeinput=new TextField(new Double(endtime).toString(),10);
      teraseinput=new TextField(new Integer(terase).toString(),10);
      diminput=new TextField(new Integer(dimensions).toString(),5);
      numberofshellsinput=new TextField(new Integer(numberofshells).toString(),5);
      inputpanel.add(tpanel1);
      tpanel1.add(tlabel1); tpanel1.add(Ninput);
      inputpanel.add(tpanel2);
      tpanel2.add(tlabel2); tpanel2.add(deltatinput);
      inputpanel.add(tpanel3);
      tpanel3.add(tlabel3); tpanel3.add(densityinput);
      inputpanel.add(tpanel4);
      tpanel4.add(tlabel4); tpanel4.add(endtimeinput);
      inputpanel.add(tpanel5);
      tpanel5.add(tlabel5); tpanel5.add(teraseinput);
      inputpanel.add(tpanel6);
      tpanel6.add(tlabel6); tpanel6.add(diminput);
      inputpanel.add(tpanel7);
      tpanel7.add(tlabel7); tpanel7.add(numberofshellsinput);
      // how to handle inputs = events
      Ninput.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          // here we could react to the return of the input
        }});

      // Buttons
      gobutton = new Button("GO");
      buttonPanel.add(gobutton);
      stopbutton = new Button("Stop");
      buttonPanel.add(stopbutton);
      continuebutton = new Button("Continue");
      buttonPanel.add(continuebutton);
      inputpanel.add(buttonPanel);
      // action to be taken, when buttons are pressed
      clearP1Button.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
	      if (e.getSource() == clearP1Button) {
		  plot1.clear(true); InitPlots(); plot1.repaint();
		  colorindex=0;
	      } } });
      stopbutton.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
	      if (e.getSource() == stopbutton) {
		  if (calcThread.isAlive()==true) {
		      calcThread.suspend();
		      SUSPEND=1;
		  } } } });
      continuebutton.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
	      if (e.getSource() == continuebutton) {
		  if (calcThread.isAlive()==true) {
		      calcThread.resume();
		      SUSPEND=0;
		  } } } });
      gobutton.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
	      if (e.getSource() == gobutton) {		  
		  // if thread suspended, restart a new thread
		  if (calcThread.isAlive()==true && SUSPEND==1) {
		      calcThread.stop();
		  }
		  // start  the new calc only if no other thread is still alive
		  if (calcThread.isAlive()==false) {
		      // derefernece the old thread to stop it completely !
		      calcThread=null; FLAG=0;
		      // get the parameters from the GUI
		      N=Integer.valueOf(Ninput.getText()).intValue();
		      deltat=Double.valueOf(deltatinput.getText()).doubleValue();
		      density=Double.valueOf(densityinput.getText()).doubleValue();
		      endtime=Double.valueOf(endtimeinput.getText()).doubleValue();
		      terase=Integer.valueOf(teraseinput.getText()).intValue();
		      dimensions=Integer.valueOf(diminput.getText()).intValue();		  
		      if (dimensions>3) dimensions=3;
		      if (dimensions<2) dimensions=2;
		      numberofshells=Integer.valueOf(numberofshellsinput.getText()).intValue();
		      gamma=Double.valueOf(gammainput.getText()).doubleValue();
		      SHEAR=Integer.valueOf(shearinput.getText()).intValue();
		      if (SHEAR<0 || SHEAR>1) SHEAR=0;

		      // start the calculation (again)		  
		      SUSPEND=0;
		      calcThread = new Thread(prg);
		      calcThread.setPriority(current.getPriority()-1);		      
		      calcThread.start(); // calls the run() method !!
		  }
	      }
	  }});
  }
  

    /** calculate the total momentum and correct the velocities for zero total momentum */
    void checkMomentum(int time) {
	double[] vsum = new double[dimensions];

	if (time>1 && DEBUG>=0) System.out.println("\n Momentum check:");
	// total momentum
	for (int j=0; j<N; j++) {
            for (k=0; k<dimensions; k++) {        
		vsum[k]+=mass*velocity[k][j][time];
	    }
	}
	// prepare for correction
	for (k=0; k<dimensions; k++) {        	    
	    if (time>1 && DEBUG>=0) System.out.print(vsum[k]+" ");
	    vsum[k]/=N;
	}
	if (time>1 && DEBUG>=0) System.out.println();
	// correct the velocities for vanishing total momentum
	for (int j=0; j<N; j++) {
	    for (k=0; k<dimensions; k++) {        
		velocity[k][j][time]-=vsum[k];
	    }
	}
    }

    /** Check for shear boundary conditions:
	dim 0 (0x) is not changed
	dim 1 and 2 are changed (=shear in x direction) 
	--> actually only for 2D meaningful */
    void shearboundary(int dim, int particle, int time) {
	if (dim>0) {
	    if (pos[dim][particle][time]<0) {
		velocity[0][particle][time]-=(gamma*L); }
	    else if (pos[dim][particle][time]>L) {
		velocity[0][particle][time]+=(gamma*L); }
	}
	periodicboundary(dim,particle,time);
    }

    /** Check for periodic boundary conditions. 
	Because of the use of the modulo function, the TYS JIT 1.0 does not work !! */
    void periodicboundary(int dim, int particle, int time) {	
	double dummy;
	dummy=pos[dim][particle][time];
	dummy%=L;
	if (dummy<0) dummy+=L;
	pos[dim][particle][time]=dummy;
    }

  /** Calculate force = acceleration:  (no Cut-off used)
      Lennard-Jones-Potential: V=4*epsilon*(sigma/r^12-sigma/r^6) 
      => F= 12 epsilon V/r  
      We use the minimum image convention.
  */
  void acceleration(int time) {
    double r,dummy,product,dummyaccel;
    double[] dr = new double[dimensions];

    for (int i=0; i<N; i++) {
      for (int k=0; k<dimensions; k++) {
        accel[k][i][time]=0;
      }
    }
    
    virial[time]=0;
    potential[time]=0;
    for (int i=0; i<N-1; i++) {
      for (int j=i+1; j<N; j++) {
	// minimum image convention
        dr = distance(time,i,j);
        r=0;
        for (int k=0; k<dimensions; k++) {
            r += dr[k]*dr[k];
        }
        // gradient of Lennard-Jones Potential
        product=(sigma*sigma)/(r*r); 
        product=product*product*product;
        dummy=24*epsilon*product*(2*product-1)/r;
        potential[time]+=4*epsilon*(product*product-product);
        // acceleration (force)
        for (int k=0; k<dimensions; k++) {
          dummyaccel=dummy*dr[k];
          accel[k][i][time]+=dummyaccel;
          accel[k][j][time]-=dummyaccel;
        }
	// Calculate the virial (for the pressure)
        for (int k=0; k<dimensions; k++) {
	    virial[time]+=dr[k]*accel[k][i][time];
	} // end virial loop
      } // end j loop
    } // end paritcle loop
  } // end force calculation
      

  /** Calc the euclidean distance vector of two points in 2D or 3D and
      correct the distance obeying minimum image convention. 
      Given are the particle numbers and the time.
      Here we get errors in the results at high densities !!!  */
    double[] distance(int time, int i1, int i2) {      
	double[] sum = new double[dimensions];
	double ds;
	
	for (int k=0; k<dimensions; k++) {	    
	    ds=(pos[k][i1][time]-pos[k][i2][time]);
	    // minimum image convention
	    if (ds > 0.5*L) {
		sum[k]=ds-L;
	    }
	    else if ( ds < -0.5*L ) {
		sum[k]=ds+L;
	    }
	    else {
		sum[k]=ds;
	    }
	}
	return sum;
    }

    /** calc the (norm of the) distance, given the distance vector */
    double normdist(int time, int p1, int p2) {
	double[] dist = new double[dimensions];
	double dummy;
	
	dist=distance(time,p1,p2);
	dummy=0;
	for (int k=0;  k<dimensions; k++) {
	    dummy+=Math.pow(dist[k],2);
	}
	return Math.sqrt(dummy);
    }


    /** This is called when the thread is started */
    public void run() {
	instantVars();
	plot2.clear(true);
	plot3.clear(true);
	connect=false;
	//  initialize plots 
	InitPlots();
	g = canv.getGraphics();
	canv.update(g); 

	/* initial values (t=0) */
	int onedim = (int)Math.pow(N,1/(double)dimensions);
	double setscale = L/onedim;
	int[] setpos = new int[dimensions+1];
	for (int time=0; time<1; time++) {
	    kinetic[time]=0;
	    for (j=0; j<N; j++) {      
		// Distribute the particles on a lattice
		for (int k=0; k<dimensions; k++) {
		    pos[k][j][time]=setpos[k]*setscale+setscale/2;
		}
		setpos[0]++;
		for (int k=0; k<dimensions; k++) {
		    if (setpos[k]>=onedim) {
			setpos[k]=0;
			setpos[k+1]++;
		    }
		}
		// periodic boundary conditions
		if (SHEAR==0) {
		    // velocities are drawn from the Maxwell-Boltzmann distribution
		    // (Components are drawn from normal distrib. 
		    for (int k=0; k<dimensions; k++) {
			velocity[k][j][time]=rand.nextGaussian();
		    }            
		}
		// shear boundary conditions
		else if (SHEAR==1) {
		    // velocity profile is a shear profile v=gamma*(y-L/2)*e_x
		    velocity[0][j][time]=gamma*(pos[1][j][time]-L/2);
		    velocity[1][j][time]=0;
		    if (dimensions==3) velocity[2][j][time]=0;
		}
		// kinetic energy
		for (k=0; k<dimensions; k++) {        
		    kinetic[time]+=Math.pow(velocity[k][j][time],2);
		}
	    }
	    // Momentum check
	    checkMomentum(time);
	    kinetic[time]=0.5*mass*kinetic[time];
	    acceleration(time);
	}
	sumkinetic=0;
	sumkinetic2=0;
	sumpotential=0;
	sumvirial=0;
	sumpressure=0;
	/* END Initital */
	FLAG=1; // now we can use paint() for the canvas
	
	if (DEBUG >= 0) System.out.println("Start Simulation ....");
		
		// calculate and printout initial conditions
		i=0;
		kinetic[0]=0;
		if (DEBUG>=0) System.out.println("\n  ---- Timestep: 0");        
		for (j=0; j<N; j++) {
		    if (DEBUG==2) System.out.println("Particle "+j);        
		    for (k=0; k<dimensions; k++) {        
			// 2nd update of velocities
			velocity[k][j][0]+=0.5*deltat*accel[k][j][0];            
			// save velocities for auto correlation function
			velocitysave[k][j][0]=velocity[k][j][0];
			vindex=1;
			kinetic[0]+=Math.pow(velocity[k][j][0],2);
			if (DEBUG==2) System.out.print(pos[k][j][0]+" ");
		    }
		    if (DEBUG==2) System.out.println();
		}
		kinetic[0]=0.5*mass*kinetic[0];
		if (DEBUG>=1) System.out.println("Total energy: "+
						 (kinetic[0]+potential[0]));		
		/* TIME LOOP **************************** */
		i=0;
		for (itotal=1; itotal<timesteps; itotal++) {
		    // display time elapsed
		    t=itotal*deltat;
		    label4.setText("Time t = "+t);
		    // save only terase steps in an array
		    i++;
		    if (i>=terase) {
			i=1;
			for (k=0; k<dimensions; k++) {        
			    for (j=0; j<N; j++) {
				pos[k][j][0]=pos[k][j][terase-1];
				velocity[k][j][0]=velocity[k][j][terase-1];
				accel[k][j][0]=accel[k][j][terase-1];
				accel[k][j][1]=0;
			    }
			}
		    }
		    
		    /*  ---------------------------------------------------------------------
			Velocity Verlet Algorithm 
			--------------------------------------------------------------------- */
		    for (j=0; j<N; j++) {
			for (k=0; k<dimensions; k++) {        
			    // calculate new positions
			    pos[k][j][i]=pos[k][j][i-1]+velocity[k][j][i-1]*deltat
				+0.5*deltatsq*accel[k][j][i-1];
			    // 1st update of velocities
			    velocity[k][j][i]=velocity[k][j][i-1]+
				0.5*deltat*accel[k][j][i-1];
			    // Boundary Conditions: Periodic
			    /* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			       Here you can put your own boundary conditions for example:
			       write your own method and change the method here. */
			    if (SHEAR==0) { periodicboundary(k,j,i); }
			    else { shearboundary(k,j,i); }
			}
		    }
		    // calculate new accelerations
		    acceleration(i);
		    /* ----------------------------------------------------------------------
		       End of Velocity Verlet
		       ----------------------------------------------------------------------- */
		    // printout on screen for checking the calculations
		    if (DEBUG>0) {
			System.out.println("\n  ---- Timestep: "+itotal+":t="+t);   
		    }
		    else if (DEBUG==0) {
			System.out.print(".");
		    }
		    
		    /* Calculate kinetic energy and 2nd update of velocities 
		       (Velocity Verlet) */
		    kinetic[i]=0;
		    for (j=0; j<N; j++) {
			if (DEBUG==2) System.out.println("Particle "+j);        
			for (k=0; k<dimensions; k++) {        
			    // 2nd update of velocities
			    velocity[k][j][i]+=0.5*deltat*accel[k][j][i];   
			    // save velocities for auto correl. fct.
			    if (itotal%vautocorreltime==0) {
				velocitysave[k][j][vindex]=velocity[k][j][i];
			    }
			    // kinetic energy
			    kinetic[i]+=Math.pow(velocity[k][j][i],2);
			    if (DEBUG==2) System.out.print(pos[k][j][i]+" ");
			}
			if (DEBUG==2) System.out.println();
		    }
		    kinetic[i]=0.5*mass*kinetic[i];
		    if (itotal%vautocorreltime==0) vindex++;
		    
		    // Temperature calc from kinetic energy
		    temperature=2*kinetic[i]/(dimensions*(N-1));

		    // Pressure calc from virial
		    pressure=N/Volume*temperature+virial[i]/(dimensions*Volume);

		    // draw the trajectories of the particles
		    Graphics g=canv.getGraphics();
		    canv.paint(g); 

		    /* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		       here we calculate interesting properties, so put your own ones
		       in here in the loop.
		       !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		    */
		    // calc pair correlation function g[r] at time i
		    // set pair correl. function to zero
		    dr=L/numberofshells;
		    for (shell=0; shell<numberofshells; shell++) {
			pair[shell]=0;
		    }
		    // calc n(r,dr) for each particle
		    for (int part1=0; part1<N; part1++) {
			// look for all other particle positions
			for (part=0; part<N; part++) {
			    if (part!=part1) { 
				r=normdist(i,part1,part);
				shell=0;
				while (r > ((shell+1)*dr)) {
				    shell++; }
				pair[shell]++;
			    }// end if statement
			} // end other particle loop
		    } // end all particle loop
		    // calc g(r) from n(r,dr)
		    for (shell=0; shell<numberofshells; shell++) {
			r=shell*dr;
			if (dimensions == 2) {
			    Ashell=Math.PI*(Math.pow(dr,2)+2*r*dr); }
			else {
			    Ashell=Math.PI*(Math.pow(r+dr,3)-r*r*r); }
			pair[shell]*=2/(density*N*dr*Ashell);
			pairsum[shell]+=pair[shell];
		    }

		    /* sum important quantities for mean values at the end */
		    /* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		       if you need sums (=time averages) put the sums here */
		    sumpotential+=potential[i];
		    sumkinetic+=kinetic[i];
		    sumkinetic2+=kinetic[i]*kinetic[i];
		    sumvirial+=virial[i];
		    sumpressure+=pressure;

		    /* Plot the interesting quantities in separate plots using Ptolemy. */
		    /* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
		       and here you can plot the results in plots or see later */
		    boolean connect2=false;
		    /* 
		       for (int shell=0; shell<numberofshells; shell++) {
		       plot1.addPoint(0,shell*dr,pair[shell],connect2);
		       if (connect2==false) connect2=true;
		       }
		    */
		    plot2.addPoint(0,t,kinetic[i]+potential[i],connect);
		    plot3.addPoint(3,t,temperature,connect);
		    plot3.addPoint(4,t,pressure,connect);
		    if (itotal>1) connect=true;

		    // redraw plots every now and then
		    if ((itotal%replotcount) == 0 ) {
			plot2.repaint(); plot3.repaint();
		    }

		    // check for total momentum conservation and correct if necessary
		    if (itotal%momentumcheck == 0) checkMomentum(i);

		    if (DEBUG>=1) System.out.println("Total energy: "+
						     (kinetic[i]+potential[i]));
		} // End of time loop

		// mean values of Pair Correlation function
		for (int shell=0; shell<numberofshells; shell++) {
		    pairsum[shell]/=(double)timesteps;
		}
		boolean connect2=false;
		for (int shell=0; shell<numberofshells; shell++) {
		    plot1.addPoint(colorindex,shell*dr,pairsum[shell],connect2);
		    if (connect2==false) connect2=true;
		}
		// plot a vertical line to show the valid range of the plot
		plot1.addPoint(colorindex,L/2,0,false);
		plot1.addPoint(colorindex,L/2,Statistics.maximum(pairsum),true);
		colorindex++; 
		if (colorindex>9) colorindex=0;
		plot1.repaint();

		/* Calculate the important mean values in the desried units */
		Tmean=(2*sumkinetic)/(dimensions*timesteps);
		cv=2.0/(dimensions*N)*( 1-2*(sumkinetic2/timesteps-Tmean*Tmean)
		    / (dimensions*N*kBoltz*kBoltz*Tmean*Tmean));
		cv=1/cv;
		Tmean/=(N-1);
		Emean=(sumkinetic+sumpotential)/(timesteps);
		// Pmean=(N*kBoltz*Tmean+(sumvirial/timesteps)/dimensions)/Volume;
		Pmean=sumpressure/timesteps;

		// velocity autocorrelation function
		double[][] vcorrelation = new double[timesteps/vautocorreltime+1][N]; 
		double[] vcorrelsum = new double[timesteps/vautocorreltime+1];
		double[] vdummy = new double[N];
		for (int tau=0; tau<vindex; tau++) {
		    for (int part=0; part<N; part++) {
			dummy=0;
			for (int k=0; k<dimensions; k++) {
			    dummy+=(velocitysave[k][part][tau]*velocitysave[k][part][0]);
			}	
			vcorrelation[tau][part]=dummy;					    
			if (tau==0) vdummy[part]=vcorrelation[0][part];
			vcorrelation[tau][part]/=vdummy[part];
			vcorrelsum[tau]+=vcorrelation[tau][part];
		    } // end particle loop
		    vcorrelsum[tau]/=N;
		} // end time loop
		FLAG=2;

		System.out.println("\n\n Velocity Auto Correlation Function:");
		dummy=0;
		for (int tau=0; tau<vindex; tau++) {
		    System.out.println(" at time t = "+(tau*deltat)+"  "+vcorrelsum[tau]);
		    dummy+=vcorrelsum[tau];
		}		
		// Self Diffusion Coefficient
		SelfDiffusion=dimensions*Tmean/mass*dummy;
		System.out.println(" Self Diffusion Coefficient = "+SelfDiffusion);
    
		// set the default cursor again, because we are done calculating
	    prg.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    // set flag, so we do not calc again, if just repainting screen
	    FLAG=1;
	    // display the mean values 
	    /* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	       finally, here you can output the results on the screen */ 
	    label1.setText("Mean Temperature :  "+Tmean);
	    label2.setText("Mean Energy      :  "+Emean);
	    label3.setText("Mean Pressure    :  "+Pmean);
	    label6.setText("Mean spec. heat  :  "+cv);
	    label5.setText("Box Length L = "+L);
    }

    /** Create our own Canvas (to use only a part of the window for painting) 
	for plotting the trajectories (implemented only for 2 dimensions) */
    class DrawArea extends Canvas {
	public void paint(Graphics g) {
	    int xsize=(this.getSize()).width;
	    int ysize=(this.getSize()).height;
	    int resolx = (int)(xsize/L);
	    int resoly = (int)(ysize/L);
	    int xoffset=0,yoffset=0;
	    int x1,y1;

	    /* Plot data, if we are in 2 dimensions */		
	    if (dimensions==2) {
		for (int p=0; p<N; p++) {
		    // use 2 different colors for the particles
		    if (p%2 == 0) { g.setColor(Color.blue); }
		    else          { g.setColor(Color.red); }
		    // transform positions to pixels on screen
		    x1=(int)(pos[0][p][i]*resolx)+xoffset;
		    y1=(int)(pos[1][p][i]*resoly)+yoffset;
		    g.drawLine(x1,y1,x1,y1);

		    // erase "old" points
		    if (itotal>=terase-1) {    
			g.setColor(getBackground());
			dummyi=i+1;
			if (dummyi>=terase) {
			    dummyi=0;
			    x1=(int)(pos[0][p][1]*resolx)+xoffset;
			    y1=(int)(pos[1][p][1]*resoly)+yoffset;
			    g.drawLine(x1,y1,x1,y1);
			}
			x1=(int)(pos[0][p][dummyi]*resolx)+xoffset;
			y1=(int)(pos[1][p][dummyi]*resoly)+yoffset;
			g.drawLine(x1,y1,x1,y1);
		    } // end erasing
		} // end particle loop
	    } // end dimensions if
	} // end of paint method	

	/** We have to override update(), otherwise the canvas is erased
	    every time repaint() is called */
	public void update(Graphics g) {
	    int xsize=(this.getSize()).width;
	    int ysize=(this.getSize()).height;

	    if (FLAG==0) {
		g.setColor(getBackground());
		g.fillRect(0,0,xsize-1,ysize-1); 
		g.setColor(Color.black);
		g.drawRect(0,0,xsize-1,ysize-1); }
	    else {
		paint(g);
		}
	}

    } // end of Canvas Subclass
    
    /** Initialize the plots */
    void InitPlots() {
	// plot 1
	plot1.setButtons(true);
	plot1.setMarksStyle("points");
	plot1.setXLabel("distance r");
	plot1.setYLabel("g(r)");
	plot1.setTitle("Pair Correlation Function");
	// plot 2
	plot2.setButtons(true);
	plot2.setMarksStyle("points");
	plot2.setXLabel("Time t");
	plot2.setYLabel("energy");
	plot2.setTitle("Total Energy");
	// plot 3
	plot3.setButtons(true);
	plot3.setMarksStyle("points");
	plot3.setXLabel("Time t");
	plot3.setYLabel("T / P");
	plot3.setTitle("Temperature / Pressure");
	plot3.addLegend(3,"Temp.");
	plot3.addLegend(4,"Pressure");
    }    

} // MolDyn
