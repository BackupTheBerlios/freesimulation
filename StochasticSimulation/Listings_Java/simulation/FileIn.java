/** Convenience class for File Reading */
package simulation;

import java.io.*;

public class FileIn extends BufferedReader {
  public FileIn(String filename) throws IOException {
     super( new BufferedReader( new FileReader(filename) ));
  }
  public FileIn(File file) throws IOException {
     this(file.getPath());
  }
}
