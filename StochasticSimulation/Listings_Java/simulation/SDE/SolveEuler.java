package simulation.SDE;

/**
 * SolveEuler.java
 *
 *
 * Created: Sat Jul  3 22:49:38 1999
 *
 * @author Peter Biechele
 * @version
 */

import java.util.*;
/**
 *
 */
public class SolveEuler  {
    
    /** The public random number genrator. Change it, if
        you want to use your own. The method nextGaussian() is used. */
    public Random rand;

    /** The process to be integrated. Is of type SDEfunction. */
    private SDEfunction process;

    /** The initial value x(t=0). Default = 1.0  */
    private double xStart;
    /** The start time (mostly = 0). */
    private double tStart;
    /** The time step. */
    private double deltaT;
    /** The end time of the simulation. */
    private double endTime;

    // internal variables
    /** The number of time steps for the computation. */
    private int nStep;

    public SolveEuler() {
        this(new simulation.SDE.LognormalRW(),1.0,0.05,1.0,0.0); }
    public SolveEuler(SDEfunction process, double endTime, double deltaT, 
                      double xStart, double tStart) {
        this(process,endTime,deltaT,xStart,tStart,new Random()); }
    public SolveEuler(SDEfunction process, double endTime, double deltaT, 
                      double xStart, double tStart, Random rand) {
        this.rand=rand;
        this.process=process;
        this.xStart=xStart;
        this.tStart=tStart;
        this.deltaT=deltaT;
        this.endTime=endTime;
        this.nStep=(int)Math.round(endTime/deltaT);
    }

    public int getNStep() {
        return nStep; }

    public double[] solve() {
        double dW, t, x;
        double[] terms = new double[2];
        double[] xpos = new double[nStep];

        xpos[0]=xStart;

        t=tStart;
        x=xStart;            
        // time loop
        for (int i=1; i<nStep; i++) {
            t+=deltaT;
            /* get the terms:
               drift    :   a(x,t) = terms[0]
               diffusion:   b(x,t) = terms[1]  */
            terms = process.SDEterms(x,t);
            dW=Math.sqrt(deltaT)*rand.nextGaussian();
            x+= (terms[0]*deltaT+terms[1]*dW);
            // store values in arrays
            xpos[i]=x; }
        return xpos;
    }

} // SolveEuler
