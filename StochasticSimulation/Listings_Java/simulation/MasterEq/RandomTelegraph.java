package simulation.MasterEq;

/**
 * RandomTelegraph.java
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
 * Here a (binary) random telegraph process is defined.
 * <p>
 * g(n) = b (1-n), r(n) = a n
 * <p>
 */
public class RandomTelegraph implements OneStepMaster {
    
    private double a, b;

    public RandomTelegraph() {	
	this(0.1, 0.9); }

    public RandomTelegraph(double param1, double param2) {
	this.a=param1; 
    	this.b=param2; }

    public double getA() {
	return this.a; }
    public double getB() {
	return this.b; }

    public void setA(double param) {
	this.a=param;
	return; }
    public void setB(double param) {
	this.b=param;
	return; }

    /** Returns the gain and loss rates for the given occupation
	given as an argument. Here for the special case of the
	continous time random walk (n is not used here). */
    public double[] oneStepRates(int n) {
	double[] dummy = new double[2];
	dummy[0] = b*(1-n);
	dummy[1] = a*n;
	return dummy;	
    }
    
} // RandomWalk
