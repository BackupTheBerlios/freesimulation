package simulation;

import simulation.*;


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

}
