
/**
 * testArray.java
 *
 *
 * Created: Fri May 28 11:54:35 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

public class testArray {
    
    public static void main(String[] args) {
        /* create and instantiate a 2D array */
        double[][] array2;
        array2=new double[10][10];
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                array2[i][j]=j+10*i; } }

        test(array2[1]); // call the method with a row of a 2D array
        // test(array2[][1]); // This is WRONG !!!!
    }
    
    /** The method prints the 1D array argument to 
        the standard output to check the result. */
    static void test(double[] array1) {
        for (int i=0; i<10; i++) {
            System.out.println(i+" "+array1[i]); }
    }

} // testArray
