
/**
 * GZFileOutAscii.java
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

public class GZFileOutAscii extends PrintStream {
    
    public GZFileOutAscii(String filename) throws IOException {
        super( new GZIPOutputStream 
               ( new BufferedOutputStream
                 ( new FileOutputStream( filename ) ) ) );
    }

    public GZFileOutAscii(File file) throws IOException {
        this(file.getPath());
    }

} // GZFileOutAscii
