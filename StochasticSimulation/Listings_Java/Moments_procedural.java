import java.util.Random;

public class Moments_procedural {
    public static void main(String[] args) {
	Random rand = new Random();
	// Number of points used
	final int N=50000;
	// what moments are to be calculated
	int moments_start=1;
	int moments_end=20;
	// declare and instantiate arrays
	double[] moments;
	moments=new double[moments_end-moments_start+1];
	double[] numbers;
	numbers=new double[N];
	
	// create N random numbers
	for (int i=0; i<N; i++) {
	    numbers[i]=rand.nextDouble(); }
	
	// initialize array of moments and calculate
	for (int i=0; i<=(moments_end-moments_start); i++) {
	    moments[i]=0;
	    // Call method for calculation
	    moments[i]=calcMoment(numbers,N,i+moments_start); }

	// display the moments
	for (int i=0; i<=(moments_end-moments_start); i++) {
	    System.out.println(" Moment #"+(i+moments_start)+
                               ": "+moments[i]); } }

    /** Method for computing the moments */
    public static double calcMoment(double[] array, int N, int moment) {
	double result=0;
	// Calculate all the moments
	for (int j=0; j<N; j++) {
	    result+=Math.pow(array[j],moment); }
	return result/N;
    }
}
