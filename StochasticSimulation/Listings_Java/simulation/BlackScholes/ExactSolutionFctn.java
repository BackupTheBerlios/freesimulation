package simulation.BlackScholes;

/**
 * ExactSolutionFctn.java
 *
 *
 * Created: Sun Jun 13 13:34:17 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

/**
   The exact analytical solution interface for an option. </p>
   You can implement this, if you know the exact solution for an option. </p>
   The option class is an abstract class, which should be subclassed
   instead of directly implementing this interface. 
*/
public interface ExactSolutionFctn  {
    
    /** returns the exact solution for the option */
    public double exactSolution(double assetPrice, double time);
    
} // ExactSolutionFctn
