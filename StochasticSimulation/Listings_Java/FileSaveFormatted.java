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
