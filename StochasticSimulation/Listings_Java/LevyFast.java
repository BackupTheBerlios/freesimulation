
/**
 * LevyFast.java
 *
 *
 * Created: Tue Dec  1 17:24:35 1998
 *
 * @author Peter Biechele
 * @version
 */

import VisualNumerics.math.*;
import java.util.*;
import JSci.maths.*;
import ptplot.*;
import simulation.*;
import java.applet.*;

public class LevyFast extends PlotApplet {
    double alpha = 1.0;
    int alphaint = 1;
    final int n = 1;
    final int number = 5000;

    double[] K = {0.795112, 1.0, 1.20519, 1.59922, 4.80663};
    double[] C = {2.483, 1.0, 2.941, 2.737, 1.3925};

    Random rand = new Random();

    public void init() {
        super.newPlot();
        super.init();
        plot().setTitle("The (fast) Levy Distribution");
	
        // generate the Levy random numbers
        double[] random = new double[number];
        for (int i=0; i<number; i++) {
            random[i]=nextLevyFast(rand);
        }
        double[] random2 = new double[number];
        for (int i=0; i<number; i++) {
            random2[i]=Distribution.nextLevy(rand,alpha);
        }

        // Calc Histogram
        int bins=400;
        double[] points = new double[bins+1];
        for (int i=-bins/2; i<bins/2; i++) {
            points[i+bins/2]=(double)i/10;
        }
        int[] histo2=DataAnalysis.histogram(random2,number,bins+2,points);
        // plot the histogram
        // Plotting.barGraph(this,1,points,histo2,bins+2,0.07);
        int[] histo=DataAnalysis.histogram(random,number,bins+2,points);
        // plot the histogram
        Plotting.barGraph(this,0,points,histo,bins+2,0.07);
    }
    
//     public LevyFast() {
// 	double levy;

// 	for (int i=0; i<number; i++) {
// 	    levy = nextLevyFast(rand);
// 	    System.out.println(levy);	    
// 	}
//     }
    
//     public static void main(String[] args) {
// 	new LevyFast();
//     }
    
    double nextLevyFast(Random rand) {
	double z,x,y,v,w;
	double sigmax=0;

	z=0;
	for (int i=0; i<n; i++) {
	    x=rand.nextGaussian();
	    y=rand.nextGaussian();
	    sigmax=Math.pow((SpecialMath.gamma(1+alpha)*Math.sin(Math.PI*alpha/2))/
			    (SpecialMath.gamma((1+alpha)/2)*alpha*Math.pow(2,0.5*(alpha-1)))
			    ,1/alpha);
	    x=x*sigmax;
	    v=x/Math.pow(Math.abs(y),1/alpha);
	    w=v*((K[alphaint]-1)*Math.exp(-v/C[alphaint])+1);
	    z+=w;
	}
	z/=Math.pow(n,1/alpha);
	// System.out.println(" sigma x for alpha = "+alpha+" is: "+sigmax);
	return z;
    }

} // LevyFast
