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
package simulation;

import java.util.Random;

public final class DataAnalysis {

    /** Calculate the factorial of an integer. 
        Possible up to factorial of 170, then it returns zero. */
    public static double factorial(int fact) {
        if (fact>170) {
            return 0;
        }
        if (fact>=0) {
            double prod=1;
            for (int i=2; i<=fact; i++) {
                prod*=i;
            }
            return prod;
        }
        else {
            return 0;
        }
    }

}
