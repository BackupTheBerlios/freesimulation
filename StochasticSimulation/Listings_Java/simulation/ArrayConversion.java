package simulation;

/**
 * ArrayConversion.java
 *
 *
 * Created: Thu Jun 17 19:27:54 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */
public class ArrayConversion {
    
    public ArrayConversion() {
    }
    
    public static void main(String[] args) {        
    }

    /** Convert a 1D integer array to a 1D double array. */
    public static double[] int2Double1D(int[] intArray) {
        int N=intArray.length;
        double[] doubleArray = new double[N];

        for (int i=0; i<N; i++) {
            doubleArray[i]=(double)intArray[i]; }
        return doubleArray;
    }
    /** Convert a SQUARE 2D integer array to a 2D double array. */
    public static double[][] int2Double2D(int[][] intArray) {
        int N=intArray[0].length;
        double[][] doubleArray = new double[N][N];

        for (int i=0; i<N; i++) {
            doubleArray[i]=ArrayConversion.int2Double1D(intArray[i]); }
        return doubleArray;
    }


    /** Convert a 1D double array to a 1D integer array. 
        The conversion is just the casting operator. */
    public static int[] double2Int1D(double[] doubleArray) {
        int N=doubleArray.length;
        int[] intArray = new int[N];

        for (int i=0; i<N; i++) {
            intArray[i]=(int)doubleArray[i]; }
        return intArray;
    }

    /** Convert a SQUARE 2D double array to a 2D integer array. 
        The conversion is just the casting operator. */
    public static int[][] double2Int2D(double[][] doubleArray) {
        int N=doubleArray.length;
        int[][] intArray = new int[N][N];

        for (int i=0; i<N; i++) {
            intArray[i]=ArrayConversion.double2Int1D(doubleArray[i]); }
        return intArray;
    }

} // ArrayConversion
