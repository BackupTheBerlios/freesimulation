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
 * GZFileOutBin.java
 *
 *
 * Created: Thu Apr  8 19:19:34 1999
 *
 * @author Peter Biechele
 * @version
 */
package simulation;

import java.io.*;
import java.util.zip.*;

public class GZFileOutBin extends DataOutputStream {
    
    public GZFileOutBin(String filename) throws IOException {
        super( new GZIPOutputStream 
               ( new BufferedOutputStream
                 ( new FileOutputStream( filename ) ) ) );
    }

    public GZFileOutBin(File file) throws IOException {
        this(file.getPath());
    }

} // GZFileOutBin
