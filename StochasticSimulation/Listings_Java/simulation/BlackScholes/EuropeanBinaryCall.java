package simulation.BlackScholes;

/**
 * EuropeanBinaryCall.java
 *
 *
 * Created: Sun Jun 13 13:25:36 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import simulation.BlackScholes.*;
import VisualNumerics.math.*;

/** An european call option. */
public class EuropeanBinaryCall extends BinaryCall {

    public double exactSolution(double asset, double time) {
        double d1,d2,dummy;
        d1=(Math.log(asset/strike)+(interestRate-dividend+vol2half)
            *(maturityTime-time)) / (volatility*Math.sqrt(maturityTime-time));
        d2=d1-volatility*Math.sqrt(maturityTime-time);
        dummy = Math.exp(-interestRate*(maturityTime-time)) 
            * Statistics.normalCdf(d2);
        return dummy;            
    }

} // EuropeanBinaryCall
