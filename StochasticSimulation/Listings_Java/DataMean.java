/** compute the mean of N random numbers distributed uniformly */

public class DataMean {
  public static void main(String[] args) {
    int i,N;            
    double mean;
    N=10000;               // set the number of random numbers

    mean=0;
    for (i=1; i<N; i++) {      // Calculate the mean of the numbers
        mean+=Math.random(); // draw a random number of type double 
    }                // nextDouble() returns the next random number
    mean/=N;

    System.out.println(" The mean of "+N+" random numbers \n"+
                       " between 0 and 1 is "+mean+" !");
  }
}
