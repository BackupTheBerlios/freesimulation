import java.util.Random;  // import the Random Number class

public class DataMean_Array {
  public static void main(String[] args) {
    Random rand;       // Declare a random number class object
    int i,N;            
    double mean,sum;
    double RandomNumber[];  // declare an array of 1 dimension
    
    N=10000;               // set the number of random numbers
    rand = new Random();   // create/allocate a random number object

    RandomNumber = new double[N];  // Allocate/Instantiate the whole array

    // Generate a lot of random numbers and store them in an array
    for (i=0; i<N; i++) {      
      RandomNumber[i]=rand.nextDouble(); 
    }                // nextDouble() returns the next random number

    // Calculate the sum of all random numbers
    sum=0;
    for (i=0; i<N; i++) {
      sum+=RandomNumber[i];
    }
    // Calculate the mean of the array
    mean=sum/N;

    System.out.println(" The mean of "+N+" random numbers \n"+
                       " between 0 and 1 is "+mean+" !");
  }
}
