/** Simple File I/O Program */

import java.io.*;
import java.util.*;
import simulation.*;

public class FileReadConvenience {
    public static void main(String args[]) {
	String filename = "test.asc"; // set the filename
	
	double[] array = new double[100]; // array to load
	try {
	    FileIn in = new FileIn ( filename ); // open a File stream

	    String dummy;
	    int i=0;
	    while ( (dummy=in.readLine()) != null ) { // read the array
		System.out.println( Double.valueOf(dummy).doubleValue() ); // convert to double
	    }	    
	    in.close();  // close the stream and file
	} catch(IOException e) { }
    }
}
