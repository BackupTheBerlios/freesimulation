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
package de.berlios.StochasticSimulation.SDE;

/**
 * SDEfunction.java 
 *
 *
 * Created: Tue Jun  1 10:53:41 1999 
 *
 * @author Peter Biechele 
 * @version 1.1 
 */

/**
 * 
 * The interface for defining stochastic differential equations.
 * <p>
 * You have to define your own class, which implements
 * this interface and put your own functions for the drift and
 * the diffusion there. 
 * <p>
 * The function should return in the first argument the drift
 * term and in the second one the diffusion term. These can then
 * be used to solve the SDE.
 * <p>
 * dX = a(X,t) dt + b(X,t) dW
 * <p>
 * a(X,t) = drift, b(X,t) = diffusion
 * <p>
 */
public interface SDEfunction  {
    
    public double[] SDEterms(double x, double t); 
    
} // SDEfunction
