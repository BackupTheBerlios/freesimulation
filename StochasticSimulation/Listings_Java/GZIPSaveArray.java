/** Writing a compressed GZIP file 

 again there is a depreciation warning in Java 1.1,
 which vanishes using Java 2. 
*/

import java.util.*;
import java.util.zip.*;
import java.io.*;
import simulation.*; // Convenience

public class GZIPSaveArray {
    public static void main(String args[]) {
        /* create an array to save */
        double[] array = new double[1000];
        Random rand = new Random();
        for (int i=0; i<array.length; i++) {
            array[i]=rand.nextDouble(); }

        /* create a binary GZIP output file */
        try {
            /* DataOutputStream gzout = new DataOutputStream
                ( new GZIPOutputStream 
                  ( new BufferedOutputStream
                    (new FileOutputStream("test.bin.gz") ) ) ); */
            // Convenience: 
            GZFileOutBin gzout = new GZFileOutBin("test.bin.gz");  
            /* store the data in file */
            for (int i=0; i<array.length; i++) {
                gzout.writeDouble(array[i]); }
        
            gzout.close();
        } catch (IOException e) {}

        /* create an ASCII GZIP output file */
        try {
            /* PrintStream gzout = new PrintStream
                ( new GZIPOutputStream 
                  ( new BufferedOutputStream
                    (new FileOutputStream("test.asc.gz") ) ) ); */
            // Convenience:
            GZFileOutAscii gzout = new GZFileOutAscii("test.asc.gz");  
            /* store the data in file */
            for (int i=0; i<array.length; i++) {
                gzout.println(array[i]); }
        
            gzout.close();
        } catch (IOException e) {}
    }
}
