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
/** A simple and bad random number generator */

public class RandomNumber {
    /** These are the constants, defining (quality of) 
	the generator */
    private int a=65539;
    private long M=(int)Math.pow(2,31)-1;
    /** The seed has to be a class variable */
    private long R1;
    
    /** Constructor sets the seed of the generator */
    RandomNumber(int Seed) {
	R1=Seed;
    }    
    
    /** here we draw the next random number */
    public double nextRand() {
	R1=(long)(a*R1)%M;
	return (double)R1/(double)M;
    }

    /** set the parameters of the generator */
    public void setParameters(int a, long M) {
        this.a=a;
        this.M=M;
    }
    /** Set the seed */
    public void setSeed(long seed) {
        this.R1=seed; 
    }
    /** return the parameters */
    public int getA() {
        return this.a; }
    public long getM() {
        return this.M; }
    /** get the seed */
    public long getSeed() {
        return this.R1; }
}
