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
 * PoissonProcess.java
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
 * Here a Poisson process is defined.
 * <p>
 * g(n) = q, r(n) = 0
 * <p>
 */
public class PoissonProcess implements OneStepMaster {
    
    /** Decay constant */
    private double q;

    public PoissonProcess() {
	this(10.0); }

    public PoissonProcess(double param) {
	this.q=param; }
    
    public double getQ() {
	return this.q; }

    public void setQ(double param) {
	this.q=param;
	return; }

    /** Returns the gain and loss rates for the given occupation
	given as an argument. <p>
    The zeroth return argument: gain rate <p>
    The first return argument : loss rate <p>
    */
    public double[] oneStepRates(int n) {
	double[] dummy = new double[2];
	dummy[0] = this.q;
	dummy[1] = 0;
	return dummy;	
    }
    
} // RandomWalk
