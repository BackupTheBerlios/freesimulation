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
 * Payroll.java
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
 * Here a payroll process is defined.
 * <p>
 * g(n) = h, r(n) = l n
 * <p>
 */
public class Payroll implements OneStepMaster {
    
    /** constants */
    private double h, l;

    public Payroll() {
	this(0.2, 0.005); }

    public Payroll(double param1, double param2) {
	this.h=param1; 
    	this.l=param2; }

    public double getH() {
	return this.h; }

    public double getL() {
	return this.l; }

    public void setH(double param) {
	this.h=param;
	return; }

    public void setL(double param) {
	this.l=param;
	return; }

    /** Returns the gain and loss rates for the given occupation
	given as an argument. <p>
    The zeroth return argument: gain rate <p>
    The first return argument : loss rate <p>
    */
    public double[] oneStepRates(int n) {
	double[] dummy = new double[2];
	dummy[0] = h;
	dummy[1] = l*n;
	return dummy;	
    }
    
} // Payroll
