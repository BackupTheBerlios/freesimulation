import java.util.Random;

public class Moments_all {
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
	
	// initialize array of moments
	for (int i=0; i<=(moments_end-moments_start); i++) {
	    moments[i]=0; }
	
	// Calculate all the moments
	for (int j=0; j<N; j++) {
	    for (int i=0; i<=(moments_end-moments_start); i++) {
		moments[i]+=Math.pow(numbers[j],i+moments_start); } }
	for (int i=0; i<=(moments_end-moments_start); i++) {
	    moments[i]/=N; }
	
	// display the moments
	for (int i=0; i<=(moments_end-moments_start); i++) {
	    System.out.println(" Moment #"+(i+moments_start)+
                               ": "+moments[i]+"   exact: "+
                               1.0/(1+i+moments_start)); } }
}
