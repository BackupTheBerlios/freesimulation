package simulation.BlackScholes;

/**
 * BinaryCall.java
 *
 *
 * Created: Sun Jun 13 13:25:36 1999
 *
 * @author Peter Biechele
 * @version 1.1
 */

/** A binary call option. */
public abstract class BinaryCall extends Call  {

    /** A heaviside function of S-E. */
    public double payoff(double asset, double strike, double time) {
        double dummy=asset-strike;
        if (dummy>0) {
            return 1.0; }
        else {
            return 0.0; }
    }

} // BinaryCall
