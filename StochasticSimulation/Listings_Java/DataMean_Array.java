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
import java.util.Random;  // import the Random Number class

public class DataMean_Array {
  public static void main(String[] args) {
    Random rand;       // Declare a random number class object
    int i,N;            
    double mean,sum;
    double RandomNumber[];  // declare an array of 1 dimension
    
    N=10000;               // set the number of random numbers
    rand = new Random();   // create/allocate a random number object

    RandomNumber = new double[N];  // Allocate/Instantiate the whole array

    // Generate a lot of random numbers and store them in an array
    for (i=0; i<N; i++) {      
      RandomNumber[i]=rand.nextDouble(); 
    }                // nextDouble() returns the next random number

    // Calculate the sum of all random numbers
    sum=0;
    for (i=0; i<N; i++) {
      sum+=RandomNumber[i];
    }
    // Calculate the mean of the array
    mean=sum/N;

    System.out.println(" The mean of "+N+" random numbers \n"+
                       " between 0 and 1 is "+mean+" !");
  }
}
