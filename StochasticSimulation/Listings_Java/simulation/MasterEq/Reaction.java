package simulation.MasterEq;

/**
 * Reaction.java
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
 * Here a chemical reaction is defined.
 * <p>
 * g(n) = a, r(n) = k n 
 * <p>
 */
public class Reaction implements OneStepMaster {
    
    private double k, a;

    public Reaction() {	
	this(1.0, 100.0); }

    public Reaction(double param1, double param2) {
	this.k=param1; 
    	this.a=param2; }

    public double getK() {
	return this.k; }
    public double getA() {
	return this.a; }

    public void setK(double param) {
	this.k=param;
	return; }
    public void setA(double param) {
	this.a=param;
	return; }

    /** Returns the gain and loss rates for the given occupation
	given as an argument. Here for the special case of the
	continous time random walk (n is not used here). */
    public double[] oneStepRates(int n) {
	double[] dummy = new double[2];
	dummy[0] = a;
	dummy[1] = k*n;
	return dummy;	
    }
    
} // RandomWalk
