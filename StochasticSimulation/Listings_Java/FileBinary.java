/** StringBuffer Demonstration class */

import java.io.*;
import java.util.*;
import simulation.*;

public class FileBinary {
    public static void main(String args[]) {
        Random rand = new Random();
	String filename = "test.bin"; // set the filename
	
	double[] array = new double[100]; // array to save
	for (int i=0; i<100; i++) { // create a random array
	    array[i] = rand.nextDouble(); }
	
        /* OUTPUT */
	try {
            DataOutputStream out = new DataOutputStream 
                (new FileOutputStream(filename));

	    for (int i=0; i<100; i++) {  
                out.writeDouble(array[i]);
	    }
	    out.close();  // close the stream and file
	} catch(IOException e) {}

        /* INPUT */
	try {
            DataInputStream in = new DataInputStream 
                (new FileInputStream(filename));

	    for (int i=0; i<100; i++) {  
                System.out.println("mem: "+array[i]+
                                   " from file: "+in.readDouble());
	    }
	    in.close();  // close the stream and file
	} catch(IOException e) {}
    }
}
