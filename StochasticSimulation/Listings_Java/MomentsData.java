/**  A class for data sets in 1D 
     You can call the computation of the moments 
     with this class in 2 different ways. */
public class MomentsData {
    private int N;
    private double[] data;

    /** Instantiate an object holding the data and the size 
        of the data set */ 
    public MomentsData(double[] array) {
	this.N = array.length;        
        this.data = new double[this.N]; 
        System.arraycopy(array,0,this.data,0,this.N);
    } 
    /** Another possible constructor */
    public MomentsData(double[] array, int N) {
        this.N=N;
        this.data = new double[this.N]; 
        System.arraycopy(array,0,this.data,0,this.N);
    }

    /** The version 1 , which uses the data of the object */
    public double calcMoment(int moment) {
	double result=0;
	// Calculate all the moments
	for (int j=0; j<this.N; j++) {
	    result+=Math.pow(this.data[j],moment);
	}
	return result/this.N;
    }
    /** The version 2 , which uses a parameter for the data */
    public static double calcMoment(double[] array, int moment) {
	double result=0;
        int N = array.length;
	// Calculate all the moments
	for (int j=0; j<N; j++) {
	    result+=Math.pow(array[j],moment);
	}
	return result/N;
    }
}



