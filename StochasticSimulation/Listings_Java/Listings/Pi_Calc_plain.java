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


