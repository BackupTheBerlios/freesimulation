/** Convenience class for binary File Writing */
package simulation;

import java.io.*;

public class FileOutBin extends DataOutputStream {

    public FileOutBin(String filename, boolean append) throws IOException {
        super( new BufferedOutputStream
                 ( new FileOutputStream( filename ) ) );
    }
    
    public FileOutBin(String filename) throws IOException {
        this(filename,false);
    }

    public FileOutBin(File file) throws IOException {
        this(file.getPath());
    }

}
