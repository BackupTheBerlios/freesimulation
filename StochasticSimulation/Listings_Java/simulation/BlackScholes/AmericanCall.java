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
 * AmericanCall.java
 *
 *
 * Created: Sun Jun 13 13:25:36 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import simulation.BlackScholes.*;
import VisualNumerics.math.*;

/** An American call option. */
public class AmericanCall extends Call {

    public void freeBoundaries(double[] Vold, double[] Vnew, int noAssetSteps,
                               double[] asset, double time) {
        for (int i=0; i<noAssetSteps; i++) {
            Vold[i]=Math.max(Vnew[i],this.payoff(asset[i],this.strike,time)); }
    }

    public double exactSolution(double assetPrice, double time) {
        return 0;
    }

} // AmericanCall
