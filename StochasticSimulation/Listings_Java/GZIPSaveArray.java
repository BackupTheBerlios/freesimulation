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
/** Writing a compressed GZIP file 

 again there is a depreciation warning in Java 1.1,
 which vanishes using Java 2. 
*/

import java.util.*;
import java.util.zip.*;
import java.io.*;
import simulation.*; // Convenience

public class GZIPSaveArray {
    public static void main(String args[]) {
        /* create an array to save */
        double[] array = new double[1000];
        Random rand = new Random();
        for (int i=0; i<array.length; i++) {
            array[i]=rand.nextDouble(); }

        /* create a binary GZIP output file */
        try {
            /* DataOutputStream gzout = new DataOutputStream
                ( new GZIPOutputStream 
                  ( new BufferedOutputStream
                    (new FileOutputStream("test.bin.gz") ) ) ); */
            // Convenience: 
            GZFileOutBin gzout = new GZFileOutBin("test.bin.gz");  
            /* store the data in file */
            for (int i=0; i<array.length; i++) {
                gzout.writeDouble(array[i]); }
        
            gzout.close();
        } catch (IOException e) {}

        /* create an ASCII GZIP output file */
        try {
            /* PrintStream gzout = new PrintStream
                ( new GZIPOutputStream 
                  ( new BufferedOutputStream
                    (new FileOutputStream("test.asc.gz") ) ) ); */
            // Convenience:
            GZFileOutAscii gzout = new GZFileOutAscii("test.asc.gz");  
            /* store the data in file */
            for (int i=0; i<array.length; i++) {
                gzout.println(array[i]); }
        
            gzout.close();
        } catch (IOException e) {}
    }
}
