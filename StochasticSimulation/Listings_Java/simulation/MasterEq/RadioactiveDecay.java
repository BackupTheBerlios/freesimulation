package simulation.MasterEq;

/**
 * RadioactiveDecay.java
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
 * Here a radioactive decay is defined.
 * <p>
 * g(n) = 0, r(n) = gamma n
 * <p>
 */
public class RadioactiveDecay implements OneStepMaster {
    
    /** Decay constant */
    private double gamma;

    public RadioactiveDecay() {
	this(0.1); }

    public RadioactiveDecay(double param) {
	this.gamma=param; }
    
    public double getGamma() {
	return this.gamma; }

    public void setGamma(double param) {
	this.gamma=param;
	return; }

    /** Returns the gain and loss rates for the given occupation
	given as an argument. <p>
    The zeroth return argument: gain rate <p>
    The first return argument : loss rate <p>
    */
    public double[] oneStepRates(int n) {
	double[] dummy = new double[2];
	dummy[0] = 0;
	dummy[1] = this.gamma*n;
	return dummy;	
    }
    
} // RandomWalk
