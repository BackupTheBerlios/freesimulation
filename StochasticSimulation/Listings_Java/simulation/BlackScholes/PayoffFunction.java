package simulation.BlackScholes;

/**
 * PayoffFunction.java
 *
 *
 * Created: Sun Jun 13 13:25:36 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

/**
   The payoff function interface for an option. </p>
   You have to implement this for any option you want to define. </p>
   The option class is an abstract class, which should be subclassed
   instead of directly implementing this interface. 
*/
public interface PayoffFunction  {

    public double payoff(double asset, double strike, double time);
    
} // PayoffFunction
