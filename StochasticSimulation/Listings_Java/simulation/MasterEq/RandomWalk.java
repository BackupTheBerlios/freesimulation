package simulation.MasterEq;

/**
 * RandomWalk.java
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
 * Here a continous time random walk is defined.
 * <p>
 * g(n) = q, r(n) = q
 * <p>
 */
public class RandomWalk implements OneStepMaster {
    
    private double q;

    public RandomWalk() {	
	this(1.0); }

    public RandomWalk(double q) {
	this.q=q; }
    
    public double getQ() {
	return this.q;
    }

    public void setQ(double q) {
	this.q=q;
	return; }

    /** Returns the gain and loss rates for the given occupation
	given as an argument. Here for the special case of the
	continous time random walk (n is not used here). */
    public double[] oneStepRates(int n) {
	double[] dummy = new double[2];
	dummy[0] = this.q;
	dummy[1] = this.q;
	return dummy;	
    }
    
} // RandomWalk
