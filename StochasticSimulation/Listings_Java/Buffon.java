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
/** This is the program Buffon which simulates the buffon needle 
    using the Needle object. 
    Object Oriented Approach  */

import Needle.*;

public class Buffon {
    public static void main(String args[]){
	Needle draw;
	int hit; // how many needles hit one of the lines
	final int N=20000;  /* variable may not be changed by the program
			       N denotes the number of trials */
    
	/* In the following loop we draw N Needles.
	   To this end we have to create needles with the constructor
	   of the Needle class.
	   We check whether a Needle crosses a line with the 
	   crossInspection method of the Needle class. */
        hit=0;
	for (int i=0; i<N; i++){
            // create a new needle
	    draw=new Needle();
            // check if needle crosses a line
	    if ( draw.crossInspection() == true) {
                hit++; }
	}        
	
	/* Finally we print the result */ 
        System.out.println(" Table width    : "+Needle.tableWidth);
        System.out.println(" Table height   : "+Needle.tableHeight);
        System.out.println(" Number of Rows : "+Needle.numberOfRows);
        System.out.println(" Needle Length  : "+Needle.needleLength);
        System.out.println();
        System.out.println(" crossings ="+hit+";  N="+N);
	System.out.println(" Estimated value of pi="
			   +2 * (double)N / hit );
    }
}
