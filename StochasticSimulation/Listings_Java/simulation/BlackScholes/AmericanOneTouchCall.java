package simulation.BlackScholes;

/**
 * AmericanOneTouchCall.java
 *
 *
 * Created: Sun Jun 13 13:25:36 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import VisualNumerics.math.*;

/** An American one-touch call option. */
public class AmericanOneTouchCall extends BinaryCall {

    public void freeBoundaries(double[] Vold, double[] Vnew, int noAssetSteps,
                               double[] asset, double time) {
        for (int i=0; i<noAssetSteps; i++) {
            Vold[i]=Math.max(Vnew[i],this.payoff(asset[i],this.strike,time)); }
    }

    public double exactSolution(double asset, double time) {
        double d1,d5,dummy;

        if (time==maturityTime || asset==0 ) return 0;
        d1=(Math.log(asset/strike)+(interestRate-dividend+vol2half)
            *(maturityTime-time)) / (volatility*Math.sqrt(maturityTime-time));
        d5=(Math.log(asset/strike)-(interestRate-dividend+vol2half)
            *(maturityTime-time)) / (volatility*Math.sqrt(maturityTime-time));
        dummy=Math.pow((strike/asset),2*interestRate/Math.pow(volatility,2))
            * Statistics.normalCdf(d5) + asset/strike * Statistics.normalCdf(d1);
        return dummy;
    }

    /** We have an exact solution, so override and return true here. */
    public boolean existsExactSolution() {
        return true; }

} // AmericanOneTouchCall
