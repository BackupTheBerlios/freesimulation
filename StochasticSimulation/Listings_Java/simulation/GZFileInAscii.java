
/**
 * GZFileInAscii.java
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

public class GZFileInAscii extends BufferedReader {
    
    public GZFileInAscii(String filename) throws IOException {
        super( new InputStreamReader 
               ( new GZIPInputStream 
                 ( new BufferedInputStream
                   ( new FileInputStream( filename ) ) ) ) );
    }

    public GZFileInAscii(File file) throws IOException {
        this(file.getPath());
    }

} // GZFileInAscii
