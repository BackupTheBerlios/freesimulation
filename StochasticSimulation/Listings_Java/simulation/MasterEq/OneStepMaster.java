package simulation.MasterEq;

/**
 * OneStepMaster.java
 *
 *
 * Created: Wed May 26 11:29:18 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

/**
 * 
 * The interface for defining one step processes used 
 * for master equations.
 * <p>
 * You have to define your own class, which implements
 * this interface and put your own functions for the gain
 * and loss term there. 
 * <p>
 * The function should return in the first argument the gain
 * term and in the second one the loss term. These can then
 * be used to solve/simulate the master equation.
 * <p>
 * d/dt P(n) = g(n,t) P(n) + r(n,t) P(n-1)
 * <p>
 * g(n,t) = gain, r(n,t) = loss
 * <p>
 */
public interface OneStepMaster  {
    
    public double[] oneStepRates(int n);
	
} // OneStepMaster
