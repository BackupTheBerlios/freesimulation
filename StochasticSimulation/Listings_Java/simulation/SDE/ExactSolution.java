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
package simulation.SDE;

/**
 * ExactSolution.java
 *
 *
 * Created: Sat Jul  3 21:37:09 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import java.util.*;

/** Define the exact solution to a SDE if available. You supply the
    time argument and the initial value and it returns the value 
    at that time. You can
    implement this interface for SDEs, which have an exact solution. */
public interface ExactSolution  {
    
    public double SDEexact(double time, double ValueAtZero) ;
    
} // ExactSolution
