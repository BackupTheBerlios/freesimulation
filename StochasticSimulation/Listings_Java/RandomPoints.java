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
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;


public class RandomPoints extends Applet {
  final int width = 400;
  final int height = 200;

    int N;
    Random rand = new Random();

    // Define and allocate the 2D array and averages
    double[][] points; 
    double[] avg = new double[2];

  public void init() {
    N=Integer.parseInt(this.getParameter("NumberofPoints"));
    points=new double [N][2];

    System.out.println(N+" Points are plotted !");

    
    // Create the random points in 2 dimensions 
    for (int i=0; i<N; i++) {
      points[i][0]=rand.nextDouble();
      points[i][1]=rand.nextDouble();
    }

    // Calulate average
    for (int j=0; j<N; j++) {
      avg[0]+=points[j][0];
      avg[1]+=points[j][1];
    }
    avg[0]/=N;
    avg[1]/=(double)N;

    System.out.println("Results:");
    for (int i=0;i<2;i++) {
      System.out.println(" Average of Coordinate "+i+": "+avg[i]);
    }

    this.setBackground(Color.white);

  }

  public void paint(Graphics g) {
    for(int i=0; i<N; i++) {
      points[i][0]*=width;
      points[i][1]*=height;
      g.drawLine((int)points[i][0],(int)points[i][1],
                   (int)points[i][0],(int)points[i][1]);
    }
    g.setFont(new Font("Helvetica",Font.BOLD, 10));
    g.drawString("These are random points !",1,height+10);
    g.setColor(Color.pink);
    for (int j=30; j>20; j--) {
      g.drawOval((int)(avg[0]*width)-j/2,(int)(avg[1]*height)-j/2,j,j);
    }
  }

}
