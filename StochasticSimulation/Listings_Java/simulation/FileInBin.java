/** Convenience class for binary File Reading */
package simulation;

import java.io.*;

public class FileInBin extends DataInputStream {
  public FileInBin(String filename) throws IOException {
     super( new BufferedInputStream
                 ( new FileInputStream( filename ) ) );
   }
  public FileInBin(File file) throws IOException {
     this(file.getPath());
  }
}
