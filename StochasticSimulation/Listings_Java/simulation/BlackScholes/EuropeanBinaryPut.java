package simulation.BlackScholes;

/**
 * EuropeanBinaryPut.java
 *
 *
 * Created: Sun Jun 13 13:25:36 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import simulation.BlackScholes.*;
import VisualNumerics.math.*;

/** An european put option. */
public class EuropeanBinaryPut extends BinaryPut {

    public double exactSolution(double asset, double time) {
        double d1,d2,dummy;
        d1=(Math.log(asset/strike)+(interestRate-dividend+vol2half)
            *(maturityTime-time)) / (volatility*Math.sqrt(maturityTime-time));
        d2=d1-volatility*Math.sqrt(maturityTime-time);
        dummy = Math.exp(-interestRate*(maturityTime-time)) 
            * (1-Statistics.normalCdf(d2));
        return dummy;            
    }
    
} // EuropeanBinaryCall
