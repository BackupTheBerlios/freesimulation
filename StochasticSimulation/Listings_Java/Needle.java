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
    A class representing a needle of the Buffon Needle Problem
 **/

public class Needle {
    // These are the data fields of the class
    double needleX1, needleX2, needleY1, needleY2;
    
    /* we have to fix some constants: class variables
       these can be changed !! */
    static int needleLength = 20;     // Define length of the needles    
    static int numberOfRows = 10;     // Define number of rows    
    /* Define the size of the table on which the needles live. 
       These are fixed constants and can not be changed */      
    final static int tableHeight = needleLength*numberOfRows; 
    final static int tableWidth = 200;    

    /** This is the main constructor: 
	It calculates the position of the Needle object */
    public Needle() {
	double deltaX, deltaY;
	int ysign;
	
	// Create the coordinates of starting point
	this.needleX1 = tableWidth*Math.random();
	this.needleY1 = tableHeight*Math.random();
	// create the coordinates of end point
	deltaX = needleLength*
	 	Math.sin(2*Math.PI*Math.random()-Math.PI);   
	deltaY = Math.sqrt(needleLength*needleLength-deltaX*deltaX);
	ysign = (Math.random()<0.5? -1 : 1);
	this.needleX2 = this.needleX1+deltaX;
	this.needleY2 = this.needleY1+ysign*deltaY;   
    }
    
    /** This is the method CrossInspection which checks whether 
     	the Needle crosses a line or not. */
    public boolean crossInspection() {
	for (int yLine=0; yLine<=needleLength*numberOfRows; 
	     	yLine+=needleLength) {
	    if( (needleY1<=yLine && needleY2>=yLine) ||
		(needleY1>=yLine && needleY2<=yLine) ) { 
		return true;
	    } 
	}
	return false;
    }
    
}        

 
