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
 * ExactSolutionFctn.java
 *
 *
 * Created: Sun Jun 13 13:34:17 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

/**
   The exact analytical solution interface for an option. </p>
   You can implement this, if you know the exact solution for an option. </p>
   The option class is an abstract class, which should be subclassed
   instead of directly implementing this interface. 
*/
public interface ExactSolutionFctn  {
    
    /** returns the exact solution for the option */
    public double exactSolution(double assetPrice, double time);
    
} // ExactSolutionFctn
