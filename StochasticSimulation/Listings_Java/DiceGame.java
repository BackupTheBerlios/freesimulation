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
/** Application to show the switch-case statement */
public class DiceGame{
    public static void main (String[] args){
	int face;
	for (int i=1; i<7; i++){
	    face = 1 + (int) (Math.random()*6);
	    switch (face%6) { 
	    case 0:   // print 6 if the remainder is zero
		System.out.println("Face equals 6");
		break;
	    default: 
		System.out.println("Face equals 1,2,3,4 or 5");
		break;
	    }
	}
    }
}
