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
 * RealizationsInt.java
 *
 *
 * Created: Wed May 26 13:18:37 1999
 *
 * @author Peter Biechele
 * @version 1.0
 *
 *
 */
 
/**
 *  <p>
 * You can add single realizations (time series) to this class
 * and get the final results (moments) by using the getArray() method.
 * You can set the maximum computed moment and the length of the array
 * for each instance of the class, but only once.
 * <p>
 */
public class RealizationsInt  {
    
    private int maxMoment;
    private int length;
    private int sampleSize;

    /* The cumulated moments of all added realizations. has
       to be of tpe long for the higher moments. */
    private long[][] array; 

    /** if no maximum moment is specified, use 4. */
    public RealizationsInt(int length) {	
	this(length, 4);
    }
    /** Create an instance, set number of elements, etc. */
    public RealizationsInt(int length, int max) {
	this.sampleSize=0;
	this.length=length;
	this.maxMoment=max;
	this.array = new long[this.maxMoment][this.length]; }

    public int getLength() {
	return length; }
    
    public int getMaxMoment() {
	return maxMoment; }

    public int getSampleSize() {
	return sampleSize; }

    public void setLength(int length) {
	if (length!=0) {
	    this.length=length; } }	    
    
    public void setMaxMoment(int max) {
	if (maxMoment!=0) {
	    this.maxMoment=max; } }
    
    /** Add a new realization to the statistics. */
    public void add(int[] realization) {
	sampleSize++;
       // compute the moments
        for (int j=1; j<=maxMoment; j++) {
            for (int i=0; i<length; i++) {
		array[j-1][i]+=Math.pow((long)realization[i],j); } }
    }
    /** Compute the statistical values and return them. 
        Already divided by the sample size = number of realizations. */
    public double[][] getMoments() {
	double[][] dummy = new double[maxMoment][length];
	for (int i=0; i<maxMoment; i++) {
	    for (int j=0; j<length; j++) {
		dummy[i][j] = (double)array[i][j] / (double)sampleSize; } }  
	return dummy; }    
    
} // RealizationsInt
