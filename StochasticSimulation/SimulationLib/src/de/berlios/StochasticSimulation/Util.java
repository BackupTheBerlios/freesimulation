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
 * Util.java
 *
 *
 * Created: Thu Jun 17 17:00:12 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

/** Some small methods to make life easier in Java. */
public class Util {
    
    /** Testing some features of this class. */
    public static void main(String[] args) {        
    }

    /** Converts a String to a double primitive variable. */
    public static double stringToDouble(String s) {
        return string2Double(s); }
    /** Converts a String to a double primitive variable. */
    public static double string2Double(String s) {
        // first possibility : (more efficient)
        return Double.valueOf(s).doubleValue();

        // second possibility : 
        // return new Double(s).doubleValue();
    }

    /** Converts a String to a float primitive variable. */
    public static float stringToFloat(String s) {
        return string2Float(s); }    
    /** Converts a String to a float primitive variable. */
    public static float string2Float(String s) {
        // first possibility : (more efficient)
        return Float.valueOf(s).floatValue();

        // second possibility : 
        // return new Float(s).floatValue();
    }

    /** Converts a String to a integer primitive variable. */
    public static int stringToInt(String s) {
        return string2Int(s); }    
    /** Converts a String to a integer primitive variable. */
    public static int string2Int(String s) {
        // first possibility : 
        // return Integer.valueOf(s).intValue();

        // second possibility : 
        // return new Integer(s).intValue();

        // third possibility : (most efficient)
        String myString = s.trim();
        return Integer.parseInt(myString);
    }
    
    /** Converts a String to a long primitive variable. */
    public static long stringToLong(String s) {
        return string2Long(s); }    
    /** Converts a String to a long primitive variable. */
    public static long string2Long(String s) {
        // first possibility : 
        // return Long.valueOf(s).longValue();

        // second possibility : 
        // return new Long(s).longValue();

        // third possibility : (most efficient)
        String myString = s.trim();
        return Long.parseLong(myString);
    }

    /** Converts a double primitive variable to a String. */
    public static String doubleToString(double x) {
        return double2String(x); }
    /** Converts a double primitive variable to a String. */
    public static String double2String(double x) {
        return String.valueOf(x);
        // OR:
        //     Double.toString(x);
    }

    /** Converts a float primitive variable to a String. */
    public static String floatToString(float x) {
        return float2String(x); }
    /** Converts a float primitive variable to a String. */
    public static String float2String(float x) {
        return String.valueOf(x);
        // OR:
        //     Float.toString(x);
    }

    /** Converts a int primitive variable to a String. */
    public static String intToString(int x) {
        return int2String(x); }
    /** Converts a int primitive variable to a String. */
    public static String int2String(int x) {
        return String.valueOf(x);
        // OR:
        //     Integer.toString(x);
    }

    /** Converts a long primitive variable to a String. */
    public static String longToString(long x) {
        return long2String(x); }
    /** Converts a long primitive variable to a String. */
    public static String long2String(long x) {
        return String.valueOf(x);
        // OR:
        //     Long.toString(x);
    }

} // Util
