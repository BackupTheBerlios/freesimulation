
/**
 * Gnuplot.java
 *
 *
 * Created: Wed Jun 30 17:51:32 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import java.io.*;

public class Gnuplot {
    
    public static void main(String[] args) throws IOException {

        // get a Runtime object
        Runtime r = Runtime.getRuntime();

        // start the process: gnuplot
        Process p = r.exec("gnuplot Gnuplot.gnu");
    }
    
} // Gnuplot
