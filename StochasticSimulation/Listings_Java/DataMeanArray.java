public class DataMeanArray {
  public static void main(String[] args) {
    int i,N;            
    double mean,sum;
    double RandomNumber[];  // declare an array of 1 dimension
    
    N=10000;               // set the number of random numbers
    RandomNumber = new double[N];  // Instantiate the whole array

    // Generate a lot of random numbers and store them in an array
    for (i=0; i<N; i++) {      
      RandomNumber[i]=Math.random(); 
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
