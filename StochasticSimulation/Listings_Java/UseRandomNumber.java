/** A small test program, to test the Random Number class */
public class UseRandomNumber {
    public static void main(String args[]) {
	final int Seed=123;
	final int N=100000;
	double[] numbers = new double[N];

	// create a new Random Number Generator
	RandomNumber rand = new RandomNumber(Seed);
	
	for(int i=0;i<N;i++) {
	    numbers[i]=rand.nextRand(); // draw random numbers
	}

	// Calculate the average
	double avg=0;
	for (int j=0; j<N; j++) {
	    avg+=numbers[j];
	}
	avg/=(double)N;

	System.out.println(" The Mean is: "+avg);
    }
}
