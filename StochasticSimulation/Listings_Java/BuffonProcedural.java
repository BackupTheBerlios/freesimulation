/** Procedural Version of the Buffon Needle problem */

public class BuffonProcedural {
    /** some constants of the problem */
    final static int NumberOfRows=10;
    final static int NeedleLength=20;
    final static int TableWidth=200;    
    final static int TableHeight=NeedleLength*NumberOfRows;

    /** The main program */
    public static void main (String[] args) {
	int N=20000;  // number of needles to be drawn
	int cross=0;  // number of crossings, which occured
	double[] needlePos = new double [4]; // positions of the needles

        // The loop over all needles to be drawn
	for (int i=0; i<N; i++) {
            // create a random needle: call method drawNeedle()
	    needlePos = drawNeedle();
            // Is the needle crossing a line ??? 
            // use the method checkNeddle()
	    if (checkNeedle(needlePos) == true) cross++;
	}
        // The variable cross contains the number of intersections
	System.out.println(" crossings = "+cross+";  N = "+N);
        // The estimate for pi is printed
	System.out.println(" Estimated value of pi="
			   + 2*(double)N/cross);
    }

    /** Create a needle at a random place and a random orientation */
    private static double[] drawNeedle() {
        // store the position of the needle: (X0,Y0) to (X1,Y1)
	double[] position = new double[4];
        // dummy variables
	double deltaX,deltaY,ysign;

        // create starting position for the needle
	position[0]=TableWidth*Math.random();
	position[1]=TableHeight*Math.random();
        // create the end points of the needle
	deltaX=NeedleLength*Math.sin(2*Math.PI*Math.random()-Math.PI); 
	deltaY = Math.sqrt(NeedleLength*NeedleLength-deltaX*deltaX);
	ysign = (Math.random()<0.5? -1 : 1);
        // store the end points of the needle
	position[2]=position[0]+deltaX;
	position[3]=position[1]+deltaY;
        // return the position of the start and endpoints of the needle
	return position;
    }

    /** Check if a needle crosses a line:
        The position of the needle is supplied and it returns true
        or false. */
    private static boolean checkNeedle(double[] needle) {
        // loop over all lines in the plane
	for (int yLine=0; yLine<=NeedleLength*NumberOfRows; 
                          yLine+=NeedleLength) {
            // check if the start and endpoints are on opposite
            // sides of the line: if yes return the method with true
	    if((needle[1]<=yLine && needle[3]>=yLine) ||
	       (needle[1]>=yLine && needle[3]<=yLine)) return true;	   
	}
        // if no line is crossed by this needle, return false
	return false;	
    }
}
