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
