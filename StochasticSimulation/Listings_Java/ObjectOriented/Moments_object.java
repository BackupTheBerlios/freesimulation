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
import java.util.Random;

/** the object oriented version using two different approaches */
public class Moments_object {
    public static void main(String[] args) {
	Random rand = new Random();
	// Number of points used
	final int N=50000;
	// what moments are to be calculated
	int moments_start=1;
	int moments_end=20;
	// declare and instantiate arrays
	double[] moments;
	moments=new double[moments_end-moments_start+1];
	double[] numbers;
	numbers=new double[N];
	
	// create N random numbers
	for (int i=0; i<N; i++) {
	    numbers[i]=rand.nextDouble(); }
	
        /* Version 1 --------------------- */
	// instantiate an object of class MomentsData called dat !
	MomentsData dat = new MomentsData(numbers);

	// initialize array of moments and calculate
	for (int i=0; i<=(moments_end-moments_start); i++) {
	    moments[i]=0;
	    // Call method for calculation !!!!!!!!!!!!!!!
	    moments[i]=dat.calcMoment(i+moments_start);
	}
	// display the moments
	for (int i=0; i<=(moments_end-moments_start); i++) {
	    System.out.println(" Moment #"+(i+moments_start)+
			       	": "+moments[i]);
	}

        System.out.println();

        /* Version 2 --------------------  */
	for (int i=0; i<=(moments_end-moments_start); i++) {
	    moments[i]=0;
	    // Call method for calculation !!!!!!!!!!!!!!!
	    moments[i]=MomentsData.calcMoment(numbers,i+moments_start);
	}
	// display the moments
	for (int i=0; i<=(moments_end-moments_start); i++) {
	    System.out.println(" Moment #"+(i+moments_start)+
			       	": "+moments[i]);
	}
    }
}
