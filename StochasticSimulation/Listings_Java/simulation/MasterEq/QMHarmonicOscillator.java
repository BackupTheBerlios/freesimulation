package simulation.MasterEq;

/**
 * QMHarmonicOscillator.java
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
 * Here a quantum mechanical harmonic oscillator is defined.
 * <p>
 * g(n) = beta n, r(n) = alpha n
 * <p>
 */
public class QMHarmonicOscillator implements OneStepMaster {
    
    private double alpha, beta, gamma;

    public QMHarmonicOscillator() {	
	this(0.45, 0.4); }

    public QMHarmonicOscillator(double param1, double param2) {
        this.beta=param2;
        this.alpha=param1; }
    
    public double getAlpha() {
	return this.alpha; }
    public double getBeta() {
	return this.beta; }

    public void setAlpha(double param) {
	this.alpha=param;
	return; }
    public void setBeta(double param) {
	this.beta=param;
	return; }

    /** Returns the gain and loss rates for the given occupation
        given as an argument. <p>
        The zeroth return argument: gain rate <p>
        The first return argument : loss rate <p>
    */
    public double[] oneStepRates(int n) {
	double[] dummy = new double[2];
	dummy[0] = beta*n;
	dummy[1] = alpha*n;
	return dummy;	
    }
    
} 
