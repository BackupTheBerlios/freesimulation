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
        this(1,0.2,0.03,0.06,100);
    }
    public Option(double endTime, double volatility, double dividend,
                        double interestRate, double strike) {
        this.maturityTime=endTime;
        this.volatility=volatility;
        this.interestRate=interestRate;
        this.dividend=dividend;
        this.strike=strike;
        this.vol2half=0.5*volatility*volatility;
    }

    public double getVolatility() {
        return this.volatility; }
    public double getStrike() {
        return this.strike; }
    public double getInterestRate() {
        return this.interestRate; }
    public double getDividend() {
        return this.dividend; }

    /** returns the exact solution for the option */
    public abstract double exactSolution(double assetPrice, double time);
    public abstract double payoff(double asset, double strike);
    public abstract void boundaryConditions(double time, 
                                            double[] Value, double[] asset);

} // Option
