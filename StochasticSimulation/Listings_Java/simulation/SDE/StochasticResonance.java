package simulation.SDE;

/**
 * StochasticResonance.java
 *
 *
 * Created: Tue Jun  1 10:50:30 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

/**
 * <p>
 * A class implementing the SDEfunction interface for solving
 * a stochastic differential equation (SDE).
 * <p>
 * You can access all parameters by using the standard bean
 * method names. For example to get a parameter "test" you simply
 * call the method getTest() of the object and to set a parameter
 * "sigma" you call setSigma(value).
 * <p>
 * Here a process representing exhibiting stochastic resonance 
 * is defined. The underlying system is an overdamped anharmonic 
 * oscillator.
 * <p>
 * dX = [ X-X^3+U1/(4 deltaU) cos(omegas t) ] dt + (diffusion)^0.5 dW
 * <p>
 */
public class StochasticResonance implements SDEfunction {
    
    private double U1;
    private double deltaU;
    private double D;
    private double omegaS;

    public StochasticResonance() { 
        this(1.0, 1.0, 1.0, 1.0); }
    public StochasticResonance(double param1, double param2, 
                               double param3, double param4) {     
        U1=param1;
        deltaU=param2; 
        D=param3; 
        omegaS=param4; }
    
    public double getU1() {
        return this.U1; }
    public double getDeltaU() {
        return this.deltaU; }
    public double getD() {
        return this.D; }
    public double getOmegaS() {
        return this.omegaS; }

    public void setU1(double param) {
        this.U1=param; }
    public void setDeltaU(double param) {
        this.deltaU=param; }
    public void setD(double param) {
        this.D=param; }
    public void setOmegaS(double param) {
        this.omegaS=param; }

    public double[] SDEterms(double x, double t) {
        double[] retVal = new double[2];

        retVal[0] = x-x*x*x+U1/(4*deltaU)*Math.cos(omegaS*t);
        retVal[1] = Math.sqrt(D);
        return retVal;
    }
    
} // StochasticResonance
