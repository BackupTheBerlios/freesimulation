package simulation;

import java.util.Random;

/** This class contains many methods for generating random numbers
  with different distributions. */
public class Distribution {
  private static int count;    // nextNormal(Random)
  private static double V1,V2; // nextNormal(Random)
  private static int count2;     // nextNormal()
  private static double V21,V22; // nextNormal() 
  // GGL uniform deviates:
  private static final long c=0, a=16807;            // nextUniform()
  private static final long M=(int)Math.pow(2,31)-1; // nextUniform()
  static long seed=1;  // for each Distribution object we have one
  // VERY BAD uniform deviates:
  private static final long cBad=0, aBad=65535;       // nextUniformBad()
  private static final long MBad=(int)Math.pow(2,31)-1; // nextUniformBad()
  static long seedBad=1;  // for each Distribution object we have one
    
  /** Constructor: create our own seed, if necessary.
    initialize all seeds, so that the different generators can be
    used in parallel. */
  public Distribution (int seed) {
    this.seed=seed;
    this.seedBad=seed;
  }

  /** Calculate a integer random number between 0 and N 
    (including 0 and N) -- use Random class */
  public static int nextInteger(Random rand, int N) {
    return (int)Math.round(N*rand.nextDouble());
  }

    /** Generate uniformly distributed random numbers using
	congruential method (the so-called GGL of IBM) */
    public static double nextUniform() {
	seed = (seed*a+c) % M;
	return (double)seed/(double)M;
    }
    /** Generate uniformly distributed random numbers using
	congruential method (with a very bad choice of parameters) */
    public static double nextUniformBad() {
	seedBad = (seedBad*aBad+cBad) % MBad;
	return (double)seedBad/(double)MBad;
    }

    /** Generate normally distributed random numbers using
	the transformation method  and USING nextDouble() */
    public static double nextNormal(Random rand) {
	double U1,U2;
	if (count == 0) {
	    count=1;
	    U1=0;
	    while (U1==0) { U1=rand.nextDouble(); }
	    U2=rand.nextDouble();
	    V1=Math.sqrt(-2*Math.log(U1))*Math.sin(2*Math.PI*U2);
	    V2=Math.sqrt(-2*Math.log(U1))*Math.cos(2*Math.PI*U2);
	    return V1;  }
	else {
	    count=0;
	    return V2; }
    }

    /** Generate normally distributed random numbers using
	the transformation method and USING one of our own
	generators. To select one you can choose an integer as:
	0 = nextUniform(), 99 = nextUniformBad(). */
    public static double nextNormal(int generator) {
	double U1,U2;
	if (count2 == 0) {
	    count2=1;
	    U1=0;
	    while (U1==0) { 
		switch (generator) {
		case 0: U1=nextUniform(); break;
		case 99: U1=nextUniformBad(); break;
		}
	    }
	    U2=0;
	    switch (generator) {
	    case 0: U2=nextUniform(); break;
	    case 99: U2=nextUniformBad(); break;
	    }
	    V21=Math.sqrt(-2*Math.log(U1))*Math.sin(2*Math.PI*U2);
	    V22=Math.sqrt(-2*Math.log(U1))*Math.cos(2*Math.PI*U2);
	    return V21;  }
	else {
	    count2=0;
	    return V22; }
    }
	    
  /** Generate Cauchy distributed random numbers using
    the quotient of two Gaussian distributed random numbers. */
    public static double nextCauchy(Random rand) {
	double x1=0;
	double x2=0;
 	while (x1==0) { x1=rand.nextGaussian(); }
 	while (x2==0) { x2=rand.nextGaussian(); }
	return x1/x2;
   }
  /** Generate Poisson distributed random numbers using
      transformation method. lambda is the parameter.
   */
  public static int nextPoisson(Random rand,int lambda) {
    double A=0;
    int k=0;
    double dummy;
    for(;;) {
      dummy=0;
      while (dummy<=0 || dummy>=1) dummy=rand.nextDouble();
      A*=dummy;
      if (A<Math.exp(-lambda)) return k;
      k++;
    }
  }

  /** Generate exponential distributed random numbers using
      transformation method. w is the parameter.
   */
  public static double nextExponential(Random rand, double w) {
    double dummy=0;
    while (dummy<=0) dummy=rand.nextDouble();
    return -Math.log(dummy)/w;
  }

    /** Generate binomial distributed random numbers using
        direct method (for small n). Parameters: n and p
    */
    public static int nextBinomial(Random rand, int n, double p) {
      int hits=0;
      for (int i=0; i<n; i++) {
	  if (rand.nextDouble() < p) hits++;
      }
      return hits;
  }

//      /** Generate Levy distrubuted randm numbers.
//          Parameter: alpha */
//      public static double nextLevy(Random rand, double alpha){
//        double gamma;
//        double w;
//        double dummy1;
//        double dummy2;
//        gamma= (rand.nextDouble()-0.5)*Math.PI/2.;
//        w = -Math.log(rand.nextDouble());;
//        dummy1= Math.sin(alpha*gamma)/Math.pow(Math.cos(gamma),1./alpha);
//        dummy2= Math.cos((1.-alpha)*gamma)/w;
//        dummy2= Math.pow(dummy2,(1-alpha)/alpha);
//        return dummy1*dummy2;
//    }

    /** Generate symmetric Levy distributed random numbers using
        transformation method with stability index alpha.
        (after Chambers, Mallows, Stuck, 1976)
    */
    public static double nextLevy(Random rand,double alpha) {
        double uniform,expo;
        
        uniform=rand.nextDouble()*Math.PI-Math.PI/2;
        expo=nextExponential(rand,1); // mean 1
        return Math.sin(alpha*uniform)/Math.pow(Math.cos(uniform),1/alpha)*
            Math.pow(Math.cos((1-alpha)*uniform)/expo,(1-alpha)/alpha);
    }

}
