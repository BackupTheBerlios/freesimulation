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
/** compute the mean of N random numbers distributed uniformly */

public class DataMean {
  public static void main(String[] args) {
    int i,N;            
    double mean;
    N=10000;               // set the number of random numbers

    mean=0;
    for (i=1; i<N; i++) {      // Calculate the mean of the numbers
        mean+=Math.random(); // draw a random number of type double 
    }                // nextDouble() returns the next random number
    mean/=N;

    System.out.println(" The mean of "+N+" random numbers \n"+
                       " between 0 and 1 is "+mean+" !");
  }
}
