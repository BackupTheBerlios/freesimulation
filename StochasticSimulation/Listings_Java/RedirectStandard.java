/** Redirection of standard output and error 

 In Java 1.1: You get a depreciation message, which is not important.
 In Java 2: The "wrong" depreciation message is gone.
 */
import java.io.*;

public class RedirectStandard {
    public static void main(String args[]) {
  
        try {
            PrintStream out = new PrintStream
                (new BufferedOutputStream(new FileOutputStream("Standard.out")));
            System.setOut(out);
            System.setErr(out);
            
            System.out.println(" This must go to the file !"); 
            
            out.close();  // !!!! DO NOT FORGET
        } catch (IOException e) {}
    }
}
