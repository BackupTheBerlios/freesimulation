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
public class Data {
  double[] data;  

  // The constructor: called by : new Data(array);
  Data(double[] array) {
    data = new double[100];
    for (int i=0; i<array.length; i++) {
      data[i]=array[i]; }
  }

  // The method for computing the mean (average)
  double average() {
    double sum=0;

    for (int i=0; i<data.length; i++) {
      sum = sum + data[i]; }
    sum = sum / data.length;
    return sum;
  }
}
