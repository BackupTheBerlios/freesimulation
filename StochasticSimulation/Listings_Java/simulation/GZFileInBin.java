
/**
 * GZFileInBin.java
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

public class GZFileInBin extends DataInputStream {
    
    public GZFileInBin(String filename) throws IOException {
        super( new GZIPInputStream 
               ( new BufferedInputStream
                 ( new FileInputStream( filename ) ) ) );
    }

    public GZFileInBin(File file) throws IOException {
        this(file.getPath());
    }

} // GZFileInBin
