
/**
 * DirectoryListing.java
 *
 *
 * Created: Wed Jun 30 17:51:32 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import java.io.*;

public class DirectoryListing {
    
    public static void main(String[] args) throws IOException{

        // get a Runtime object
        Runtime r = Runtime.getRuntime();

        Process p;

        // start the process : UNIX
        p = r.exec("ls -al");

        // start the process : Windows
        // p = r.exec("dir");

        // wait for the process to finish
        try {
            p.waitFor(); }
        catch (InterruptedException e) {}
        System.out.println("Exited Process !");

        // read the standard output from the process
        BufferedReader in=new BufferedReader(new InputStreamReader
                                             (p.getInputStream()));
        int c;
        while ((c=in.read())>0) {
            System.out.print((char)c); }        
    }
    
} // DirectoryListing
