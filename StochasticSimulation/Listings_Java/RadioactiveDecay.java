import java.applet.Applet;
/** A simple Radioactive decay simulation 
    with output to the command line */
public class RadioactiveDecay extends Applet {
    private int N_0=1000;
    private double t_end=300;
    private double decay_const=0.02;
    private double dt=1;
    private double prob=decay_const*dt;
    public int[] N_simu,N_exact;
    public java.util.Random rand = new java.util.Random();
   
    /** empty constructor */
    void RadioactiveDecay() {}

    /** use a main method to use it as application OR applet */
    public static void main(String[] args) {
        RadioactiveDecay decay = new RadioactiveDecay();
	decay.init();
    }

    /** The actual program */
    public void init() {
        int steps, N_save, N;
        double jump;

	steps=(int)(t_end/dt)+1;
	N_simu=new int[steps];
	N_exact=new int[steps];
	N_simu[0]=N_0;
	N_exact[0]=N_0;

	N_save=N_0;
	N=N_0;
	for (int t=0; t<steps-1; t++) {
	    for (int i=0; i<N_save; i++) {
		jump=rand.nextDouble();
		if (jump < prob) N--; }
	    N_save=N;
	    N_simu[t+1]=N;
	    N_exact[t+1]=(int)(N_0*Math.exp(-decay_const*t));}

	for (int t=0; t<t_end; t++) {
	    System.out.println(" Time "+t+" : "+N_simu[t]+"  "+
			       N_exact[t]+" :exact"); }
    }
}
