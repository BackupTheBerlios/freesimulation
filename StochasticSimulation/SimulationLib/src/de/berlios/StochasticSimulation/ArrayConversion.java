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
package de.berlios.StochasticSimulation;

/**
 * Conversion Routines for Arrays.
 *
 *
 *
 * @author Peter Biechele
 * @version $Revision: 1.1 $ $Date: 2004/03/20 17:50:27 $
 */
public class ArrayConversion {
    
    public ArrayConversion() {
    }
    
    public static void main(String[] args) {        
    }

    /** Convert a 1D integer array to a 1D double array. */
    public static double[] int2Double1D(int[] intArray) {
        int N=intArray.length;
        double[] doubleArray = new double[N];

        for (int i=0; i<N; i++) {
            doubleArray[i]=(double)intArray[i]; }
        return doubleArray;
    }
    /** Convert a SQUARE 2D integer array to a 2D double array. */
    public static double[][] int2Double2D(int[][] intArray) {
        int N=intArray[0].length;
        double[][] doubleArray = new double[N][N];

        for (int i=0; i<N; i++) {
            doubleArray[i]=ArrayConversion.int2Double1D(intArray[i]); }
        return doubleArray;
    }


    /** Convert a 1D double array to a 1D integer array. 
        The conversion is just the casting operator. */
    public static int[] double2Int1D(double[] doubleArray) {
        int N=doubleArray.length;
        int[] intArray = new int[N];

        for (int i=0; i<N; i++) {
            intArray[i]=(int)doubleArray[i]; }
        return intArray;
    }

    /** Convert a SQUARE 2D double array to a 2D integer array. 
        The conversion is just the casting operator. */
    public static int[][] double2Int2D(double[][] doubleArray) {
        int N=doubleArray.length;
        int[][] intArray = new int[N][N];

        for (int i=0; i<N; i++) {
            intArray[i]=ArrayConversion.double2Int1D(doubleArray[i]); }
        return intArray;
    }

} // ArrayConversion
