/** Reading a compressed GZIP file */

import java.util.*;
import java.util.zip.*;
import java.io.*;
import simulation.*; // Convenience

public class GZIPReadArray {
    public static void main(String args[]) {
        /* create an array to read */
        double[] array = new double[1000];

        /* read a binary GZIP file */
        try {
            GZFileInBin gzin = new GZFileInBin("test.bin.gz");  
            for (int i=0; i<array.length; i++) {
                System.out.println("from file: "+gzin.readDouble());
            }
            gzin.close();  // close the stream and file
        } catch (IOException e) {}

        System.out.println("ASCII:");
        System.out.println("--------------------------");
        /* read an ASCII GZIP file */
        try {
            GZFileInAscii gzin = new GZFileInAscii("test.asc.gz");  
            String dummy;
            int i=0;
            while ( (dummy=gzin.readLine()) != null ) { // read the array
                System.out.println( Double.valueOf(dummy).doubleValue() );
            }
            gzin.close();  // close the stream and file
        } catch (IOException e) {}
    }
}
