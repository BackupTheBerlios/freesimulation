package simulation.MasterEq;

/**
 * PoissonProcess.java
 *
 *
 * Created: Wed May 26 11:26:45 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

/**
 * <p>
 * A class implementing the OneStepMaster interface for solving
 * or simulating a master equation.
 * <p>
 * You can access all parameters by using the standard bean
 * method names. For example to get a parameter "test" you simply
 * call the method getTest() of the object and to set a parameter
 * "sigma" you call setSigma(value).
 * <p>
 * Here a Poisson process is defined.
 * <p>
 * g(n) = q, r(n) = 0
 * <p>
 */
public class PoissonProcess implements OneStepMaster {
    
    /** Decay constant */
    private double q;

    public PoissonProcess() {
	this(10.0); }

    public PoissonProcess(double param) {
	this.q=param; }
    
    public double getQ() {
	return this.q; }

    public void setQ(double param) {
	this.q=param;
	return; }

    /** Returns the gain and loss rates for the given occupation
	given as an argument. <p>
    The zeroth return argument: gain rate <p>
    The first return argument : loss rate <p>
    */
    public double[] oneStepRates(int n) {
	double[] dummy = new double[2];
	dummy[0] = this.q;
	dummy[1] = 0;
	return dummy;	
    }
    
} // RandomWalk
