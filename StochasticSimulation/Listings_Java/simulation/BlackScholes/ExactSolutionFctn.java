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

public interface ExactSolutionFctn  {
    
    /** returns the exact solution for the option */
    public double exactSolution(double assetPrice, double time);
    
} // ExactSolutionFctn
