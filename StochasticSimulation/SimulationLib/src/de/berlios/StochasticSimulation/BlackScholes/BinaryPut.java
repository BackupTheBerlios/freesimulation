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

    public void boundaryConditions(double time, 
                                   double[] Value, double[] asset) {
        int lastIndex = Value.length-1; 
        Value[lastIndex]=0;
        Value[0]=Math.exp(-(interestRate)*(maturityTime-time));
        return;
    }

} // BinaryPut
