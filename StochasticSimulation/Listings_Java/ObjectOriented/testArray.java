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

/**
 * testArray.java
 *
 *
 * Created: Fri May 28 11:54:35 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

public class testArray {
    
    public static void main(String[] args) {
        /* create and instantiate a 2D array */
        double[][] array2;
        array2=new double[10][10];
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                array2[i][j]=j+10*i; } }

        test(array2[1]); // call the method with a row of a 2D array
        // test(array2[][1]); // This is WRONG !!!!
    }
    
    /** The method prints the 1D array argument to 
        the standard output to check the result. */
    static void test(double[] array1) {
        for (int i=0; i<10; i++) {
            System.out.println(i+" "+array1[i]); }
    }

} // testArray
