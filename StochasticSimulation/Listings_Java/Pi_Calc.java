/** A Monte Carlo method to estimate PI (Hit or Miss)

 we draw random numbers in a square and check how many
 fall into a circle od radius 1.                                   
 
 Specify the number of points on the command line      */


import java.util.Random;
import java.awt.*;
import java.awt.event.*;

/* if you want to calulate the error of the sample, you
   have to save the estimates in an array.  */

public class Pi_Calc extends Frame {
  // initialize the generator
  public static Random rand = new Random();
  public static long num;
  // set layout of window
  public static BorderLayout mylayout = new BorderLayout(10,10);
  public static int width=600;
  public static int height=600;
  public static double inside=0;

  public static void main (String[] args) {
    // check for command line arguments
    if (args.length != 1) {
      System.err.println(" Error: no or wrong number of "
                        +" arguments specified!");
      System.exit(1);
    }
    Pi_Calc.num = Integer.parseInt(args[0]);

    // create a window and plot points
    Frame f= new Frame("Calc Pi using Monte Carlo");
    // Event to close the window and exit
    f.addWindowListener(new WindowAdapter() {  // Handle window close
      public void windowClosing(WindowEvent e) { System.exit(0); }
    });
    // size of window
    f.setSize(width,height);
    // set a layout manager
    f.setLayout(mylayout);
    // Plot a text on the top bar of the window
    f.add(new Label("Calculate PI using Hit or Miss Method. "+
                    "  To quit, close the window!"),"North");
    // Create a drawing area for the points
    Drawing draw = new Drawing(f,width,height);
    // add it to window in the middle
    f.add(draw,"Center");
    // set the background color of the drawing panel
    draw.setBackground(Color.white);
    // display it all
    f.show();
  }

  
  static class Drawing extends Canvas {
    protected Frame frame;
    protected int width;
    protected int height;

    public Drawing(Frame frame, int width, int height) {
      this.frame=frame;
      this.width=width;
      this.height=height;
    }
    public void paint(Graphics g) {                               
      // Calculation and plot points:
      // plot the quarter of a circle
      g.setColor(Color.red);
      g.drawArc(-width,0,2*width,2*height,0,90);
      g.drawArc(-width-1,1,2*width,2*height,0,90);
      g.drawArc(-width-2,2,2*width,2*height,0,90);
      g.setColor(Color.black);

      // Calculation
      Pi_Calc.inside=0;
      for (int i=0; i<Pi_Calc.num; i++) {
        double x = rand.nextDouble();
        double y = rand.nextDouble();
        double r=Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        // plot the point
        x*=width;
        y*=height;
        g.drawLine((int)x,(int)y,(int)x,(int)y);
        if (r < 1) {
          Pi_Calc.inside+=1;
        }
      }
      Pi_Calc.inside/=Pi_Calc.num;
      Pi_Calc.inside*=4;

      // plot the quarter of a circle again
      g.setColor(Color.red);
      g.drawArc(-width,0,2*width,2*height,0,90);
      g.drawArc(-width-1,1,2*width,2*height,0,90);
      g.drawArc(-width-2,2,2*width,2*height,0,90);
      g.setColor(Color.black);

      // output of results in shell
      System.out.println(" Calculated Pi using "+Pi_Calc.num+" points!"); 
      System.out.println(" The exact value is     : "+Math.PI);
      System.out.println(" The estimate for PI is : "+Pi_Calc.inside);
      System.out.println(" The exact error is     : "+Math.abs(Math.PI-Pi_Calc.inside));
    }
  }

}
