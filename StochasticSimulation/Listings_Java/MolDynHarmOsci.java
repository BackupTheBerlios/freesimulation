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
 * MolDynHarmOsci.java
 *
 * Molecular Dynamics Simulation of a 1D harmonic Oscillator
 * use: mass = 1 and omega^2 = 1 => period = 2 pi / omega = 2 pi
 * therefore: total energy is given by: E = 0.5 (v^2 + x^2)
 * the force is given by: F = -grad V = -d/dx 1/2 (x^2) = -x
 *
 * Created: Wed Dec  2 16:12:25 1998
 *
 * @author Peter Biechele
 * @version
 */

public class MolDynHarmOsci {
    /* method: 0  Euler
               1  Verlet
	       2  Velocity Verlet
	       3  Beeman
    */
    final int method = 0;
    
    final double endtime = 2*Math.PI;
    double deltat,deltatsq,deltatstart=0.01,deltatend=0.5,deltatstep=0.05;

    // position and velocity variables
    int count;
    double energy;
    double x,v,a,t,f;
    double xnew,vnew,anew,fnew,xold,vold,aold,fold;
    double[] exactx = new double[2];
    double maxdeviation[],dummy;

    // the actual simulation
    public MolDynHarmOsci() {


	// check many different dt's
	int dtSteps=(int)((deltatend-deltatstart)/deltatstep)+1;
	maxdeviation=new double[dtSteps];
	count=0;
	for (deltat=deltatstart; deltat<=deltatend; deltat+=deltatstep) {
	    deltatsq=deltat*deltat;
	    maxdeviation[count]=0;
	    // initial conditions
	    xold=1; vold=0; aold=-1; 
	    fold=force(xold);
	    x=xold+deltat*vold+0.5*deltatsq*fold; 
	    v=vold+deltat*fold;
	    a=force(x); 
	    f=a;
	    // System.out.println(x+" "+exact(deltat)[0]);	    
	    // time loop
	    for (t=2*deltat; t<=endtime; t+=deltat) {
		switch (method){ 
		case 0: // Euler Method
		    f = force(x);
		    x = x+deltat*v+0.5*deltatsq*f;
		    v = v+deltat*f;
		    break;
		case 1: // Verlet Method
		    f = force(x);
		    xnew = 2*x-xold+deltatsq*f;
		    vnew = (xnew-xold)/(2*deltat);
		    xold=x; vold=v;
		    x=xnew; v=vnew;
		    break;
		case 2: // Velocity Verlet Method
		    f = force(x);
		    xnew = x+deltat*v+0.5*deltatsq*a;
		    anew=force(xnew);
		    vnew = v+0.5*deltat*(anew+a);
		    xold=x; vold=v; aold=a;
		    x=xnew; v=vnew; a=anew;
		    break;
		case 3: // Beeman Method
		    xnew = x+deltat*v+2./3.*deltatsq*f-1./6.*deltatsq*fold;
		    fnew=force(xnew);
		    vnew = v+1./3.*deltat*fnew+5./6.*deltat*f-1./6.*deltat*fold;
		    xold=x; vold=v; aold=a; fold=f;
		    x=xnew; v=vnew; a=anew; f=fnew;
		    break;
		}
		energy=0.5*(x*x+v*v);
		// System.out.println(energy);
		// calc maximum deviation from exact result
		exactx=exact(t);
		dummy=Math.abs(exactx[0]-x);
		if (maxdeviation[count] < dummy) maxdeviation[count]=dummy;
		// System.out.println(x+" "+exactx[0]);	    
	    } // time loop	    
	    System.out.println(" Maximum x deviation for dt="+deltat+
			       " was : "+maxdeviation[count]+
			       " , energy drift: "+(energy-0.5));	
	    count++;
	} // dt loop

    } // end constructor
    
    double force(double x) {
	return -x;
    }
    
    double[] exact (double time) {
	double[] result = new double[2];
	result[0]=Math.cos(time);
	result[1]=-Math.sin(time);
	return result;
    }

    public static void main(String[] args) {
	new MolDynHarmOsci();
    }
    
} // MolDynHarmOsci
