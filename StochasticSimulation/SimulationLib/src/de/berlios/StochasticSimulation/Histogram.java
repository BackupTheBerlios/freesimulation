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
 * Histogram.java
 *
 *
 * Created: Thu Jun 17 13:40:05 1999
 *
 * @author Peter Biechele
 * @version
 */

import java.awt.*;
import de.berlios.StochasticSimulation.Statistics;
import ptolemy.plot.*;

/** This class allows to create a histogram from a 1D double data array.

    You first have to instantiate a Histogram object, before you can call 
    the estimate() method to compute the histogram itself. </p>

    The constructor guesses the intervals for the bins on the grounds of
    the data used for estimating. You can supply your own bins
    if you use the other constructor. </p>

    For checking and displaying the results, you can use the
    print() or the plot() method. 
*/
public class Histogram  {
    
    final private static int defaultNumOfBins = 20;

    // input values
    private int bins;
    private int numDataPoints;
    private double[] data;

    // the points to be used
    public double[] points;

    // output/compute values
    public int[] histogram; 



    /** The boundaries of the bins are computed automatically.
        Use all data values in the array for the histogram. */
    public Histogram (double[] data) {
        this(data,data.length); }

    /** Supply the data and the boundaries of the bins. */
    public Histogram (double[] data, double[] points) {
        this.data=data;
        this.numDataPoints=data.length;
        this.bins=points.length-1;
        this.points=new double[this.bins+1];
        System.arraycopy(points,0,this.points,0,bins+1);
    }

    /** Supply the data, the boundaries of the bins and the number
        of data points used for the histogram. */
    public Histogram (double[] data, double[] points, int numDataPoints) {
        this(data,points);
        this.numDataPoints=numDataPoints;
    }


    /** Construct the boundary points for the histogram. 
        Use the mean and the standard deviation. 
        The resulting interval for the histogram is: [-2sigma,2sigma] 
        around the mean.
        The default is to use 20 intervals.
        To choose your own number of intervals you have set the number
        of bins BEFORE calling the constructor using the setBins()
        method.
    */
    public Histogram (double[] data, int number) {
        double mean, sigma;
        double binWidth;
        
        // default for number of bins
        if (bins==0) bins=defaultNumOfBins;

        this.numDataPoints=number;
        // DO NOT COPY THE ARRAY, JUST REFERENCE IT
        this.data=data;

        // get mean and standard deviation
        mean=Statistics.average(this.data);
        sigma=Statistics.standardDeviation(this.data);
        // compute bin width and shift the mean to start of boundaries
        binWidth=4*sigma/bins;
        mean -= (2*sigma);
        /* test
           System.out.println("Using a bin width of "+binWidth+
           " using "+bins+" bins "+
           "in the interval ["+mean+","+(mean+4*sigma)+"]");
        */

        // construct intervals bins around the mean in 4 sigma wide interval
        points=new double[bins+1];
        for (int i=0; i<=bins; i++) {
            points[i]=mean+i*binWidth; }
    }





    public int getNumDataPoints() {
        return this.numDataPoints; }
    public void setNumDataPoints(int param) {
        this.numDataPoints=param; }

    public int getBins() {
        return this.bins; }
    public static int getDefaultBins() {
        return defaultNumOfBins; }
    public void setBins(int param) {
        if (bins==0) { 
            this.bins=param; }
        else {
            System.out.println(" ERROR: You can not set he number of bins,"+
                               " after you have instantiated the object."); }
    }

    public int[] getHistogram() {
        return histogram; }




    /** Call the actual histogram calculation in the DataAnalysis
        package.
    */
    public void estimate() {
        this.histogram = new int[this.bins];
        this.histogram = Histogram.histogram(this.data,this.points,
                                             this.numDataPoints,this.bins);
    }    

    /** Plot the result of the histogram estimate in a table on
        the screen. Use the formatting of Fmt class. */
    public void print() {
        for (int i=0; i<bins; i++) {
            System.out.println(" [ "+Fmt.fmt(points[i],6,3)+","+
                               Fmt.fmt(points[i+1],6,3)+"] : "+
                               Fmt.fmt(histogram[i],8));
        }
    }


    /** Plot the histogram in a window using bargraph. (and ptplot)</p>
        The width of the bars is 80 percent of the mean bin width. */
    public void plot() {
        double dummyDouble, meanBinWidth;
        Frame frame;
        Plot plot;

        // create a frame and put the plot inside
        frame = new ClosingFrame("Histogram of data");
        frame.setLayout(new BorderLayout());
        plot = new Plot();
        frame.add(plot);
        frame.pack();
        frame.setVisible(true);

        /* some plot settings:
           the width of the bars = 80 per cent of the mean bin width
           button for zooimg is displayed
        */
        meanBinWidth=(points[bins]-points[0])/bins;
        plot.setBars(0.8*meanBinWidth,0);
        plot.setButtons(true);

        // plot the bars
        for (int i=0; i<bins; i++) {
            dummyDouble = (points[i]+points[i+1])/2;
            plot.addPoint(1,dummyDouble,histogram[i],false); 
        }        
    }


    /** Calculate a histogram from data sets. </p>
        The array points has to be sorted (but not the data) ! </p>
        points.length must be one more than number of bins. </p> 
        <ol>
        <li> data: 1D data points 
        <li> points: an array containing the boundaries of the bins
        <li> number: the number of data points to be used for histogram
        <li> bins: the number of bins to be used (=points.length-1)
        </ol>
        All points which do not fall into one of the bins are neglected!!! 
        If you want to catch all points, you have to supply a large bin on the
        left and a large bin on the right of the desired x interval.
    */
    public static int[] histogram(double[] data, double[] points, 
                                  int number, int bins) {
        int[] histo = new int[bins];
        double[] pointsDummy = new double[bins+2];
        int j;

        /* add a boundary point on the right which is very large
           to end the while loop below. */        
        System.arraycopy(points,0,pointsDummy,0,bins+1);
        pointsDummy[bins+1]=Double.MAX_VALUE;

        /* test all points in which bin they fit. */
        for (int i=0; i<number; i++) {
            /* the loop to get the bin number for the point. */
            j=0;
            while (data[i] > pointsDummy[j]) j++;
            /* do not count the points of the last (artificial) bin. */
            if (j-1<bins) histo[j-1]++;   
            /* test the program. */
            /* System.out.println(" po: "+data[i]+"  bin: "+
               (j-1)+"  bound: "+pointsDummy[j-1]); 
            */
        }
        return histo;
    }


    /** This is a convenience routine for an integer array. It calls the
        double method for histograms. */
    public static int[] histogram(int[] data, double[] points, 
                                  int number, int bins) {
        double[] dummydata = new double[number];
        for (int j=0; j<number; j++) {
            dummydata[j]=(double)data[j];
        }
        return Histogram.histogram(dummydata,points,number,bins);
    }
    /** This is a convenience routine for an integer array. It calls the
        double method for histograms. */
    public static int[] histogram(int[] data, double[] points, int number) {
        return Histogram.histogram(data,points,number,points.length-1);
    }


    /** A test main program. */
    public static void main(String[] args) {
        int N=1000;
        double[] array=new double[N];

        // Create random data
        for (int i=0; i<N; i++) {
            array[i]=Math.random(); }
        
        // call histogram
        Histogram histo = new Histogram(array);
        histo.estimate();
        histo.plot();
        histo.print();
    }

} // Histogram



