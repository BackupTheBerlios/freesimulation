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
 * DogFlea.java
 *
 *
 * Created: 21 Mar 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;
import ptolemy.plot.*;
import de.berlios.StochasticSimulation.*;

public class DogFleaThreads extends Applet {
        
    public int Nthreads = 4; // how many threads = initial conditions ?
    public final int NCPUS = 2; // How many CPUs are available to be used
    // ATTENTION: Nthreads has to be divisible by NCPUS

    private int N = 1000;  // number of fleas
    private int steps = 100000; // How many time steps to be done

    private DogFleaThreads prg;
    private Thread current;

    public DogFleaThreads() {
    }
    
    public static void main(String[] args) {
	Frame f = new Frame("Dog-Flea Model");
	f.setSize(600,600);
	DogFleaThreads prg=new DogFleaThreads();
	f.add(prg);
	f.add(new Label(" I am calculating ...."));
	f.show();
        // Close Window event
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
                { System.exit(0);}
        });
	prg.init();
	prg.start();
    }

    public void init() {
        if (prg==null) {
            prg = new DogFleaThreads();
        }

        current = Thread.currentThread();
	current.setPriority(Thread.MAX_PRIORITY);

        // Ensure Round Robin Scheduling
        //SimpleScheduler ss = new SimpleScheduler(100);
        //ss.start();
    }
        
    public void start() {
	int n0; // initial number of fleas on dog 1
	int stepsize = N/(Nthreads-1);
	Thread[] job = new Thread[NCPUS];

	// start exactly NCPUS threads, then wait for them to finish 
	// and start again NCPUS threads
	int prio=Thread.currentThread().getPriority()-1;
	int count=0;	
	for (n0=0; n0<=N; n0+=stepsize) {
	    job[count]=new Thread( new DogFleaCalc(N,n0,steps) );
	    job[count].setPriority(prio);
            job[count].start();
	    count++;
	    if (count==NCPUS) {
                // start all threads
 		for (int j=0; j<NCPUS; j++) {
                    //        job[j].start();
                }
		System.out.println(" Waiting !");
		// wait for all threads to be done
		for (int j=0; j<NCPUS; j++) {
                    try {
                        job[j].join(); 
                    } catch (InterruptedException e) {}
		}
		System.out.println(" Ready !");
		count=0;
            }
        }
	System.exit(0);
    }
    public void stop() { }
} // DogFlea


