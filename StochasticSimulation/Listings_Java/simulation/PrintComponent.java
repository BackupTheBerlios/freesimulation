package simulation;

/**
 * PrintComponentTest.java
 *
 *  Prints a component to the printer or a file using a dialog screen.
 *  The component to print gets rescaled to fit into the whole available
 *  area of the page. This can have desireable or undesirable results.
 *
 * Created: Mon Apr 12 11:53:56 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import java.awt.*;
import java.util.*;

public class PrintComponent  {
    
    public PrintComponent() {
    }
    
    public static void Dialog(Frame printFrame, Component com, String s) {
        // get the original size of the component for restoring
        Dimension dimOrig = com.getSize();
        
        // get a "connection" to the printer
        Toolkit toolkit = com.getToolkit();
        // get a dialog box for the print job 
        PrintJob job = toolkit.getPrintJob(printFrame,s,(Properties)null);
        if (job != null) {
            // rescale component for printing
            Dimension dim = job.getPageDimension();
            com.setSize(dim);
            // get the graphics handle
            Graphics pg = job.getGraphics();
            // print all components contained in com
            com.printAll(pg);
            // send it to printer
            pg.dispose();
            // close all necessary stuff
            job.end();
            // restore original size of component
            com.setSize(dimOrig);
        }
    }
    
} // PrintComponent
