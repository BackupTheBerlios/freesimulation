/**
 * 
 *     Copyright (C) 2002  P. Biechele, F. Petruccione
 *
 *
 *     This program is free software; you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation; either version 2 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program; if not, write to the Free Software
 *     Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *
 *     
 */
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
