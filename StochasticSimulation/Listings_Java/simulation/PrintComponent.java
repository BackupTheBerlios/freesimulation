/**
 * 
 *     Copyright (C) 2002  P. Biechele, F. Petruccione
 *
 *
 *     This program is free software; you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation; either version 2 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program; if not, write to the Free Software
 *     Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 *
 *     
 */
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
