package simulation;

import java.util.Random;

public final class Distribution {

    /** Generate Cauchy distributed random numbers using
	the quotient of two Gaussian distributed random numbers. */
    public static double nextCauchy(Random rand) {
	double x1=rand.nextGaussian();
 	double x2=rand.nextGaussian();
	return x1/x2;
   }
}
