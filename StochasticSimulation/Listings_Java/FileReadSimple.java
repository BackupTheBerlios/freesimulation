/** Simple File I/O Program */

import java.io.*;
import java.util.*;

public class FileReadSimple {
    public static void main(String args[]) {
	String filename = "test.asc"; // set the filename
	
	double[] array = new double[100]; // array to load
	try {
	    FileReader fin = new FileReader( filename ); // open a File stream
	    BufferedReader in = new BufferedReader(fin); // use a buffer
	    /* OR BufferedReader in = new BufferedReader(new FileReader( filename )); */

	    String dummy;
	    int i=0;
	    while ( (dummy=in.readLine()) != null ) { // read the array
		System.out.println( Double.valueOf(dummy).doubleValue() ); // convert to double
	    }	    
	    in.close();  // close the stream and file
	} catch(IOException e) { }
    }
}
