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
 * Gnuplot.java
 *
 *
 * Created: Wed Jun 30 17:51:32 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import java.io.*;

public class Gnuplot {
    
    public static void main(String[] args) throws IOException {

        // get a Runtime object
        Runtime r = Runtime.getRuntime();

        // start the process: gnuplot
        Process p = r.exec("gnuplot Gnuplot.gnu");
    }
    
} // Gnuplot
