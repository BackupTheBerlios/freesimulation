package simulation.BlackScholes;

/**
 * Option.java
 *
 *
 * Created: Sun Jun 13 13:34:17 1999
 *
 * @author Peter Biechele
 * @version 1.1
 */

/**
   An abstract class, which imlements the payoff function and the 
   exact solution of a special option. </p>
   This class should be subclassed to create your own special option
   (e.g. European Call option). </p>
   This class just supplies the basic routines necessary for all options
   like fixing all parameters for a Black Scholes world option. </p>
   It also allows to read and set these parameters: Volatility,
   interest rate, dividend rate and the strike (exercise) price.
</p> */
public abstract class Option implements ExactSolutionFctn, PayoffFunction  {

    /** Fields/parameters of the option. */
    protected double maturityTime;
    protected double volatility;
    protected double interestRate;
    protected double dividend;
    protected double strike;

    /** variables used in this class. */
    protected double vol2half;

    
    /** Default values for the constructor. */
    public Option() {
        this(1,0.2,0.03,0.06,100); }

    /** Construct an option with the given parameters. */
    public Option(double endTime, double volatility, double dividend,
                        double interestRate, double strike) {
        this.maturityTime=endTime;
        this.volatility=volatility;
        this.interestRate=interestRate;
        this.dividend=dividend;
        this.strike=strike;
        // derived constant
        this.vol2half=0.5*volatility*volatility;
    }

    // the read methods
    public double getVolatility() {
        return this.volatility; }
    public double getStrike() {
        return this.strike; }
    public double getInterestRate() {
        return this.interestRate; }
    public double getDividend() {
        return this.dividend; }

    // the set methods
    public void setVolatility(double param) {
        this.volatility=param; 
        // derived constant
        this.vol2half=0.5*volatility*volatility; }
    public void setStrike(double param) {
        this.strike=param; }
    public void setInterestRate(double param) {
        this.interestRate=param; }
    public void setDividend(double param) {
        this.dividend=param; }


    /** The free boundary conditions are implemented here. </p> 
        You have to override this method in one of the subclasses to
        get an american option or even different behaviour. This
        implementation is for European options. */
    public void freeBoundaries(double[] Vold, double[] Vnew, int noAssetSteps,
                               double[] asset, double time) {
        System.arraycopy(Vnew,0,Vold,0,noAssetSteps); // Vold=Vnew        
    }

    /** returns the exact solution for the option */
    public abstract double exactSolution(double assetPrice, double time);
    /** returns the payoff of the option. */
    public abstract double payoff(double asset, double strike, double time);
    /** Applies the boundary conditions at a certain time, for a
        given option value and asset value. Here it is, where
        the difference between the american and european options enter. */
    public abstract void boundaryConditions(double time, 
                                            double[] Value, double[] asset);

} // Option
