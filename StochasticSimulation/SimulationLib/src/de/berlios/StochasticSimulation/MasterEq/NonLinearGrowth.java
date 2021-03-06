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
 * NonLinearGrowth.java
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
 * Here a nonlinear growth process is defined.
 * <p>
 * g(n) = beta n, r(n) = alpha n + gamma n (n-1)
 * <p>
 */
public class NonLinearGrowth implements OneStepMaster {
    
    private double alpha, beta, gamma;

    public NonLinearGrowth() {	
	this(0.1, 1.1, 0.01); }

    public NonLinearGrowth(double param1, double param2, double param3) {
        this.beta=param2;
        this.gamma=param3;	
        this.alpha=param1; }
    
    public double getAlpha() {
	return this.alpha; }
    public double getBeta() {
	return this.beta; }
    public double getGammaa() {
	return this.gamma; }

    public void setAlpha(double param) {
	this.alpha=param;
	return; }
    public void setBeta(double param) {
	this.beta=param;
	return; }
    public void setGamma(double param) {
	this.gamma=param;
	return; }

    /** Returns the gain and loss rates for the given occupation
        given as an argument. <p>
        The zeroth return argument: gain rate <p>
        The first return argument : loss rate <p>
    */
    public double[] oneStepRates(int n) {
	double[] dummy = new double[2];
	dummy[0] = beta*n;
	dummy[1] = alpha*n+gamma*n*(n-1);
	return dummy;	
    }
    
}
