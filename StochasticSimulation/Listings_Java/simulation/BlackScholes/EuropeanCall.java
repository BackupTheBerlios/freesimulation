package simulation.BlackScholes;

/**
 * EuropeanCall.java
 *
 *
 * Created: Sun Jun 13 13:25:36 1999
 *
 * @author Peter Biechele
 * @version 1.1
 */

import simulation.BlackScholes.*;
import VisualNumerics.math.*;

/** An European call option. */
public class EuropeanCall extends Call  {

    public double exactSolution(double asset, double time) {
        setup(asset,time);
        return asset*Math.exp(-dividend*(maturityTime-time))
            *Statistics.normalCdf(d[0]) - strike*Math.exp
            (-interestRate*(maturityTime-time)) * Statistics.normalCdf(d[1]);
    }
    public double exactTheta(double asset, double time) {
        setup(asset,time);
        return -volatility*asset*Math.exp(-dividend*dt)*nPrime(d[0])
                                 / (2*Math.sqrt(dt))
            + dividend*asset*Statistics.normalCdf(d[0])*Math.exp(-dividend*dt)
            - interestRate*strike*Math.exp(-interestRate*dt)
                                 *Statistics.normalCdf(d[1]);
    }
    public double exactDelta(double asset, double time) {
        setup(asset,time);
        return Math.exp(-dividend*dt)*Statistics.normalCdf(d[0]);
    }
    public double exactGamma(double asset, double time) {
        setup(asset,time);
        if (asset!=0) {
            return Math.exp(-dividend*dt)*nPrime(d[0]) 
                / (volatility*asset*Math.sqrt(dt)); }
        else {
            return 0; }
    }

    /** We have an exact solution, so override and return true here. */
    public boolean existsExactSolution() {
        return true; }
    
} // EuropeanCall
