
package de.berlios.StochasticSimulation;


import junit.framework.TestCase;
import de.berlios.StochasticSimulation.*;


public class HistogramTest extends TestCase
{
    public HistogramTest (String name) {
	super(name);
    }


    private int N = Histogram.getDefaultBins();
    private double[] array=new double[N];

    public void setUp() {
        // Create data
        for (int i=0; i<N; i++) {
            array[i]=i; }
        // mean is: 9,5
	// stdDev sigma is: 35 
    }



    public void testRandomHistogramJustData() {

        // call histogram
        Histogram histo = new Histogram(array);

	assertTrue(histo.getNumDataPoints()== N);
	assertTrue(histo.getBins()== Histogram.getDefaultBins());

	// calc
	histo.estimate();

	assertTrue(histo.getNumDataPoints()== N);
	assertTrue(histo.getBins()== Histogram.getDefaultBins());

	int[] resultBins = new int[N];
	resultBins = histo.getHistogram();

	for (int i=0; i<N; i++) {
	    if ( (i == 8) || (i == 11)) {
		assertTrue(resultBins[i] == 3);	
	    } else {
		if ( (i == 9) || (i == 10)) {
		    assertTrue(resultBins[i] == 7);	
		} else {
		    assertTrue(resultBins[i] == 0);			    
		}
	    }
	}
    }



    public void tearDown() {
    }

}
