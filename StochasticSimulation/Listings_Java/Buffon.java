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
