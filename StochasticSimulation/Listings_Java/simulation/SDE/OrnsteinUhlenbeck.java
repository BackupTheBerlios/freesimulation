package simulation.SDE;

/**
 * OrnsteinUhlenbeck.java
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
 * Here an Ornstein-Uhlenbeck Process is defined.
 * <p>
 * dX = -drift X dt + diffusion dW
 * <p>
 */
public class OrnsteinUhlenbeck implements SDEfunction {
    
    private double drift;
    private double diffusion;

    public OrnsteinUhlenbeck() { 
        this(1.0, 1.0); }
    public OrnsteinUhlenbeck(double param1, double param2) {        
        drift=param1;
        diffusion=param2; }

    public double getDrift() {
        return this.drift; }
    public double getDiffusion() {
        return this.diffusion; }

    public void setDrift(double param) {
        this.drift=param; }
    public void setDiffusion(double param) {
        this.diffusion=param; }

    public double[] SDEterms(double x, double t) {
        double[] retVal = new double[2];

        retVal[0] = -drift*x;
        retVal[1] = Math.sqrt(diffusion);
        return retVal;
    }
    
} // OrnsteinUhlenbeck
