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
/** Convenience class for File Writing */
package simulation;

import java.io.*;

public class FileOut extends BufferedWriter {

    public FileOut(String filename, boolean append) throws IOException {
        super( new BufferedWriter( new FileWriter(filename,append) ));
    }
    
    public FileOut(String filename) throws IOException {
        this(filename,false);
    }

    public FileOut(File file) throws IOException {
        this(file.getPath());
    }

}
