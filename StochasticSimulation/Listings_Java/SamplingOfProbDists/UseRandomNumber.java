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
/** A small test program, to test the Random Number class */
public class UseRandomNumber {
    public static void main(String args[]) {
	final int Seed=123;
	final int N=100000;
	double[] numbers = new double[N];

	// create a new Random Number Generator
	RandomNumber rand = new RandomNumber(Seed);
	
	for(int i=0;i<N;i++) {
	    numbers[i]=rand.nextRand(); // draw random numbers
	}

	// Calculate the average
	double avg=0;
	for (int j=0; j<N; j++) {
	    avg+=numbers[j];
	}
	avg/=(double)N;

	System.out.println(" The Mean is: "+avg);
    }
}
