/** StringBuffer Demonstration class */

import java.io.*;
import java.util.*;
import simulation.*;

public class StringBufferDemo {
    public static void main(String args[]) {
        Random rand = new Random();
	String filename = "testbuff.asc"; // set the filename
	
	double[] array = new double[100]; // array to save
	for (int i=0; i<100; i++) { // create a random array
	    array[i] = rand.nextDouble(); }
	
	try {
            FileOut out = new FileOut(filename);

            /* create a StringBuffer with length 5000 */ 
	    StringBuffer buff = new StringBuffer(5000);
	    for (int i=0; i<100; i++) {  
                buff.append(array[i]).append("\n"); // concatenate
	    }
            out.write(buff.toString()); // convert to String and write 
	    out.close();  // close the stream and file
	} catch(IOException e) {}
    }
}
