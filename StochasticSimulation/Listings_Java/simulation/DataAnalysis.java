package simulation;

import java.util.Random;


public final class DataAnalysis {

  /** Calculate the factorial of an integer. 
    Possible up to factorial of 170, then it returns zero. */
  public static double factorial(int fact) {
    if (fact>170) {
      return 0;
    }
    if (fact>=0) {
      double prod=1;
      for (int i=2; i<=fact; i++) {
        prod*=i;
      }
      return prod;
    }
    else {
      return 0;
    }
  }

  /** Calculate a histogram from data sets.
      points have to be sorted !
      points.length must be one less than bins. */
  public static int[] histogram(double[] data, int number,
                                   int bins, double[] points) {
    int[] histo = new int[bins];
    for (int i=0; i<number; i++) {
      int flag=0;
      for (int j=0; j<bins-1; j++) {
        if (data[i] < points[j]) {
          histo[j]++;
          flag=1;
          break;
        }
      }
      if (flag==0) histo[bins-1]++;
    }
    return histo;
  }

  public static int[] histogram(int[] data, int number,
                                   int bins, double[] points) {
      double[] dummydata = new double[number];
      for (int j=0; j<number; j++) {
	  dummydata[j]=(double)data[j];
      }
      return histogram(dummydata,number,bins,points);
  }

}
