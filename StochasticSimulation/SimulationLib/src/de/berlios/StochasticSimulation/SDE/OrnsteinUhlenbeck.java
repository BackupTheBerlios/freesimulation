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
 * OrnsteinUhlenbeck.java
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
 * Here an Ornstein-Uhlenbeck Process is defined.
 * <p>
 * dX = -drift X dt + diffusion dW
 * <p>
 */
public class OrnsteinUhlenbeck implements SDEfunction {
    
    private double drift;
    private double diffusion;

    public OrnsteinUhlenbeck() { 
        this(1.0, 1.0); }
    public OrnsteinUhlenbeck(double param1, double param2) {        
        drift=param1;
        diffusion=param2; }

    public double getDrift() {
        return this.drift; }
    public double getDiffusion() {
        return this.diffusion; }

    public void setDrift(double param) {
        this.drift=param; }
    public void setDiffusion(double param) {
        this.diffusion=param; }

    public double[] SDEterms(double x, double t) {
        double[] retVal = new double[2];

        retVal[0] = -drift*x;
        retVal[1] = Math.sqrt(diffusion);
        return retVal;
    }
    
} // OrnsteinUhlenbeck
