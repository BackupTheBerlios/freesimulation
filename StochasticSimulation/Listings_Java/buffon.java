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
/** this is the program nadel which simulates the buffon needle */

import needle.*;

public class buffon {
        public static void main(String args[]){
        final int N=10000;  //variable may not be changed
                            // N denotes the number of trials
        int hit=0;            // hit denotes the number of crosses 
                            // of some line
        int cross;           // cross conts the crosses
        double x1,x2,y1,y2;
        x1=1.0;      // these four arguments are dummy !!!!
        x2=1.0;
        y1=1.0;
        y2=2.0;
        /* The First needle (object is created) 
              The object name is: needle */
        needle FirstNeedle = new needle(x1,x2,y1,y2);
        
        /* In the following loop we draw N needles.
           To this end we have to create needles with the NextNeedle
           method of the needle class.
           We check whether a needle crosses a line with the 
           CrossInspection method of the needle class. */
        
        for (int i=1; i<N+1; i++){
                needle Drawneedle=FirstNeedle.NextNeedle(FirstNeedle);
                hit = Drawneedle.CrossInspection(Drawneedle.NeedleX1,
                Drawneedle.NeedleX2,Drawneedle.NeedleY1,Drawneedle.NeedleY2);
        }        
        
        /* Finally we print the result */
        
        System.out.println("hit="+hit+";  N="+N);
        System.out.println("Estimated value of pi="
                              +2.* (double) N / (double) hit);
        }
}
