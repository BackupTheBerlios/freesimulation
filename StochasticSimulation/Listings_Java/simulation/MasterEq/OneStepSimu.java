package simulation.MasterEq;

/**
 * OneStepSimu.java
 *
 *
 * Created: Wed May 26 12:01:23 1999
 *
 * @author Peter Biechele
 * @version
 */

import simulation.MasterEq.*;
import java.util.*;

public class OneStepSimu  {
    
    // Random Numbers for all realizations
    public static Random rand = new Random();

    // parameters
    private OneStepMaster process;
    private int nStart;
    private double tStart, tEnd;
    private double tSample;

    // variables used
    private int tSave;

    // return values depending on time
    private int[] array;

    /** construct with standard parameters. */
    public OneStepSimu() {
	this(new RandomWalk(), 0, 0.0, 50.0, 1.0); }

    /** construct with standard parameters. */
    public OneStepSimu(OneStepMaster onestep) {
	this(onestep, 0, 0.0, 50.0, 1.0); }

    /** construct with only some standard parameters: give process and 
	number of particles at start time. */
    public OneStepSimu(OneStepMaster onestep, int nStart) {
	this(onestep, nStart, 0.0, 50.0, 1.0); }

    /** Set parameters and start computation of one realization. */
    public OneStepSimu(OneStepMaster onestep, 
		       int nStart, double tStart, double tEnd, double tSample) {
	this.process=onestep; // REFERENCEs !!!! can be changed from caller
	this.nStart=nStart;
	this.tStart=tStart;
	this.tEnd=tEnd;
	this.tSample=tSample;

	// instantiate the arrays 
	this.tSave = (int)Math.floor(tEnd/tSample)+1;
	this.array = new int[tSave];

	// start the computation
	this.compute();
    }

    private void compute() {
	double lambda=1, t, stopTime=0;
	int dummyN, sample;
	double[] rate = new double[2];

	array[0]=nStart;
	dummyN=nStart;
	t=0; 
	sample=1;
	while ( sample < tSave ) {
	    stopTime=sample*tSample;
	    while ( t < stopTime ) { 
		rate = process.oneStepRates(dummyN);
		lambda = rate[0]+rate[1];
		if (lambda>0) {
		    t=t-Math.log(rand.nextDouble())/lambda;		    
		    if (rand.nextDouble()*lambda > rate[1])  {
			dummyN++; }
		    else {
			dummyN--; } }		
		else {
		    break; }
	    }
	    if (lambda>0) {
		array[sample]=dummyN;
		sample++; }
	    else {
		break; }
	}
    } 

    public int[] getArray() {
	return array;
    }

    /** A test main function  */
    public static void main(String[] args) {
	System.out.println(" Producing one realization:");
	OneStepSimu process = new OneStepSimu();
	int[] result = new int[51];
	result=process.getArray();
	for (int i=0; i<51; i++) {
	    System.out.println(" time: "+i+" position: "+result[i]);
	}
    }

} // OneStepSimu
