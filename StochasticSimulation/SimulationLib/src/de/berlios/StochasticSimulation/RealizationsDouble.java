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

/**
 * RealizationsDouble.java
 *
 *
 * Created: Wed May 26 13:18:37 1999
 *
 * @author Peter Biechele
 * @version 1.0
 *
 *
 */

import java.awt.*;
import ptolemy.plot.*; 

/**
 * You can add single realizations (time series) to this class
 * and get the final results (moments) by using the getArray() method.
 * You can set the maximum computed moment and the length of the array
 * for each instance of the class, but only once.
 */
public class RealizationsDouble  {
    
    private int maxMoment;
    private int length;
    private int sampleSize;

    /* The acumulated moments of all added realizations. 
       The format: array[Moment][time] */
    private double[][] array; 

    /** if no maximum moment is specified, use 4. */
    public RealizationsDouble(int length) {	
	this(length, 4);
    }
    /** Create an instance, set number of elements, etc. */
    public RealizationsDouble(int length, int max) {
	this.sampleSize=0;
	this.length=length;
	this.maxMoment=max;
	this.array = new double[this.maxMoment][this.length]; }

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
    public void add(double[] realization) {
	sampleSize++;
       // compute the moments
        for (int j=1; j<=maxMoment; j++) {
            for (int i=0; i<length; i++) {
		array[j-1][i]+=Math.pow(realization[i],j); } }
    }

    /** Compute the statistical values and return them. 
        Already divided by the sample size = number of realizations. */
    public double[][] getMoments() {
	double[][] dummy = new double[maxMoment][length];
	for (int i=0; i<maxMoment; i++) {
	    for (int j=0; j<length; j++) {
		dummy[i][j] = array[i][j] / sampleSize; } }  
	return dummy; }    

    /** Plot one moment (1=mean, 2=second moment, etc.) 
        and the corresponding error of the realizations in a 
        seperate window.The moment of course has to be at most half of the 
        maximum moment saved in the object (see computeError). */
    public void plotMoment(int moment) {
        if (2*moment-1>maxMoment || moment<1) return;
        moment--;

        Frame f = new CloseableFrame();
        Plot plot = new Plot();
        plot.setButtons(true);
        plot.setTitle("Moment "+(moment+1)+" of the Realizations");
        plot.setXLabel("# of time steps");
        plot.setYLabel("Moment "+(moment+1));
        f.setLayout(new BorderLayout());
        f.add(plot,"Center");
        f.pack();
        f.show();

        Plotting.errorBarPlot(plot,0,
		     array[moment],computeError(moment+1));
    }

    /** Computes the error of a moment (only possible if 2*moment is
        computed by the object) and returns the error. 
        e.g. for the second moment error, you need the 4th moment. 
        The mean is moment = 1, etc. */
    public double[] computeError(int moment) {
        double[] error = new double[length];
        double factor;

        if (2*moment-1>maxMoment || moment<1) return error;
        moment--;
        
        factor = 1/Math.sqrt(sampleSize);
        for (int i=0; i<length; i++) {
            error[i]=factor*( array[2*moment][i]/sampleSize
                - Math.pow(array[moment][i]/sampleSize,2) ); }
        return error;
    }
    
} // RealizationsDouble


