
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
