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
/** Reading a compressed GZIP file */

import java.util.*;
import java.util.zip.*;
import java.io.*;
import simulation.*; // Convenience

public class GZIPReadArray {
    public static void main(String args[]) {
        /* create an array to read */
        double[] array = new double[1000];

        /* read a binary GZIP file */
        try {
            GZFileInBin gzin = new GZFileInBin("test.bin.gz");  
            for (int i=0; i<array.length; i++) {
                System.out.println("from file: "+gzin.readDouble());
            }
            gzin.close();  // close the stream and file
        } catch (IOException e) {}

        System.out.println("ASCII:");
        System.out.println("--------------------------");
        /* read an ASCII GZIP file */
        try {
            GZFileInAscii gzin = new GZFileInAscii("test.asc.gz");  
            String dummy;
            int i=0;
            while ( (dummy=gzin.readLine()) != null ) { // read the array
                System.out.println( Double.valueOf(dummy).doubleValue() );
            }
            gzin.close();  // close the stream and file
        } catch (IOException e) {}
    }
}
