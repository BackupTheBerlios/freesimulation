/**
 * 
 *     Copyright (C) 2002  P. Biechele, F. Petruccione
 *
 *
 *     This program is free software; you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation; either version 2 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program; if not, write to the Free Software
 *     Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *
 *     
 */
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
