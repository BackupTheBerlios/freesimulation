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
 * DirectoryListing.java
 *
 *
 * Created: Wed Jun 30 17:51:32 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import java.io.*;

public class DirectoryListing {
    
    public static void main(String[] args) throws IOException{

        // get a Runtime object
        Runtime r = Runtime.getRuntime();

        Process p;

        // start the process : UNIX
        p = r.exec("ls -al");

        // start the process : Windows
        // p = r.exec("dir");

        // wait for the process to finish
        try {
            p.waitFor(); }
        catch (InterruptedException e) {}
        System.out.println("Exited Process !");

        // read the standard output from the process
        BufferedReader in=new BufferedReader(new InputStreamReader
                                             (p.getInputStream()));
        int c;
        while ((c=in.read())>0) {
            System.out.print((char)c); }        
    }
    
} // DirectoryListing
