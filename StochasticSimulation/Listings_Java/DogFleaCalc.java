import java.util.*;
import simulation.*;

/**
 * DogFleaCalc.java
 *
 *
 * Created: Wed Mar 17 12:37:06 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */
public class DogFleaCalc extends Thread {
    int na, nb; // Output values
    int n0, N, steps;

    private int[] flea; // to which dog belongs the flea ? (1 or 0)   
    private Random rand = new Random();

    DogFleaCalc(int N, int n0, int steps) {
	this.flea = new int[N];
	this.n0 = n0;
	this.N = N;
	this.steps = steps;
    }

    public void run() {
	int choose;
	boolean connect=false;
	
	initial(n0);
	na = calck();
	
	System.out.println("at time s=0:  "+na); 
	for (int s=1; s<steps; s++) {
	    choose=Distribution.nextInteger(rand,N-1);
	    flea[choose]=1-flea[choose];
	    na = calck();
	} 
	nb=N-na;
	System.out.println("at time s="+steps+":  "+na); 
    }
    
    int calck() {
	int sum=0;
	for (int i=0; i<N; i++) {
	    sum+=flea[i]; }
	return sum;
    }
    
    void initial(int n0) {
	for (int i=0; i<N; i++) {
	    if (i<n0) flea[i]=1;
	}
    }
} // DogFleaCalc
