package simulation.SDE;

/**
 * NoiseInducedTransition.java
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
 * Here an example of a noise induced transition. The process is 
 * defined by a potential U(x) = x(1-x).
 * <p>
 * dX = X(0.5-X) dt + (epsilon x(1-x))^2 dW
 * <p>
 */
public class NoiseInducedTransition implements SDEfunction {
    
    private double epsilon;

    public NoiseInducedTransition() { 
        this(1.0); }
    public NoiseInducedTransition(double param1) {        
        epsilon=param1; }

    public double getEpsilon() {
        return this.epsilon; }

    public void setEpsilon(double param) {
        this.epsilon=param; }

    public double[] SDEterms(double x, double t) {
        double[] retVal = new double[2];
        double dummy=x*(1-x);

        retVal[0] = dummy;
        retVal[1] = Math.pow(epsilon*dummy,2);
        return retVal;
    }
    
} // NoiseInducedTransition
