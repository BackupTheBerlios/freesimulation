/** A Monte Carlo method to estimate PI (Hit or Miss)

 we draw random numbers in a square and check how many
 fall into a circle od radius 1.                                   
 
 Specify the number of points on the command line      */


import java.util.Random;

/* if you want to calulate the error of the sample, you
   have to save the estimates in an array.  */

public class Pi_Calc_plain {
  // initialize the generator
  public static Random rand = new Random();
  public static long num;
  public static double inside=0;

  public static void main (String[] args) {
    // check for command line arguments
    if (args.length != 1) {
      System.err.println(" Error: no or wrong number of "
                        +" arguments specified!");
      System.exit(1);
    }
    num = Integer.parseInt(args[0]);

    // Calculation 
    inside=0;
      for (int i=0; i<num; i++) {
        double x = rand.nextDouble();
        double y = rand.nextDouble();
        double r=Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        if (r < 1) {
          inside++;
        }
      }
      inside/=num;
      inside*=4;

      // output of results in shell
      System.out.println(" Calculated Pi using "+num+" points!"); 
      System.out.println(" The exact value is     : "+Math.PI);
      System.out.println(" The estimate for PI is : "+inside);
      System.out.println(" The exact error is     : "+Math.abs(Math.PI-inside));
  }
}


