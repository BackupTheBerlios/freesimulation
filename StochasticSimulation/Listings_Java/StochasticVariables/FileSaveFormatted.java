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
/** 
 *
 * Simple Formatted File I/O Program 
 *
 */

import java.io.*;          // for I/O relevant parts
import java.util.*;        // for Random()
import de.berlios.StochasticSimulation.*;       // for FileOut()


public class FileSaveFormatted {
    public static void main(String args[]) {
	Random rand = new Random();
	String filename = "testform.asc"; // set the filename
	double time;

	double[] array = new double[101]; // array to save
	for (int i=0; i<101; i++) { // create a random array
	    array[i] = rand.nextDouble(); }

	try {
	    FileOut out = new FileOut( filename ); // open a buffered File stream

	    String dummy;
	    for (int i=0; i<101; i++) {  // write the array and time
		
		dummy = (new Format("%5.2f").format(i/10.0))
		    + (new Format("%10.6f\n").format(array[i]));
		//System.out.println(dummy);
		out.write( dummy );  // write to stream = file
	    }	    
	    out.close();  // close the stream and file
	} catch(IOException e) {}
    }
}
