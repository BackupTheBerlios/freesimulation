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
package simulation.SDE;

/**
 * NoiseInducedTransition.java
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
 * Here an example of a noise induced transition. The process is 
 * defined by a potential U(x) = x(1-x).
 * <p>
 * dX = X(0.5-X) dt + (epsilon x(1-x))^2 dW
 * <p>
 */
public class NoiseInducedTransition implements SDEfunction {
    
    private double epsilon;

    public NoiseInducedTransition() { 
        this(1.0); }
    public NoiseInducedTransition(double param1) {        
        epsilon=param1; }

    public double getEpsilon() {
        return this.epsilon; }

    public void setEpsilon(double param) {
        this.epsilon=param; }

    public double[] SDEterms(double x, double t) {
        double[] retVal = new double[2];
        double dummy=x*(1-x);

        retVal[0] = dummy;
        retVal[1] = Math.pow(epsilon*dummy,2);
        return retVal;
    }
    
} // NoiseInducedTransition
