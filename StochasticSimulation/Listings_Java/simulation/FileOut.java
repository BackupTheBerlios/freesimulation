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
