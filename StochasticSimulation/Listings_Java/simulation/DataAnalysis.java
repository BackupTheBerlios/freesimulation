package simulation;

import java.util.Random;


public final class DataAnalysis {

  /** Calculate a integer random number between 0 and N 
      (including 0 and N) -- use Random class */
  public static int nextInteger(Random rand, int N) {
    return (int)Math.round(N*rand.nextDouble());
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

}
