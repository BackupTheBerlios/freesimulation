/** Simple File I/O Program */

import java.io.*;
import java.util.*;
import simulation.*;

public class FileWriteConvenience {
    static Random rand = new Random();

    public static void main(String args[]) {
	String filename = "test.asc"; // set the filename
	
	double[] array = new double[100]; // array to save
	for (int i=0; i<100; i++) { // create a random array
	    array[i] = rand.nextDouble(); }
	
	try {
	    FileOut out = new FileOut( filename ); // open a buffered File stream

	    String dummy;
	    for (int i=0; i<100; i++) {  // write the array
		dummy = Double.toString( array[i] ) + "\n"; // convert to String 
		out.write( dummy );  // write to stream = file
	    }	    
	    out.close();  // close the stream and file
	} catch(IOException e) {}
    }
}
