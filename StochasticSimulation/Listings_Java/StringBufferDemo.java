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
