
/**
 * Ising2D.java
 *
 * Monte Carlo Simulation of the
 * Ising Model in 2 Dimensions with external field B
 * using periodic boundary conditions on a square lattice
 * Algorithm: Metropolis M(RT)2  (1953)
 * OR         Swendson-Wang multiple Cluster (1987)
 *
 * Units used:
 * 
 * J and B are in units of temperature ([J] = [kT])
 *
 * additional Java libraries used:
 *         -  Ptolemy (Ptplot V2.0)
 *         -  VNL (Visual Numerics Library)
 *
 * Created: Fri Dec 11 14:35:35 1998
 *
 * @author Peter Biechele
 * @version 2.2
 *
 * STILL problems with T < Tc  (J>0.5) with both algorithms
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import ptolemy.plot.*;
import VisualNumerics.math.*; 

public class Ising2D extends Applet implements Runnable {
    // Boltzmann Constant
    final double kBoltz=1.38e-23;
    // Debugging Parameters (DEBUG= -1 / 0 / 1 or 2)
    final int DEBUG = -1;
    // Blocking test ??
    final int BLOCKING = 1;

    // Initial Screen Size parameters
    final static int XSize = 800;
    final static int YSize = 800;
    // are we an aplication or an applet ? (is set during execution)
    static int application=0;
 
    // Variables describing the system
    int FLAG=0;  // already did the calculation ?? (necessary for paint method)
    int algorithm = 1; // choose algorithm (0 -> MR2, 1 -> Cluster)
    int Nx=10, Ny=10; // dimensions of lattice
    int size = Nx*Ny; // size of lattice 
    int Nspins = Nx*Ny; // number of spins
    double J=0.3, B=0;  // Coupling and external magnetic field
    double Jc=0.5*Math.log(1+Math.sqrt(2)); //  = 0.4406868 : critical Temperature
    double beta=1; // inverse temperature !!! ???
    int Ntherm = 20; // thermalization sweeps
    int Ngroups = 3; // number of groups to run
    int Nsamples = 3; // number of samples per group
    int SampleFreq = 5; // sampling frequency 
    int tsteps; // number of timesteps (computed from Ngroups, size and SampleFreq)
    int[][] Spin;    // Spin lattice 
    double[][] Prob = new double[5][2]; // precaluclated probabilities
    // important variables
    double energy=0, magnetization=0;

    // for the blocking test !
    double[] Eblock, Mblock;
    int tn;

    int accept,more;
    int igroup,sweep;
    double E,M,Chi,Cb;
    double[] Cbexact, Jexact;
    double Msum,Msum2,Msumsigma,Esum,Esum2,Esumsigma;
    double Chisum,Chisum2,Cbsum,Cbsum2;
    double Mgroup,Mgroup2,Egroup,Egroup2;
    double Msigma,Esigma,Chisigma,Cbsigma;

    // Loop/Dummy Variables
    int i,j,k,x,y;
    double dummy;
    int dummyint;
    // for cluster algorithm
    int[][] cnum;
    int[] csize, cnumdummy; // for cluster renaming used 

    // Random Numbers
    Random rand=new Random();

    // AWT Variables
    static Frame f;
    static Applet a;
    DrawArea canv; // for the spin matrix plotting
    Graphics g;
    Color backg; 
    static Panel display;
    FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
    Panel panA=new Panel(fl); Panel panB=new Panel(fl);
    Panel panC=new Panel(fl); Panel panD=new Panel(fl);
    // panels needed for the input fields
    Panel pan1=new Panel(fl); Panel pan2=new Panel(fl);
    Panel pan3=new Panel(fl); Panel pan4=new Panel(fl);
    Panel pan5=new Panel(fl); Panel pan6=new Panel(fl);
    Panel pan7=new Panel(fl); Panel pan8=new Panel(fl);
    Panel pan9=new Panel(fl); Panel pan10=new Panel(fl);
    Panel pan11=new Panel(fl); Panel pan12=new Panel(fl);
    Panel pan13=new Panel(fl); Panel pan14=new Panel(fl);
    TextField Nxinput,Nyinput,Binput,Jinput,Ntherminput,
        Ngroupsinput,SampleFreqinput; // GUI input fields
    // Labels thereof
    Label Nxlabel,Nylabel,Blabel,Jlabel,Nthermlabel,Ngroupslabel,SampleFreqlabel;
    Label l0,l1,l2,l3,l4,l5,l6,l7,l8,l9;
    // Plots and Buttons
    Plot plot1,plot2,plot3; 
    Button p1clearbutton=new Button("Clear Plot 1");
    Button p2clearbutton=new Button("Clear Plot 2");
    Button p3clearbutton=new Button("Clear Plot 3");
    Button gobutton = new Button("GO"); // the GO Button
    // Checkboxes
    CheckboxGroup cbg;
    Checkbox MetropolisCB, ClusterCB;

    void initialSetup() {
	// Screen Layout
	GridLayout mylayout = new GridLayout(4,2);
	display.setLayout(mylayout);
	// add a drawing Canvas for the Spins
	canv = new DrawArea();
	display.add(canv);
	g=canv.getGraphics();
	backg=canv.getBackground();
	// the plots
	plot1 = new Plot(); display.add(plot1);
	plot2 = new Plot(); display.add(plot2);
	plot3 = new Plot(); display.add(plot3);
	InitPlots();
	/* The Information Panels :::  */
	panA.setLayout(new GridLayout(4,1)); panB.setLayout(new GridLayout(4,1));
	// Panel A and B:
	// add the buttons 
	pan1.add(p1clearbutton); pan1.add(p2clearbutton); pan1.add(p3clearbutton); 
	// pan2.add(gobutton);
	panA.add(pan1); panB.add(gobutton);
	// add the input fields
	Nxlabel=new Label("Number of cells in x: ",Label.LEFT);
	Nxinput=new TextField(new Integer(Nx).toString(),5); 
	pan3.add(Nxlabel); pan3.add(Nxinput); panA.add(pan3);
	Nylabel=new Label("Number of cells in y: ",Label.LEFT);
	Nyinput=new TextField(new Integer(Ny).toString(),5); 
	pan4.add(Nylabel); pan4.add(Nyinput); panB.add(pan4);
	Jlabel=new Label("Interaction J : ",Label.LEFT);
	Jinput=new TextField(new Double(J).toString(),15); 
	pan5.add(Jlabel); pan5.add(Jinput); panA.add(pan5);
	Blabel=new Label("External magnetic field B : ",Label.LEFT);
	Binput=new TextField(new Double(B).toString(),15); 
	pan6.add(Blabel); pan6.add(Binput); panB.add(pan6);
	Nthermlabel=new Label("Number of thermalization sweeps : ",Label.LEFT);
	Ntherminput=new TextField(new Integer(Ntherm).toString(),8); 
	pan7.add(Nthermlabel); pan7.add(Ntherminput); panA.add(pan7);
	SampleFreqlabel=new Label("Frequency of samples: ",Label.LEFT);
	SampleFreqinput=new TextField(new Integer(SampleFreq).toString(),8); 
	pan8.add(SampleFreqlabel); pan8.add(SampleFreqinput); panB.add(pan8);
	// Panel C and D:
	panC.setLayout(new GridLayout(4,1)); panD.setLayout(new GridLayout(4,1));
	Ngroupslabel=new Label("Number of groups : ",Label.LEFT);
	Ngroupsinput=new TextField(new Integer(Ngroups).toString(),8); 
	pan9.add(Ngroupslabel); pan9.add(Ngroupsinput); panC.add(pan9);
	// Checkbox for algorithm
	cbg = new CheckboxGroup();
	MetropolisCB=new Checkbox("Metropolis", cbg, (algorithm==0) );
        panC.add(MetropolisCB);
	ClusterCB=new Checkbox("Multiple Cluster", cbg, (algorithm==1) );
        panC.add(ClusterCB);
	// Info Labels
	l0=new Label("Sweeps = ",Label.LEFT);
	l1=new Label(sweep+" of "+SampleFreq*size,Label.LEFT);
	l2=new Label("   Groups = "+igroup+" of "+Ngroups+" ",Label.LEFT);
	l4=new Label("Energy = "+energy,Label.LEFT);
	l6=new Label("Magnetization = "+magnetization,Label.LEFT);
	// l7=new Label(" ");
	l8=new Label("Chi = ",Label.LEFT);
	l9=new Label("Cb =  ",Label.LEFT);
	pan10.add(l0); pan10.add(l1); pan10.add(l2); panD.add(pan10);
	panD.add(l4); panD.add(l6); panD.add(l8);
	panC.add(l9);
	// add the panels to the display finally
	display.add(panA); display.add(panB);
	display.add(panC); display.add(panD);
 
	// how to handle inputs = events
	Nxinput.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		// here we could react to the return of the input
	    }});
	
	// Buttons
	// action to be taken, when go button is pressed
	gobutton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gobutton) {
		    // get the parameters from the GUI
		    Nx=Integer.valueOf(Nxinput.getText()).intValue(); 
		    Ny=Integer.valueOf(Nyinput.getText()).intValue(); 
		    size = Nx*Ny; 
		    Nspins = Nx*Ny; 
		    J=Double.valueOf(Jinput.getText()).doubleValue();
		    B=Double.valueOf(Binput.getText()).doubleValue();
		    Ntherm=Integer.valueOf(Ntherminput.getText()).intValue(); 
		    Ngroups=Integer.valueOf(Ngroupsinput.getText()).intValue(); 
		    SampleFreq=Integer.valueOf(SampleFreqinput.getText()).intValue(); 
		    if (cbg.getSelectedCheckbox().equals(MetropolisCB)) { algorithm=0; }
		    else { algorithm=1; }
		    FLAG=0; // we have to calc everything again
		    run();
		}
	    } });	    
	p1clearbutton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == p1clearbutton) {
		    plot1.clear(true); InitPlots(); plot1.repaint(0); }
	    } });	    
	p2clearbutton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == p2clearbutton) {
		    plot2.clear(true); InitPlots(); plot2.repaint(0); }
	    } });	    
	p3clearbutton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == p3clearbutton) {
		    plot3.clear(true); InitPlots(); plot3.repaint(0); }
	    } });	    
    }
    
    /** Initial configuration : random */
    void initial() {
	for (i=0; i<Nx; i++) {
	    for (j=0; j<Ny; j++) {
		dummy=rand.nextDouble();
		if (dummy<0.5) Spin[i][j]=1;
		else Spin[i][j]=-1;
	    }	    
	}
    }

    /** Pre Calculation of Flip Probabilities */
    void calcFlipProbs() {
	for (i=0; i<5; i++) {
	    Prob[i][1] = Math.exp(-2*(J*(2*i-4)+B));
	    Prob[i][0] = 1/Prob[i][1];
	}
    }

    /** The actual calculation takes place here */
    public void run() {
     if (FLAG==0) {
	// clear graphics Canvas
	clearcanv();
	// for blocking test
	if (BLOCKING==1) {
	    tsteps=Ngroups*(SampleFreq*size);
	    Eblock = new double[tsteps+1];
	    Mblock = new double[tsteps+1]; }
	// instantiate arrays
	Spin = new int[Nx][Ny];
	cnum = new int[Nx][Ny];
	csize = new int[Nx*Ny+2]; 
	cnumdummy = new int[Ny*Nx/10+50]; // (10 is an arbitrary estimation)
	initial();
	calcFlipProbs();
	// Thermalize
	if (Ntherm>0) {
	    for (sweep=0; sweep<Ntherm; sweep++) {
		if (algorithm==0) {
		    accept=MR2sweep();  } // METROPOLIS
		else if (algorithm==1) {
		    accept=cluster(); } // CLUSTER
		plotLattice();
	    }
	}

	// The actual calculation, collecting the data */
	if (DEBUG>=0) System.out.println("Nx="+Nx+" Ny="+Ny+" J="+J+" B="+B);
	
	// for blocking test
	if (BLOCKING==1) {
	    computeEM();
	    Eblock[0]=energy;
	    Mblock[0]=magnetization;
	    tn=1; }

	// initialize total sums
	Msum=0; Msum2=0; Msumsigma=0;
	Esum=0; Esum2=0; Esumsigma=0;
	Chisum=0; Chisum2=0;
	Cbsum=0; Cbsum2=0;
	// just used, if after the calc, you want to have more sweeps
	more = Ngroups;

	// group loop
	for (igroup=Ngroups-more+1; igroup<Ngroups+1; igroup++) {
	    // initialize group sums
	    Mgroup=0; Mgroup2=0;
	    Egroup=0; Egroup2=0;
	    // loop over sweeps in a group: 
	    for (sweep=1; sweep< SampleFreq*size; sweep++) {
		if (algorithm==0) {
		    accept=MR2sweep();  } // METROPOLIS
		else if (algorithm==1) {
		    accept=cluster(); } // CLUSTER
		if (DEBUG>=2) {
		    System.out.println("Sample "+(double)sweep/SampleFreq+" of "+size);
		    System.out.println("in group "+igroup+" of "+Ngroups);
		    System.out.println(" Accepted: "+(double)accept/Nspins);
		}
		// plot every 10 steps
		if (sweep%(SampleFreq*size/10)==0) plotLattice();
		// sometimes compute observables
		if (sweep%SampleFreq==0) {
		    computeEM();
		    // calc group updates
		    Mgroup+=magnetization;
		    Mgroup2+=Math.pow(magnetization,2);
		    Egroup+=energy;
		    Egroup2+=Math.pow(energy,2);
		    // save for blocking test
		    if (BLOCKING==1) {
			Eblock[tn]=energy;
			Mblock[tn]=magnetization; 
			tn++; }
		}
		// display information
		l1.setText(sweep+" of "+SampleFreq*size); 
		l2.setText("   Groups = "+igroup+" of "+Ngroups+" ");
	    } // END OF SWEEP LOOP

	    /* calculate and display total observables */
	    totalObservables();
	    plotLattice();

	} // END OF GROUPS LOOP
	// add the results to the plots
	boolean connect=false;
	dummy=1/(2*beta*J);
	Esigma/=Nspins; Msigma/=Nspins; 
	Chisigma/=Nspins; Cbsigma/=Nspins;
	M=Math.abs(M);
	plot1.addPointWithErrorBars(0,dummy,M,M-Msigma,M+Msigma,connect);
	plot2.addPointWithErrorBars(1,dummy,Chi,Chi-Chisigma,Chi+Chisigma,connect);
	plot3.addPointWithErrorBars(2,dummy,Cb,Cb-Cbsigma,Cb+Cbsigma,connect);
	plot1.repaint(0); plot2.repaint(0); plot3.repaint(0);
	// DONE !
	// here we could set the var "more" and go on calculating 


	// Exact Finite size scaling
	finiteSizeScaling();
	connect=false;
	for (int i=0; i<Cbexact.length; i++) {
	    plot3.addPoint(5,1/(2*beta*Jexact[i]),Cbexact[i],connect);
	    if (i==1) connect=true;
	}

	// EXACT Results for B=0 and Nx,Ny to infinity
	printExactResults();
	FLAG=1;

	// The Blocking Test 
	if (BLOCKING==1) {
	    double[] Evalues = new double[2];
	    double[] Mvalues = new double[2];
	    int nn=0;
	    for (int n=1; n<51; n++ ) { // the number of groups
		nn=tn/n; // integer division
		Mvalues = blocking(Mblock,nn);
		Evalues = blocking(Eblock,nn); 
		System.out.println(nn+" "+Evalues[0]+" "+Evalues[1]+"  "+
				   Mvalues[0]+" "+Mvalues[1]);
	    }
	}
     }
    }

    /** compute the mean and variance for the blocking test */
    double[] blocking(double[] array, int groupsize) {
	double[] returnval = new double[2];
	int alen=array.length;
	int numgroups=alen/groupsize;
	double[] gmean=new double[numgroups];
	int count=0;
	double gsum, totsum;

	// mean
	totsum=0;
	for (int g=0; g<numgroups; g++) {
	    gsum=0;
	    for (int i=0; i<groupsize; i++) {
		gsum+=array[count]; 
		count++; }
	    gmean[g]=gsum/groupsize;
	    totsum+=gmean[g];
	}
	returnval[0]=totsum/numgroups;
	// variance
	gsum=0;
	for (int g=0; g<numgroups; g++) {
	    gsum+=Math.pow(gmean[g]-returnval[0],2); }
	if (numgroups>1) {
	    returnval[1]=gsum/(numgroups-1); }
	else {
	    returnval[1]=0; }  // correct here, if necessary
	
	return returnval;
    }


    /** computation of energy and magentization */
    void computeEM() {
	int upper,left;
	double suminteraction=0;

	magnetization=0; 
	for (y=0; y<Ny; y++) {
	    if (y>0) {
		upper=y-1; }
	    else {
		upper=Ny-1; }
	    for (x=0; x<Nx; x++) {
		if (x>0) {
		    left=x-1; }
		else {
		    left=Nx-1; }
		suminteraction+=Spin[x][y]*(Spin[x][upper]+Spin[left][y]);
		magnetization+=Spin[x][y];
	    }
	}
	energy=-J*suminteraction-B*magnetization;
    }

    /** The Cluster Algorithm 
	(Hoshen-Kopelmann-Algorithm from percolation theoryx) */
    int cluster() {
	int upper, left, bond, numsave;
	int c, dummyx, dummyy, cset, back; 
	double pbond = 1-Math.exp(-2*beta*(J+B)); // Is this correct for B not 0 ????

	for (int y=0; y<Ny; y++) {
	    for (int x=0; x<Nx; x++) {
		cnum[x][y]=0; 
		csize[x+y*Nx]=0; }}
	csize[Nx*Ny]=0; csize[Nx*Ny+1]=0;

	c=1;
	for (int y=0; y<Ny; y++) {
	    if (y>0) {
		upper=y-1; }
	    else {
		upper=Ny-1; }
	    for (int x=0; x<Nx; x++) {
		if (x>0) {
		    left=x-1; }
		else {
		    left=Nx-1; }
		// how many bonds from Spin(x,y) ?
		bond=0; dummyx=x; dummyy=y;
		if (Spin[x][y]==Spin[left][y]) {
		    if (rand.nextDouble() < pbond) {
			bond++; 
			dummyx=left; }
		}
		if (Spin[x][y]==Spin[x][upper]) {
		    if (rand.nextDouble() < pbond) {
			bond++;
			dummyy=upper; }
		}
		// cluster building, depending on number of bonds to left/upper neighbour
		numsave=cnum[x][y];
		if (bond==0) {
		    cnum[x][y]=c;
		    csize[c]=0;
		    c++; } 
		else if (bond==1) {
		    // special cases in the first row
		    if (y==0) {
			// create new cluster in the last row
			if (dummyy==upper) {
			    cnum[x][upper]=c; 
			    csize[c]=0; c++; }     }
		    // special cases in the first column
		    if (x==0) {
			// create new cluster in the last column
			if (dummyx==left) {
			    cnum[left][y]=c; 
			    csize[c]=0; c++; }     }	
		    cnum[x][y]=cnum[dummyx][dummyy]; }
		else if (bond==2) {
		    // special case in the upper left corner
		    if (x==0 && y==0) {
			// create new cluster in the corner
			cnum[left][y]=c; cnum[x][upper]=c;
			csize[c]=0; c++; }
		    // same cluster
		    if (cnum[left][y]==cnum[x][upper]) {
			cnum[x][y]=cnum[left][y]; }
		    // different cluster
		    else {
			// which cluster-number is smaller ?
			if (cnum[left][y] < cnum[x][upper]) {
			    cset=cnum[left][y]; cnum[x][y]=cset;
			    cnumdummy[0]=cnum[x][upper]; }
			else {
			    cset=cnum[x][upper]; cnum[x][y]=cset;
			    cnumdummy[0]=cnum[left][y];	}
			back=0;
			while (cnumdummy[back] < 0) {
			    cnumdummy[back+1]=csize[ Math.abs(cnumdummy[back]) ];
			    back++; }
			for (int i=0; i<back+1; i++) csize[ cnumdummy[i] ] = - cset; } 
		}  // end cluster building
		// special case again: last row or column
		if (numsave>0) {
		    if (numsave<cnum[x][y]) {cset=numsave; cnumdummy[0]=cnum[x][y]; }
		    else {cset=cnum[x][y]; cnumdummy[0]=numsave; } 
		    back=0;
		    while (cnumdummy[back] < 0) {
			cnumdummy[back+1]=csize[ Math.abs(cnumdummy[back]) ];
			back++; }
		    for (int i=0; i<back+1; i++) csize[ cnumdummy[i] ] = - cset; }
	    } // x loop
	} // y loop
	c--;

	if (DEBUG==2) {
	    System.out.println(); 
	    for (y=0; y<Ny; y++) {
		for (x=0; x<Nx; x++) {
		    if (Spin[x][y]==1) System.out.print("*"); 
		    else System.out.print(" ");
		}
		System.out.println(); }
	    for (int i=1; i<c+1; i++) System.out.print(csize[i]+" "); 
	}

	csize[0]=1; // if something went wrong !
	// unbounded clusters
	for (int i=1; i<c+1; i++) {
	    if (csize[i]==0) {
		if (rand.nextDouble()<0.5) csize[i]=1;
		else csize[i]=2; } }
	// bounded clusters
	for (int i=1; i<c+1; i++) {
	    if (csize[i]<0) {
		csize[i]=csize[ Math.abs(csize[i]) ]; } }
	
	// change the Spins of the clusters
	for (y=0; y<Ny; y++) {
	    for (x=0; x<Nx; x++) { 
		Spin[x][y]=2*csize[ cnum[x][y] ]-3; } }

	if (DEBUG==2) {
	    System.out.println(); 
	    for (y=0; y<Ny; y++) {
		for (x=0; x<Nx; x++) {
		    System.out.print(cnum[x][y]+" "); }
		System.out.println(); }
	    for (int i=1; i<c+1; i++) System.out.print(csize[i]+" "); 
	    System.out.println(); 
	}
	
	return 0; // return value unimportant
    }

    /** The sweep over the whole lattice: Metropolis Algorithm 
	using a ordered sweep over the whole lattice */
    int MR2sweep() {
	int accept,f;
	int upper,lower,left,right;

	accept=0;
	for (x=0; x<Nx; x++) {
	    if (x<Nx-1) {
		right=x+1; }
	    else {
		right=0; }
	    if (x>0) {
		left=x-1; }
	    else {
		left=Nx-1; }
	    for (y=0; y<Ny; y++) {
		if (y<Ny-1) {
		    lower=y+1; }
		else {
		    lower=0; }
		if (y>0) {
		    upper=y-1; }
		else {
		    upper=Ny-1; }
		dummyint=(Spin[x][y]+2)/2;
		f=2+(Spin[x][upper]+Spin[x][lower]+Spin[left][y]+Spin[right][y])/2;
		// Flip the Spin ??
		if (rand.nextDouble() < Prob[f][dummyint]) {
		    Spin[x][y]=-Spin[x][y];
		    accept++;
		}
	    }
	}
	return accept;
    }

    /** Calculate and output the exact results for infinite lattice 
	(Onsager Solutions) */
    void printExactResults() {
	double kappaprime,kappa,M,z;

	z=Math.exp(-2*beta*J);
	kappa=2*Sfun.sinh(2*J)/Math.pow(Sfun.cosh(2*J),2);
	kappaprime=2*Math.pow(Sfun.tanh(2*J),2)-1;

	energy=-Nspins*J*coth(2*J)*(1+2/Math.PI*kappaprime*K1(kappa));
	Cb=Nspins*2.0/Math.PI*Math.pow(J*coth(2*J),2)*(2*K1(kappa)-
	   2*E1(kappa)-(1-kappaprime)*(Math.PI/2+kappaprime*K1(kappa)));
	if (J>Jc) {
	    M=-Nspins * Math.pow(1+z*z,0.25)
		* Math.pow(1-6*z*z+Math.pow(z,4),1.0/8.0) 
		/ Math.sqrt(1-z*z);
	}
	else {
	    M=0;
	}

	// display exact results
	if (DEBUG>=0) {
	    System.out.println(" EXACT (only for B=0 valid):");
	    System.out.println(" Energy: "+energy/Nspins);
	    System.out.println(" Magnetization: "+M/Nspins);
	    System.out.println(" specific heat (constant B): "+Cb/Nspins);
	}
    }

    /** sum and calculate the total observables from the group observables */
    void totalObservables() {
	double Edummy,Mdummy,Esigmadummy,Msigmadummy;
	
	// first: normalize
	Mgroup/=size; Mgroup2/=size;
	Egroup/=size; Egroup2/=size;
	Chi=Mgroup2-Math.pow(Mgroup,2);
	Cb=Egroup2-Math.pow(Egroup,2);
	Msigma=Math.sqrt(Chi/size);
	Esigma=Math.sqrt(Cb/size);
	// second: display group values
	if (DEBUG>=0) {
	    System.out.println("Group "+igroup+":");
	}
	if (DEBUG>=1) {
	    System.out.println("E= "+Egroup/Nspins+"+-"+Esigma/Nspins);
	    System.out.println("M= "+Mgroup/Nspins+"+-"+Msigma/Nspins);
	    System.out.println("Chi= "+Chi/Nspins+"  Cb="+Cb/Nspins);
	    }
	// third: calc total sums
	Msum+=Mgroup; Msum2+=Mgroup2; Msumsigma+=Msigma;
	Esum+=Egroup; Esum2+=Egroup2; Esumsigma+=Esigma;
	Chisum+=Chi; Chisum2+=Math.pow(Chi,2);
	Cbsum+=Cb; Cbsum2+=Math.pow(Cb,2);
	// fourth: total quantities
	Edummy=Esum/igroup; 
	Esigma=Math.sqrt((Esum2/igroup-Math.pow(Edummy,2))/igroup);
	Esigmadummy=Math.sqrt(Esumsigma)/igroup;
	Mdummy=Msum/igroup; 
	Msigma=Math.sqrt((Msum2/igroup-Math.pow(Mdummy,2))/igroup);
	Msigmadummy=Math.sqrt(Msumsigma)/igroup;
	Chi=Chisum/igroup;
	Chisigma=Math.sqrt(Chisum2/igroup-Math.pow(Chi,2))/igroup;
	Cb=Cbsum/igroup;
	Cbsigma=Math.sqrt(Cbsum2/igroup-Math.pow(Cb,2))/igroup;
	if (DEBUG>=0) {
	    System.out.println("Total :");
	    System.out.println("E= "+Edummy/Nspins+"+-"+Esigma/Nspins+" ? "
			       +Esigmadummy/Nspins);
	    System.out.println("M= "+Mdummy/Nspins+"+-"+Msigma/Nspins+" ? "
			       +Msigmadummy/Nspins);
	    System.out.println("Chi= "+Chi/Nspins+"+-"+Chisigma/Nspins);
	    System.out.println("Cb= "+Cb/Nspins+"+-"+Cbsigma/Nspins);
	}		
	// display total observables
	l4.setText("Energy = "+Edummy/Nspins+"+-"+Esigma/Nspins);
	l6.setText("Magnetization = "+Mdummy/Nspins+"+-"+Msigma/Nspins);
	l8.setText("Chi = "+Chi/Nspins+"+-"+Chisigma/Nspins);
	l9.setText("Cb = "+Cb/Nspins+"+-"+Cbsigma/Nspins);

	E=Edummy/Nspins; M=Mdummy/Nspins; 
	Chi=Chi/Nspins; Cb=Cb/Nspins;
    }

    /** Cotangent Function */
    double coth(double x) {
	return 1.0/VisualNumerics.math.Sfun.tanh(x); 
    }
    /** Complete elliptic integral of first kind */
    double K1(double kappa) {
	double[] tab = {1.5708, 1.5738, 1.5828, 1.5981, 1.6200, 1.6490, 
			1.6858, 1.7312, 1.7868, 1.8541, 1.9356, 2.0347,
			2.1565, 2.3088, 2.5046, 2.7681, 3.1534, 3.8317, 1e99};
	double[] angle = {0,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90};
	double alpha;
	alpha=Math.asin(kappa)/Math.PI*180;

	int i=0;
	for (;;) {
	    if (Math.abs(angle[i]-alpha)<=2.5) break;
	    i++;
	}
	return tab[i];
    }
    /** Complete elliptic integral of second kind */
    double E1(double kappa) {
	double[] tab = {1.5708, 1.5678, 1.5589, 1.5442, 1.5238, 1.4981,   
			1.4675, 1.4223, 1.3931, 1.3506, 1.3055, 1.2587,
			1.2111, 1.1638, 1.1184, 1.0764, 1.0401, 1.0127, 1e99};
	double[] angle = {0,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90};
	double alpha;
	alpha=Math.asin(kappa)/Math.PI*180;
	
	int i=0;
	for (;;) {
	    if (Math.abs(angle[i]-alpha)<=2.5) break;
	    i++;
	}
	return tab[i];
    }
  
    
    // Inner member class of Ising2D to overwrite the paint method 
    // for repainting the screen
    public class DrawArea extends Canvas {
	public void paint(Graphics g) {
	    Ising2D.this.plotLattice(); // same as plotLattice();
	}
    }
	
    /** clear the draw area */
    void clearcanv() {
	int width=canv.getSize().width;
	int height=canv.getSize().height;
	
	g.setColor(backg);
	g.fillRect(0,0,width,height);
    }

    /** plot the lattice */
    public void plotLattice() {
	int width=canv.getSize().width;
	int height=canv.getSize().height;
	int xwidth=width/Nx;
	int ywidth=height/Ny;
	
	// first test, if Spin array already instantiated
	if (Spin!=null) {
	    for (x=0; x<Nx; x++) {
		for (y=0; y<Ny; y++) {
		    if (Spin[x][y]==1) { g.setColor(Color.black); }
		    else { g.setColor(backg); }
		    g.fillRect(x*xwidth,y*ywidth,xwidth,ywidth); 
		    if (DEBUG==2) {
			if (Math.abs(Spin[x][y]) != 1) 
			    System.out.println("ERROR: Spin is not 1 or -1");
			if (Spin[x][y] > 0) System.out.print("*");
			else System.out.print(" ");
		    }
		}
		if (DEBUG==2) System.out.println();
	    }
	    if (DEBUG==2) System.out.println();
	    if (DEBUG==2) System.out.println();
	} 	
    }
    
    /** Finite Size Scaling : EXACT */
    public void finiteSizeScaling() {
	double free, dT, Jstart, Jstop, invtemp, h;
	int Jpoints;

	Jstart=0.3; Jstop=0.6; Jpoints=300;
	dT = (Jstop-Jstart)/(Jpoints-1);
	h=0.001;
	Cbexact = new double[Jpoints];
	Jexact = new double[Jpoints];

	// 200 points are computed
	for (int i=0; i<Jpoints; i++) {
	    invtemp=Jstart+i*dT;
	    Jexact[i]=invtemp;
	    // free = freeEnergy(invtemp);
	    Cbexact[i]=invtemp*invtemp*diff2(invtemp,h);
	    if (DEBUG==2) System.out.println(invtemp+" "+Cbexact[i]);	    
	}
    }

    /** Compute exact free energy */
    public double freeEnergy(double b) {
	double[] c = new double[2*Nx];
	double[] g = new double[2*Nx];
	double b2=2*b;
	double m2=Ny/2;

	for (int l=1;l<2*Nx; l++) {
	    c[l]=Sfun.cosh(b2)/Sfun.tanh(b2)-Math.cos(l*Math.PI/Nx); }
	g[0]=b2+Math.log(Sfun.tanh(b));
	for (int l=1;l<2*Nx; l++) {
	    g[l]=Math.log(c[l]+Math.sqrt(c[l]*c[l]-1)); }

	double z1=1,z2=1,z3=1,z4=1;
	double exp1,exp2,exp3;
	double gsum=0;
	double g2l1=0, g2l=0;

	for (int l=0; l<Nx; l++) {
	    g2l1=g[2*l+1];
	    g2l=g[2*l];
	    gsum+=g2l;
	    if (Ny*g2l<=87.5) { exp1 = Math.exp(-Ny*g2l); }
	    else { exp1=0; }
	    if (Ny*g2l1<=87.5) { exp2 = Math.exp(-Ny*g2l1); }
	    else { exp2=0; }
	    exp3 = Math.exp(m2*(g2l1-g2l));
	    
	    z1*=exp3*(1+exp2);
	    z2*=exp3*(1-exp2);
	    z3*=(1+exp1);
	    z4*=(1-exp1);
	}
	gsum/=Nx;
	return (Math.log(z1+z2+z3+z4)-Math.log(2))/size + 0.5*gsum 
	    + 0.5*Math.log(2*Sfun.sinh(b2));
    }

    /** Compute second derivatice using 5 point formula */
    public double diff2(double x, double h) {
	double[] coeff = {-1, 16, -30, 16, -1};
	double result=0;
	for (int i=-2; i<=2; i++) {
	    result+=coeff[i+2]*freeEnergy(x+i*h);
	}
	return result/(12*h*h);
    }

    /** Initialize the plots */
    void InitPlots() {
        // plot 1
        plot1.setButtons(true);
        plot1.setMarksStyle("points");
        plot1.setXLabel("kT/2J");
        plot1.setYLabel("M");
        plot1.setTitle("Magnetization");
        // plot 2
        plot2.setButtons(true);
        plot2.setMarksStyle("points");
        plot2.setXLabel("kT/2J");
        plot2.setYLabel("Chi");
        plot2.setTitle("magnetic Susceptibility");
        // plot 3
        plot3.setButtons(true);
        plot3.setMarksStyle("points");
        plot3.setXLabel("kT/2J");
        plot3.setYLabel("Cb");
        plot3.setTitle("specific heat at constant B");
        //plot3.addLegend(3,"");
        //plot3.addLegend(4,"");
    }

    /** Main wrapper to run as application */
    public static void main(String[] args) {
        f = new Frame("Ising Model in 2D");
	// f.setLayout(new GridLayout(2,2));
	f.setLayout(new BorderLayout(10,10));
        // setup the window sizes
        f.setSize(XSize,YSize);
	// f.setResizable(false);
	// add Applet Panel to Frame
	application=1;
        a = new Ising2D();
	display=a;
        f.add(a);
        // display the frame with the Applet Panel
        f.show();
        // start applet/program
        a.init();
        // Close Window event
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
                { a.stop(); f.dispose(); System.exit(0);}
        }); 	
    }
    
    /** Starts the calculation in the first place.
        Gets called by the main program or directly by the browser. */
    public void init() {
	Ising2D Ising = new Ising2D(); 
	initialSetup();
	// use a new thread for the calculation: the run() method is invoked
	/* Thread thread = new Thread(Ising);
	thread.setPriority(Thread.MIN_PRIORITY);
	thread.start(); */
	run();
    }  

    /** determine the panel to draw in, in the constructor */
    public Ising2D() {	
	if (application==0) display=this; 
    }

} // Ising2D
