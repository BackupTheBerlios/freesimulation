/** Simple Formatted File I/O Program */

import java.io.*;          // for I/O relevant parts
import java.util.*;        // for Random()
import simulation.*;       // for FileOut()
import lava.clib.*;        // for fprintf()
import lava.clib.stdio.*;  // for PrintfFormatString()

public class FileSaveFormatted {
    public static void main(String args[]) {
	Random rand = new Random();
	String filename = "testform.asc"; // set the filename
	double time;

	double[] array = new double[100]; // array to save
	for (int i=0; i<100; i++) { // create a random array
	    array[i] = rand.nextDouble(); }
	
	try {
	    FileOut out = new FileOut( filename ); // open a buffered File stream

	    PrintfFormatString fmt = new PrintfFormatString ("%5.2f %10.6f \n");
	    for (int i=0; i<100; i++) {  // write the array and time
		// write to the output stream = file
		Stdio.fprintf ( out, fmt, new Object [] { new Double( (i/10.0) ), 
							new Double(array[i]) } );
	    }	    
	    out.close();  // close the stream and file
	} catch(IOException e) {}
    }
}
