package simulation.BlackScholes;

/**
 * AmericanPut.java
 *
 *
 * Created: Sun Jun 13 13:25:36 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

/** An American put option. */
public class AmericanPut extends Put {

    public void freeBoundaries(double[] Vold, double[] Vnew, int noAssetSteps,
                               double[] asset, double time) {
        for (int i=0; i<noAssetSteps; i++) {
            Vold[i]=Math.max(Vnew[i],this.payoff(asset[i],this.strike,time)); }
    }

    public double exactSolution(double assetPrice, double time) {
        return 0;
    }

} // AmericanPut
