package simulation.SDE;

/**
 * SDEfunction.java 
 *
 *
 * Created: Tue Jun  1 10:53:41 1999 
 *
 * @author Peter Biechele 
 * @version 1.1 
 */

/**
 * 
 * The interface for defining stochastic differential equations.
 * <p>
 * You have to define your own class, which implements
 * this interface and put your own functions for the drift and
 * the diffusion there. 
 * <p>
 * The function should return in the first argument the drift
 * term and in the second one the diffusion term. These can then
 * be used to solve the SDE.
 * <p>
 * dX = a(X,t) dt + b(X,t) dW
 * <p>
 * a(X,t) = drift, b(X,t) = diffusion
 * <p>
 */
public interface SDEfunction  {
    
    public double[] SDEterms(double x, double t); 
    
} // SDEfunction
