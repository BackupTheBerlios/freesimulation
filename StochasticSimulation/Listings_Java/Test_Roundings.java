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
public class Test_Roundings {
  public static void main(String[] args) {
    double a1=7.49;
    double b1=7.5;
    double c1=7.51;
    
    double a2=-7.49;
    double b2=-7.5;
    double c2=-7.51;
    
    System.out.println("round: "+Math.round(a1)+" "+a1);
    System.out.println("round: "+Math.round(a2)+" "+a2);
    System.out.println("round: "+Math.round(b1)+" "+b1);
    System.out.println("round: "+Math.round(b2)+" "+b2);
    System.out.println("round: "+Math.round(c1)+" "+c1);
    System.out.println("round: "+Math.round(c2)+" "+c2);
    
    System.out.println("ceil: "+Math.ceil(a1)+" "+a1);
    System.out.println("ceil: "+Math.ceil(a2)+" "+a2);
    System.out.println("ceil: "+Math.ceil(b1)+" "+b1);
    System.out.println("ceil: "+Math.ceil(b2)+" "+b2);
    System.out.println("ceil: "+Math.ceil(c1)+" "+c1);
    System.out.println("ceil: "+Math.ceil(c2)+" "+c2);
    
    System.out.println("floor: "+Math.floor(a1)+" "+a1);
    System.out.println("floor: "+Math.floor(a2)+" "+a2);
    System.out.println("floor: "+Math.floor(b1)+" "+b1);
    System.out.println("floor: "+Math.floor(b2)+" "+b2);
    System.out.println("floor: "+Math.floor(c1)+" "+c1);
    System.out.println("floor: "+Math.floor(c2)+" "+c2);

    System.out.println("rint: "+Math.rint(a1)+" "+a1);
    System.out.println("rint: "+Math.rint(a2)+" "+a2);
    System.out.println("rint: "+Math.rint(b1)+" "+b1);
    System.out.println("rint: "+Math.rint(b2)+" "+b2);
    System.out.println("rint: "+Math.rint(c1)+" "+c1);
    System.out.println("rint: "+Math.rint(c2)+" "+c2);
  }
}
