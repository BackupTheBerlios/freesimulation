package simulation;

import java.applet.Applet;
import ptplot.*;

public final class Plotting {

  /** Plot a graph using errorbars.
      yerr contains the positive, symmetric error in the data points. */
  public static void errorBar(PlotApplet PlotObject, int plotNumber,
                              double[] x, double[] y, double[] yerr,
                              int number) {
    double lowY,highY;    
    boolean connect=false;
    for (int i=0; i<number; i++) {
      // Calculate the error bars
      lowY=y[i]-yerr[i];
      highY=y[i]+yerr[i];
      PlotObject.plot().addPointWithErrorBars(plotNumber,
                                              x[i],y[i],lowY,highY,connect);
      if (connect==false) connect=true;
    }    
  }

  /** Plot a bar graph. 
    Calculate the midpoints for the bars automatically
    from the histogram  */
  public static void barGraph(PlotApplet PlotObject, int plotNumber,
                              double[] points, int[] histogram, int bins,
                              double barWidth) {
    PlotObject.plot().setBars(barWidth,0);
    // Calc midpoints
    double[] midpoints=new double[bins];
    for (int i=1; i<bins-1; i++) {
      midpoints[i]=points[i-1]+(double)(points[i]-points[i-1])/2;
    }
    midpoints[0]=points[0]-(double)(points[1]-points[0])/2;
    midpoints[bins-1]=points[bins-2]+(double)(points[bins-2]-points[bins-3])/2;

    boolean connect=false;
    for (int i=0; i<bins; i++) {
      PlotObject.plot().addPoint(plotNumber,midpoints[i],histogram[i],connect);
    }
  }

  /** plot a graph connecting all points, 
      except the first with the last one.
      Supply 4 routines, allowing integer or double arrays  */
  public static void plot2D(PlotApplet PlotObject, int plotNumber,
                            double[] x, double[] y, int number) {
    boolean connect=false;
    for (int i=0; i<number; i++) {
      PlotObject.plot().addPoint(plotNumber,x[i],y[i],connect);
      if (connect==false) connect=true;
    }    
  }
  public static void plot2D(PlotApplet PlotObject, int plotNumber,
                            int[] x, int[] y, int number) {
    double[] dummyX = new double[number];
    double[] dummyY = new double[number];
    for (int j=0; j<number; j++) {
      dummyX[j]=(double)x[j];
      dummyY[j]=(double)y[j];
    }
    plot2D(PlotObject,plotNumber,dummyX,dummyY,number);
  }
  public static void plot2D(PlotApplet PlotObject, int plotNumber,
                            int[] x, double[] y, int number) {
    double[] dummyX = new double[number];
    for (int j=0; j<number; j++) {
      dummyX[j]=(double)x[j];
    }
    plot2D(PlotObject,plotNumber,dummyX,y,number);
  }
  public static void plot2D(PlotApplet PlotObject, int plotNumber,
                            double[] x, int[] y, int number) {
    double[] dummyY = new double[number];
    for (int j=0; j<number; j++) {
      dummyY[j]=(double)y[j];
    }
    plot2D(PlotObject,plotNumber,x,dummyY,number);
  }
}
