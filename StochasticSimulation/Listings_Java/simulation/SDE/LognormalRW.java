package simulation.SDE;

/**
 * LognormalRW.java
 *
 *
 * Created: Tue Jun  1 10:50:30 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

/**
 * <p>
 * A class implementing the SDEfunction interface for solving
 * a stochastic differential equation (SDE).
 * <p>
 * You can access all parameters by using the standard bean
 * method names. For example to get a parameter "test" you simply
 * call the method getTest() of the object and to set a parameter
 * "sigma" you call setSigma(value).
 * <p>
 * Here an lognormal random walk is defined.
 * <p>
 * dX = mu X dt + sigma X dW
 * <p>
 * <p> Because we know an exact solution, we also implement the exact solution
 * interface. </p>
 */
public class LognormalRW implements SDEfunction, ExactSolution {
    
    private double mu;
    private double sigma;

    public LognormalRW() { 
        this(1.0, 1.0); }
    public LognormalRW(double param1, double param2) {        
        mu=param1;
        sigma=param2; }

    public double getMu() {
        return this.mu; }
    public double getSigma() {
        return this.sigma; }

    public void setMu(double param) {
        this.mu=param; }
    public void setSigma(double param) {
        this.sigma=param; }

    /** The SDE drift and diffusion terms. */
    public double[] SDEterms(double x, double t) {
        double[] retVal = new double[2];

        retVal[0] = mu*x;
        retVal[1] = sigma*x;
        return retVal;
    }
    
    //////////////////////////////////////////////////////
    
    /** The random number generator used for the exact solution. 
        You can change it because it is public. */
    public java.util.Random rand = new java.util.Random();

    /** <p> The exact solution for the lognormal RW. Use the nextGaussian 
        method of rand object to get normally distributed random 
        numbers. </p> */
    public double SDEexact(double time, double ValueAtZero) {
        double dummy = rand.nextGaussian();
        return ValueAtZero*Math.exp((mu-0.5*sigma*sigma)*time
                                    +sigma*dummy*Math.sqrt(time));
    }

} // LognormalRW


