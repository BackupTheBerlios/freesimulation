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
/** FileCheck Demonstration class */

import java.io.*;

public class FileCheck {
    public static void main(String args[]) {
        if (args.length==0) {
            System.out.println(" Please supply a filename/directory !");
            System.exit(1);  } 
        
	String filename = args[0]; // set the filename
        File file = new File(filename);

        /* Check for existence of file */
        if ( file.exists() == true ) {
            System.out.println(" File "+filename+" exists!");
        } else {
            System.out.println(" File "+filename+" does not exist!");}
        /* Check for existence AND not directory of a file */
        if ( file.isFile() == true) {
            System.out.println(" File "+filename+" exists!");
        } else {
            System.out.println(" File "+filename+" does not exist or "+
                               "is a directory!");}
        /* Check for existence AND not directory of a file */
        if ( file.isDirectory() == true) {
            System.out.println(" File "+filename+" is a directory!");
            String[] filelist = file.list();
            for (int i=0; i<filelist.length; i++) {
                System.out.println(filelist[i]); }
        } else {
            System.out.println(" File "+filename+" is not a directory!");}
    }
}
