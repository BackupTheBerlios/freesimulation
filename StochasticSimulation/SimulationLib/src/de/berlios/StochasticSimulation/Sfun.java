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
package de.berlios.StochasticSimulation;

import de.berlios.StochasticSimulation.*;


/** 
 * Hyperbolic trigonometric functions like sinh, cosh, etc.
 *
 * @author Peter Biechele
 * @version $Revision: 1.1 $ $Date: 2004/03/20 17:50:27 $
 */
public final class Sfun {

    /** Calculate the sinh of a given value. We use the formula:
	sinh(x) = ( e^x - e^-x )/2 
        @param value the x value, at this point we calculate it
    */
    public static double sinh ( double value ) {
        return (Math.exp(value) - Math.exp(-value))/2.0;
    }

    /** Calculate the csch of a given value. We use the formula:
	csch(x) = 1 / sinh(x)
        @param value the x value, at this point we calculate it
    */
    public static double csch ( double value ) {
        return 1.0 / sinh(value);
    }

    /** Calculate the cosh of a given value. We use the formula:
	cosh(x) = ( e^x + e^-x )/2 
        @param value the x value, at this point we calculate it
    */
    public static double cosh ( double value ) {
        return (Math.exp(value) + Math.exp(-value))/2.0;
    }

    /** Calculate the sech of a given value. We use the formula:
	sech(x) = 1 / cosh(x)
        @param value the x value, at this point we calculate it
    */
    public static double sech ( double value ) {
        return 1.0 / cosh(value);
    }

    /** Calculate the tanh of a given value. We use the formula:
	tanh(x) = sinh(x)/cosh(x)
        @param value the x value, at this point we calculate it
    */
    public static double tanh ( double value ) {
        return sinh(value)/cosh(value);
    }

    /** Calculate the coth of a given value. We use the formula:
	coth(x) = cosh(x)/sinh(x)
        @param value the x value, at this point we calculate it
    */
    public static double coth ( double value ) {
        return cosh(value)/sinh(value);
    }

    // ----------------------------------------------------------
}
