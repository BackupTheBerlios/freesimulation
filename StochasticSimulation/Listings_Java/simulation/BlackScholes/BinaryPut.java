package simulation.BlackScholes;

/**
 * BinaryPut.java
 *
 *
 * Created: Sun Jun 13 13:25:36 1999
 *
 * @author Peter Biechele
 * @version 1.1
 */

/** A binary put option. */
public abstract class BinaryPut extends Put  {

    /** A heaviside function of E-S. */
    public double payoff(double asset, double strike, double time) {
        double dummy=asset-strike;
        if (dummy<0) {
            return 1.0; }
        else {
            return 0.0; }
    }

} // BinaryPut
