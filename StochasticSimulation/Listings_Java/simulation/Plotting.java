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
package simulation;

import java.awt.*;
import java.applet.Applet;
import ptolemy.plot.*;
import simulation.*;

public final class Plotting 
    implements FunctionDouble {

    //////////////////////////////////////////////////////////////////////

    /** Use default interval [0,1] and 1000 points for plotting. */
    public static void plotFunction(FunctionDouble fctn) {
        Plotting.plotFunction(fctn,1000,0,1); }
    /** Use default interval [0,1] for plotting using numPoints. */
    public static void plotFunction(FunctionDouble fctn, int numPoints) {
        Plotting.plotFunction(fctn,numPoints,0,1); }
    /**  Create a seperate frame,
         which is closeable, but does not finish the program. */
    public static void plotFunction(FunctionDouble fctn, int numPoints, 
                                    double xmin, double xmax) {
        plotFunction(Plotting.openPlotFrame(),fctn,numPoints,xmin,xmax);
    }
    /** Plot a 1D function f(x) in a plot. Use a supplied plot
        object. The function has tobe specified as an implementation
        of the <tt>FunctionDouble</tt> interface of the simulation package. */
    public static void plotFunction(Plot plot, FunctionDouble fctn,
                                    int numPoints, double xmin, double xmax) {
        double x, deltaX;
        boolean connect;

        /** plot the function. */
        deltaX=(xmax-xmin)/numPoints;
        connect=false;
        for (int i=0; i<numPoints; i++) {
            x=xmin+i*deltaX;
            plot.addPoint(0,x,fctn.function(x),connect);
            if (connect==false) connect=true;
        }
        plot.repaint();        
    }

    //////////////////////////////////////////////////////////////////////

    /** Plot a graph using errorbars. </p> 
        The plot will be in a new Frame.
     */
    public static void errorBarPlot(double[] x, double[] y, double[] yerr) {
        Plotting.errorBarPlot(Plotting.openPlotFrame(),0,x,y,yerr); }

    /** Plot a graph using errorbars. </p> 
        The plot will be in a new Frame.
     */
    public static void errorBarPlot(double[] x, double[] y, 
                                    double[] yerrUp, double[] yerrDown) {
        Plotting.errorBarPlot(Plotting.openPlotFrame(),0,x,y,yerrUp,yerrDown); }
    
    /** Plot a graph using errorbars. </p>
        yerr contains the positive, symmetric error (=standard deviation) 
        in the data points. </p>
        The x coordinates are just the index of the y array.
    */
    public static void errorBarPlot(Plot plot, int plotNumber,
                                    double[] y, double[] yerr) {
        int N=y.length;
        double[] x = new double[N];
        for (int i=0; i<N; i++) {
            x[i]=i; }
        Plotting.errorBarPlot(plot,plotNumber,x,y,yerr,yerr); }

    /** Plot a graph using errorbars. </p>
        yerr contains the positive, symmetric error (=standard deviation) 
        in the data points. </p>
        If the x and y arrays contain a different number of points, 
        the smaller array defines the number of points. */
    public static void errorBarPlot(Plot plot, int plotNumber,
                                    double[] x, double[] y, double[] yerr) {
        Plotting.errorBarPlot(plot,plotNumber,x,y,yerr,yerr); }

    /** Plot a graph using errorbars. </p>
        yerrUp and yerrDown contain the positive/negative error
        in the data points. </p>
        If the x and y arrays contain a different number of points, 
        the smaller array defines the number of points. */
    public static void errorBarPlot(Plot plot, int plotNumber,
                                    double[] x, double[] y, 
                                    double[] yerrUp, double[] yerrDown) {
        boolean connect=false;
        double lowY, highY;
        int size=Math.min(x.length,y.length);

        for (int i=0; i<size; i++) {
            // compute errors
            lowY=y[i]-Math.abs(yerrDown[i]);
            highY=y[i]+Math.abs(yerrUp[i]);
            // plot            
            plot.addPointWithErrorBars(plotNumber,x[i],y[i],
                                       lowY,highY,connect);
            if (connect==false) connect=true;
        }    
        plot.repaint();
    }

    //////////////////////////////////////////////////////////////////////

    /** Plot a bar graph. </p> 
        Use a new window/frame to plot the bar plot.
     */
    public static void barPlot(double[] points, int[] height, double barWidth) {
        Plotting.barPlot(Plotting.openPlotFrame(),0,points,height,barWidth); }

    /** Plot a bar graph. </p> 
        You have to supply the boundaries of the bars,
        the height of the bars and the width of the bars. </p>
        The program computes the midpoints for the bars automatically
        from the data (boundaries) given. The number of bins is just
        the number of bioundary points minus 1. */
    public static void barPlot(Plot plot, int plotNumber,
                               double[] points, int[] height, double barWidth) {
        double[] midpoints;
        boolean connect;
        int bins=points.length-1;

        plot.setBars(barWidth,0);
        // Compute the midpoints
        midpoints=new double[bins];
        for (int i=1; i<bins-1; i++) {
            midpoints[i]=points[i-1]+(double)(points[i]-points[i-1])/2;
        }
        midpoints[0]=points[0]-(double)(points[1]-points[0])/2;
        midpoints[bins-1]=points[bins-2]+(double)(points[bins-2]-points[bins-3])/2;

        connect=false;
        for (int i=0; i<bins; i++) {
            plot.addPoint(plotNumber,midpoints[i],height[i],connect);
        }
    }

    //////////////////////////////////////////////////////////////////////

    /** Plot a graph of a double arrays containing the y coordinates.
        The x data is just taken as the index of the array elements. 
        Plot number is taken to be zero. */
    public static void plot2D(Plot plot, double[] y) {
        Plotting.plot2D(plot,0,y); }
    /** Plot a graph of a double arrays containing the y coordinates.
        The x data is just taken as the index of the array elements. */
    public static void plot2D(Plot plot, int plotNumber, double[] y) {
        double[] x = new double[y.length];
        for (int i=0; i<y.length; i++) {
            x[i]=i; }
        Plotting.plot2D(plot,plotNumber,x,y); }
    /** Plot a graph of 2 double arrays containing x and y coordinates.
        Plot number is taken to be zero. */
    public static void plot2D(Plot plot, double[] x, double[] y) {
        Plotting.plot2D(plot,0,x,y); }
    /** Plot a graph of 2 double arrays containing x and y coordinates
        of points. The points are connected by a line, so they
        should be sorted in ascending or descending order. If x and
        y arrays have different sized, the minimum size is taken. 
        The plot number specifies the color of the plot, it can also be used
        to plot more than one dataset into one plot. */
    public static void plot2D(Plot plot, int plotNumber, double[] x, double[] y) {
        boolean connect=false;
        int size;

        size=Math.min(x.length,y.length);
        for (int i=0; i<size; i++) {
            plot.addPoint(plotNumber,x[i],y[i],connect);
            if (connect==false) connect=true; }
        plot.repaint();
    }

    /** Plot one double array containing the y coordinates
        in a new Frame containing the plot. 
        The x data is just taken as the index of the array elements. */
    public static void plot2D(double[] y) {
        Plotting.plot2D(openPlotFrame(),0,y); }
    /** Plot two double arrays containing x and y coordinates
        in a new Frame containing the plot. */
    public static void plot2D(double[] x, double[] y) {
        Plotting.plot2D(openPlotFrame(),0,x,y);
    }

    //////////////////////////////////////////////////////////////////////

    private static Plot openPlotFrame() {
        Frame frame = new CloseableFrame();
        Plot plot = new Plot();

        plot.setButtons(true);
        frame.add(plot);
        frame.pack();
        frame.setVisible(true);

        return plot;
    } 

    //////////////////////////////////////////////////////////////////////


    /** A test main function to show how the function plotter works. */
    public static void main (String[] args) {
        // get a closing frame to end execution
        Frame frame = new ClosingFrame("Close me");
        frame.add(new Label("Close me to stop the program!"));
        frame.pack();
        frame.setVisible(true);

        // test function plotting methods
        Plotting fct = new Plotting();
        Plotting.plotFunction(fct,1000,-50,50);

        // test the xy plotting methods
        int N=100;
        double[] a1=new double[N];
        double[] a2=new double[N];
        for (int i=0; i<N; i++) {
            a1[i]=5*(double)i/N;
            a2[i]=Math.sin(a1[i])*Math.exp(-a1[i]); }
        Plotting.plot2D(a1,a2);

        Plotting.plot2D(a2);
    }

    //////////////////////////////////////////////////////////////////////

    /** a test function */
    public double function(double x) {
        if (x==0) {
            return 1; }
        else {
            return Math.sin(x)/x; }
    }
    
    //////////////////////////////////////////////////////////////////////

}
