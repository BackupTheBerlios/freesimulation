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
 * WeierstrassFunction.java
 *
 *
 * Created: Fri May 28 12:51:05 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import ptolemy.plot.*;
import de.berlios.StochasticSimulation.*;

public class WeierstrassFunction extends Applet {
    
    public WeierstrassFunction() {
        plot = new Plot();
        plot.setBounds(0,0,600,600);
        plot.setTitle("The Weierstrass Function");
        plot.setXLabel("k");
        plot.setYLabel("G(k)");
        this.add(plot);
    }
    
    private static WeierstrassFunction prg;
    private static Frame f;
    private static Plot plot;

    public static void main(String[] args) {
        f = new Frame("Weierstrass Function");
        prg = new WeierstrassFunction();
        f.add(prg);
        f.pack();
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); } } );
        prg.init();
    }
    
    private int Mmax=5;
    private double a=2, b=3;
    private int Npoints = 1000;
    private double[] sum = new double[Npoints];
    private double[] k = new double[Npoints];

    public void init() {
        boolean connect;

        // set the points (=range) of k
        for (int j=0; j<Npoints; j++) {
            k[j]=(double)j/Npoints*Math.PI; }

        // compute and plot the curves in one plot 
        for (int M=0; M<=Mmax; M++) {
            for (int i=0; i<=M; i++) {
                for (int j=0; j<Npoints; j++) {
                    sum[j] += Math.pow(a,-i)*Math.cos(Math.pow(b,i)*k[j]); } } 
            connect=false;
            for (int j=0; j<Npoints; j++) {
                plot.addPoint(M,k[j],sum[j],connect);
                if (connect==false) connect=true; }
        }
        PrintComponent.Dialog(f,plot,"Weierstrass");
    }

} // WeierstrassFunction
