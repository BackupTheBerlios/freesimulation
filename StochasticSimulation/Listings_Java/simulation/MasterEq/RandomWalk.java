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
 * RandomWalk.java
 *
 *
 * Created: Wed May 26 11:26:45 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

/**
 * <p>
 * A class implementing the OneStepMaster interface for solving
 * or simulating a master equation.
 * <p>
 * You can access all parameters by using the standard bean
 * method names. For example to get a parameter "test" you simply
 * call the method getTest() of the object and to set a parameter
 * "sigma" you call setSigma(value).
 * <p>
 * Here a continous time random walk is defined.
 * <p>
 * g(n) = q, r(n) = q
 * <p>
 */
public class RandomWalk implements OneStepMaster {
    
    private double q;

    public RandomWalk() {	
	this(1.0); }

    public RandomWalk(double q) {
	this.q=q; }
    
    public double getQ() {
	return this.q;
    }

    public void setQ(double q) {
	this.q=q;
	return; }

    /** Returns the gain and loss rates for the given occupation
	given as an argument. Here for the special case of the
	continous time random walk (n is not used here). */
    public double[] oneStepRates(int n) {
	double[] dummy = new double[2];
	dummy[0] = this.q;
	dummy[1] = this.q;
	return dummy;	
    }
    
} // RandomWalk
