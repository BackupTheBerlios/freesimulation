package simulation.BlackScholes;

/**
 * Put.java
 *
 *
 * Created: Sun Jun 13 13:25:36 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

/** A put option. */
public abstract class Put extends Option  {

    public double payoff(double asset, double strike, double time) {
        return Math.max(strike-asset,0.0);
    }

    public void boundaryConditions(double time, 
                                   double[] Value, double[] asset) {
        int lastIndex = Value.length-1; 
        Value[lastIndex]=0;
        Value[0]=strike*Math.exp(-(interestRate)*(maturityTime-time));
        return;
    }

} // Put
