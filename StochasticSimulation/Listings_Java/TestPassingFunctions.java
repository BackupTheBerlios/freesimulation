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
 * TestPassingFunctions.java
 */
public class TestPassingFunctions {
    public static void main (String[] args) {
        SquareFunc f = new SquareFunc();
        int points = 10;
        double integral = integrate(f,0,1,points);
        System.out.println(" The integral is : "+integral);
    }

    private static double integrate (function f, double a, double b, int p) {
        double integral=0;
        double dx = (b-a)/p;
        
        for (double x=a+dx/2; x<b; x+=dx) {
            integral+=f.f(x);
        }
        return integral*dx;
    }
} // TestPassingFunctions


