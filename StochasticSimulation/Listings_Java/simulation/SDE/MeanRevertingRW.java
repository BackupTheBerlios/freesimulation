package simulation.SDE;

/**
 * MeanRevertingRW.java
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
 * Here a mean reverting random walk is defined.
 * <p>
 * dX = ( nu - mu X ) dt + sigma dW
 * <p>
 */
public class MeanRevertingRW implements SDEfunction {
    
    private double mu;
    private double sigma;
    private double nu;

    public MeanRevertingRW() { 
        this(1.0, 1.0, 1.0); }
    public MeanRevertingRW(double param1, double param2, double param3) {     
        mu=param1;
        sigma=param2; 
        nu=param3;  }
    
    public double getMu() {
        return this.mu; }
    public double getSigma() {
        return this.sigma; }
    public double getNu() {
        return this.nu; }

    public void setMu(double param) {
        this.mu=param; }
    public void setSigma(double param) {
        this.sigma=param; }
    public void setNu(double param) {
        this.nu=param; }

    public double[] SDEterms(double x, double t) {
        double[] retVal = new double[2];

        retVal[0] = (nu-mu*x);
        retVal[1] = sigma;
        return retVal;
    }
    
} // MeanRevertingRW
