package simulation.SDE;

/**
 * ExactSolution.java
 *
 *
 * Created: Sat Jul  3 21:37:09 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import java.util.*;

/** Define the exact solution to a SDE if available. You supply the
    time argument and the initial value and it returns the value 
    at that time. You can
    implement this interface for SDEs, which have an exact solution. */
public interface ExactSolution  {
    
    public double SDEexact(double time, double ValueAtZero) ;
    
} // ExactSolution
