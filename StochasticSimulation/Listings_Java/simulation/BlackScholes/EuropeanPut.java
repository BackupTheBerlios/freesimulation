package simulation.BlackScholes;

/**
 * EuropeanPut.java
 *
 *
 * Created: Sun Jun 13 13:25:36 1999
 *
 * @author Peter Biechele
 * @version 1.1
 */

import simulation.BlackScholes.*;
import VisualNumerics.math.*;

/** An European put option. */
public class EuropeanPut extends Put {

    public double exactSolution(double asset, double time) {
        double d1,d2,dummy;
        d1=(Math.log(asset/strike)+(interestRate-dividend+vol2half)
            *(maturityTime-time)) / (volatility*Math.sqrt(maturityTime-time));
        d2=d1-volatility*Math.sqrt(maturityTime-time);
        dummy = -asset*Math.exp(-dividend*(maturityTime-time))
                *Statistics.normalCdf(-d1) + strike*Math.exp
            (-interestRate*(maturityTime-time)) * Statistics.normalCdf(-d2);
        return dummy;            
    }

} // EuropeanPut
