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
