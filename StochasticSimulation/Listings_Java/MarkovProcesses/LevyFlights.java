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

/**
 * LevyFlights.java
 *
 *
 * Created: Thu Dec  3 10:25:35 1998
 *
 * @author Peter Biechele
 * @version
 */

import de.berlios.StochasticSimulation.*;
import java.applet.Applet;
import ptolemy.plot.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class LevyFlights extends PlotApplet {
    final int N = 20000;
    final double alpha = 0.8;
    double[] process = new  double[N];

    public void init() {

	super.init();
	Plot plot = (Plot) plot();

	plot.setTitle("Levy Process");
	Plotting.plot2D(plot,0,process);

    }

    public LevyFlights() {
	Random rand = new Random();
	
	process[0]=Distribution.nextLevy(rand,alpha);
	for (int i=1; i<N; i++) {
	    process[i]=process[i-1]+Distribution.nextLevy(rand,alpha);
	}
    }
    
    public static void main(String[] args) {
	Frame myframe = new Frame("LevyFlights - Pebi");
	WindowListener listen = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        };
	myframe.addWindowListener(listen);
	myframe.setSize(500,600);
	myframe.setBackground(Color.white);
 	Applet a = new LevyFlights();
	myframe.add(a);
	myframe.show();
	a.init();
    }
    
    // Has to be overwritten for some unknown reason !
    public String getParameter(String name) {
        return null;
    }

} // LevyFlights
