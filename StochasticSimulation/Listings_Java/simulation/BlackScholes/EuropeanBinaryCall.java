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
 * EuropeanBinaryCall.java
 *
 *
 * Created: Sun Jun 13 13:25:36 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import simulation.BlackScholes.*;

/** An european call option. */
public class EuropeanBinaryCall extends BinaryCall {

    public double exactSolution(double asset, double time) {
        setup(asset,time);
        return Math.exp(-interestRate*dt)*Statistics.normalCdf(d[1]);
    }
    public double exactTheta(double asset, double time) {
        setup(asset,time);
        if (dt!=0) {
            return interestRate*Math.exp(-interestRate*dt)
                *Statistics.normalCdf(d[1])
                + Math.exp(-interestRate*dt)*nPrime(d[1])
                * (d[0]/(2*dt)-(interestRate-dividend)/(volatility*Math.sqrt(dt))); }
        else {
            return 0; }
    }
    public double exactDelta(double asset, double time) {
        setup(asset,time);
        if (asset!=0) {
            return Math.exp(-interestRate*dt)*nPrime(d[1])
                / (volatility*asset*Math.sqrt(dt)); }
        else {
            return 0; }
    }
    public double exactGamma(double asset, double time) {
        setup(asset,time);
        if (asset!=0) {     
            return - Math.exp(-interestRate*dt)*d[0]*nPrime(d[1]) 
                / (volatility*volatility*asset*asset*dt); }
        else {
            return 0; }
    }

    /** We have an exact solution, so override and return true here. */
    public boolean existsExactSolution() {
        return true; }

} // EuropeanBinaryCall
