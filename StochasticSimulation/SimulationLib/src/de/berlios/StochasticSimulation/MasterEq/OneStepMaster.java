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
package simulation.MasterEq;

/**
 * OneStepMaster.java
 *
 *
 * Created: Wed May 26 11:29:18 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

/**
 * 
 * The interface for defining one step processes used 
 * for master equations.
 * <p>
 * You have to define your own class, which implements
 * this interface and put your own functions for the gain
 * and loss term there. 
 * <p>
 * The function should return in the first argument the gain
 * term and in the second one the loss term. These can then
 * be used to solve/simulate the master equation.
 * <p>
 * d/dt P(n) = g(n,t) P(n) + r(n,t) P(n-1)
 * <p>
 * g(n,t) = gain, r(n,t) = loss
 * <p>
 */
public interface OneStepMaster  {
    
    public double[] oneStepRates(int n);
	
} // OneStepMaster
