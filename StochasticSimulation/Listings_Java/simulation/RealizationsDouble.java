package simulation;

/**
 * RealizationsDouble.java
 *
 *
 * Created: Wed May 26 13:18:37 1999
 *
 * @author Peter Biechele
 * @version 1.0
 *
 *
 */
 
/**
 *  <p>
 * You can add single realizations (time series) to this class
 * and get the final results (moments) by using the getArray() method.
 * You can set the maximum computed moment and the length of the array
 * for each instance of the class, but only once.
 * <p>
 */
public class RealizationsDouble  {
    
    private int maxMoment;
    private int length;
    private int sampleSize;

    /* The acumulated moments of all added realizations. */
    private double[][] array; 

    /** if no maximum moment is specified, use 4. */
    public RealizationsDouble(int length) {	
	this(length, 4);
    }
    /** Create an instance, set number of elements, etc. */
    public RealizationsDouble(int length, int max) {
	this.sampleSize=0;
	this.length=length;
	this.maxMoment=max;
	this.array = new double[this.maxMoment][this.length]; }

    public int getLength() {
	return length; }
    
    public int getMaxMoment() {
	return maxMoment; }

    public int getSampleSize() {
	return sampleSize; }

    public void setLength(int length) {
	if (length!=0) {
	    this.length=length; } }	    
    
    public void setMaxMoment(int max) {
	if (maxMoment!=0) {
	    this.maxMoment=max; } }
    
    /** Add a new realization to the statistics. */
    public void add(double[] realization) {
	sampleSize++;
       // compute the moments
        for (int j=1; j<=maxMoment; j++) {
            for (int i=0; i<length; i++) {
		array[j-1][i]+=Math.pow(realization[i],j); } }
    }

    /** Compute the statistical values and return them. 
        Already divided by the sample size = number of realizations. */
    public double[][] getMoments() {
	double[][] dummy = new double[maxMoment][length];
	for (int i=0; i<maxMoment; i++) {
	    for (int j=0; j<length; j++) {
		dummy[i][j] = array[i][j] / sampleSize; } }  
	return dummy; }    
    
} // RealizationsDouble


