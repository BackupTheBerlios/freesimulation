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
import JSci.awt.*;
import java.awt.*;

public class JSci3DGraph {
    public static void main(String[] args) {
	ContourPlot contourGraph;
        LineGraph3D line3DGraph;
	final int N = 20;
	double[][] array = new double[N][N];

	for (int i=0; i<N; i++) {
	    for (int j=0; j<N; j++) {
		array[i][j]=Math.random();
	    }
	}

	Frame f = new Frame("Test");
	f.setSize(400,500);

	line3DGraph = new LineGraph3D(array);
	contourGraph = new ContourPlot(array);

	f.setLayout(new GridLayout(2,2));
	f.add(contourGraph);
	f.add(line3DGraph);
	f.show();
	// simulation.PrintComponent.Dialog(f,f,"Print It");
    }
}
