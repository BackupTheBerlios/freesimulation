package simulation.BlackScholes;

/**
 * Call.java
 *
 *
 * Created: Sun Jun 13 13:25:36 1999
 *
 * @author Peter Biechele
 * @version 1.1
 */

/** A call option. */
public abstract class Call extends Option  {

    public double payoff(double asset, double strike, double time) {
        return Math.max(asset-strike,0.0);
    }

    public void boundaryConditions(double time, 
                                   double[] Value, double[] asset) {
        int lastIndex = Value.length-1;   
        Value[0]=0;
        Value[lastIndex]=2*Value[lastIndex-1]-Value[lastIndex-2];
        return;
    }

} // Call
