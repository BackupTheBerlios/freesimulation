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
/* <applet code="PlotEasy.class" width=400 height=400> 
 	Run Applet </applet> */
import java.applet.Applet;
import java.awt.*;

/** The easisest way to plot a Chart in Java,
    use it as Applet or as an application
    There is no way to stop the program or to close the window, 
    you have to kill the program. */
public class PlotEasy extends Applet {
    public static void main(String[] args) {
	Applet a = new PlotEasy();
	Frame f = new Frame("Easy Plotting");
	f.add("Center",a);
	f.setSize(400,400);
	f.show();
	a.init();
    }

    /* Here we define the function to be plotted */
    double f(double x) {
	return (Math.cos(x/2)+Math.sin(x/7)+2)*getSize().height/4;
    }
    /* Here we plot the function 
       because we are using the actual size of the canvas,
       the plot gets resized each time you resize the window */
    public void paint(Graphics g) {
	for (int x=0; x<getSize().width; x++) {
	    g.drawLine(x,(int)f(x), x+1,(int)f(x+1));
	}
    }
}
