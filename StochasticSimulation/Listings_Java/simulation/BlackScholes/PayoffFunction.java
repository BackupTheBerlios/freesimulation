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

public interface PayoffFunction  {

    public double payoff(double asset, double strike);
    
} // PayoffFunction
