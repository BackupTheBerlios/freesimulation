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
 * Basic data analysis routines. 
 *
 * @author Peter Biechele
 * @version $Revision: 1.1 $ $Date: 2004/03/20 17:50:27 $
 */
public final class Statistics {

    /** Calculate the mean of given double values.
	@param array double values = array
    */
    public static double average ( double[] array ) {
	return average(array, 0, array.length);	
    }

    /** Calculate the mean of given double values
	using just part of the values - includinf start
	and end array values.
	@param array double values = array
	@param start first element used for calc
	@param end   last element used for calc
    */
    public static double average ( double[] array, int start, int end ) {
	return sum(array, start, end)/(end-start+1);
    }

    // ----------------------------------------------------------

    /** Standard Deviation of the individual value.
	Not of the mean value. To that end you have divide
	by the number of values.
     */
    public static double standardDeviation( double[] array ) {
	double std = 0;
	double mean = 0;
	double sumCenteredSquares, sumCenteredValues;
	double length = array.length;

	// Calc mean estimator
	mean = average(array);

	// Use corrected two pass algorithm to compute variance
	sumCenteredValues = 0;
	sumCenteredSquares = 0;
	for (int i=0; i<length; i++) {
	    sumCenteredValues += (array[i] - mean);
	    sumCenteredSquares += ((array[i] - mean)*(array[i] - mean));
	}

	std = (sumCenteredSquares - 
	       (sumCenteredValues * sumCenteredValues)/length )/(length - 1);

	return std;
    }

    // ----------------------------------------------------------


    /** Calculate the sum of an double array using
	given start and end array elements.
	@param array  double values = array
	@param start first element used for calc
	@param end   last element used for calc
    */
    public static double sum ( double[] array, int start, int end) {
	double sum = 0;

	for (int i=start; i<end; i++) {
	    sum += array[i];
	}
	return sum;
    }
    /** Calculate the sum of an double array.
	@param array  double values = array
	@param length length of array to be used for calc
    */
    public static double sum ( double[] array, int length) {
	return sum(array, 0, length);
    }

    /** Calculate the sum of an double array. 
	The length is the length of the given array.
	@param array  double values = array
    */
    public static double sum ( double[] array) {
	return sum(array, array.length);
    }

    // ----------------------------------------------------------

    /** Calculate the maximum of an double array. 
	@param array  double values = array
    */
    public static double maximum ( double[] array ) {
	double maximum;
	int length = array.length;

	maximum = array[0];
	if (length > 1) {
	    for (int i=1; i<length; i++) {
		if (array[i]>maximum) {
		    maximum = array[i];
		}
	    }
	}

	return maximum;
    }

}
