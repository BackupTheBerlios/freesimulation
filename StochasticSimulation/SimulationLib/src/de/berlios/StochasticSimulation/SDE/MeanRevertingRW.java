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
 * MeanRevertingRW.java
 *
 *
 * Created: Tue Jun  1 10:50:30 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

/**
 * <p>
 * A class implementing the SDEfunction interface for solving
 * a stochastic differential equation (SDE).
 * <p>
 * You can access all parameters by using the standard bean
 * method names. For example to get a parameter "test" you simply
 * call the method getTest() of the object and to set a parameter
 * "sigma" you call setSigma(value).
 * <p>
 * Here a mean reverting random walk is defined.
 * <p>
 * dX = ( nu - mu X ) dt + sigma dW
 * <p>
 */
public class MeanRevertingRW implements SDEfunction {
    
    private double mu;
    private double sigma;
    private double nu;

    public MeanRevertingRW() { 
        this(1.0, 1.0, 1.0); }
    public MeanRevertingRW(double param1, double param2, double param3) {     
        mu=param1;
        sigma=param2; 
        nu=param3;  }
    
    public double getMu() {
        return this.mu; }
    public double getSigma() {
        return this.sigma; }
    public double getNu() {
        return this.nu; }

    public void setMu(double param) {
        this.mu=param; }
    public void setSigma(double param) {
        this.sigma=param; }
    public void setNu(double param) {
        this.nu=param; }

    public double[] SDEterms(double x, double t) {
        double[] retVal = new double[2];

        retVal[0] = (nu-mu*x);
        retVal[1] = sigma;
        return retVal;
    }
    
} // MeanRevertingRW
