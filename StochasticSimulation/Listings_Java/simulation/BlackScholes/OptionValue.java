package simulation.BlackScholes;

/**
 * OptionValue.java
 *
 *
 * Created: Thu Jun 24 14:50:30 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import simulation.*;
import simulation.BlackScholes.*;
// import ptolemy.plot.*;

public class OptionValue  {
    
    // The option to be used for simulation/computation
    private Option option;
    
    /** The value and the greeks of the option:
        values[time][greek number][asset] */
    private double[][][] values;
    /** The number of different computed values:
        value of option(0), theta(1), delta(2), gamma(3) .*/
    private final int NO_VALUES = 4;

    // the discretization parameters
    // time:
    private double deltaT;
    private int timeSteps;
    // asset price:
    private double deltaS;
    private int assetSteps;
    
    // variables used in this class
    private boolean computeFLAG;

    ///////////////////////////////////////////////////////

    /** Instantiate with  default parameters. */
    public OptionValue() {        
        this(new EuropeanCall()); }
    public OptionValue(Option option) {        
        this(option, 200, 50); }
    public OptionValue(Option option, int tSteps, int sSteps) {
        this.option=option;
        timeSteps=tSteps;
        assetSteps=sSteps;
        computeDeltas();
        computeFLAG=false;
    }
    public OptionValue(Option option, double deltaT, double deltaS) {
        this.option=option;
        this.deltaT=deltaT;
        this.deltaS=deltaS;
        computeSteps();
        computeFLAG=false;
    }
    /** With this constructor you have to take care, that the deltas and the
        steps are correct!! */
    public OptionValue(Option option, int tSteps, int sSteps, double deltaT, double deltaS) {
        this.option=option;
        timeSteps=tSteps;
        assetSteps=sSteps;
        this.deltaT=deltaT;
        this.deltaS=deltaS;
        computeFLAG=false;
    }

    ///////////////////////////////////////////////////////
    public Option getOption() {
        return option; }
    public double getDeltaT() {
        return deltaT; }
    public double getDeltaS() {
        return deltaS; }
    public int getTimeSteps() {
        return timeSteps; }
    public int getAssetSteps() {
        return assetSteps; }
    /** Return the value of the option and the greeks. 
        format: array[Greek Number][asset price] */
    public double[][] getValue(double time) {
        double[][] result = new double[NO_VALUES][assetSteps];
        int dummyInt = (int)(time/deltaT);
        
        if (computeFLAG) {
            System.arraycopy(values[dummyInt],0,result,0,assetSteps*NO_VALUES); }
        else {
            System.err.println(" ERROR: No computation done so far !"); }
        return result;
    }

    ///////////////////////////////////////////////////////
    public void setOption(Option param) {
        this.option=param; }
    public void setDeltaT(double param) {
        this.deltaT=param;
        computeSteps();
    }
    public void setDeltaS(double param) {
        this.deltaS=param; 
        computeSteps(); 
    }
    public void setTimeSteps(int param) {
        this.timeSteps=param; 
        computeDeltas(); 
    }
    public void setAssetSteps(int param) {
        this.assetSteps=param; 
        computeDeltas(); 
    }


    ///////////////////////////////////////////////////////
    private void computeSteps() {
        if (option!=null) {
            timeSteps=(int)(option.getMaturityTime()/deltaT); 
            assetSteps=(int)(2*option.getStrike()/deltaS);
        }        
    }

    private void computeDeltas() {
        if (option!=null) {
            deltaT=option.getMaturityTime()/timeSteps;
            deltaS=2*option.getStrike()/assetSteps;
        }
    }
    ////////////////////////////////////////////////////////

    /** Instantiate the array for the result. Memory needed is:
        4 * timeSteps * assetSteps */
    private void instantiate() {
        if (option != null && timeSteps>0 && assetSteps>0) {
            values=null;
            values = new double[timeSteps+1][NO_VALUES][assetSteps];
        }
    }
    
    // used for the progress bar
    private boolean pBarFLAG;
    private datarep.common.ProgressBar progressBar;

    private void computingExact() {
        double dummy, percent;
        instantiate();

        if (option.existsExactSolution() == true) {
            for (int tInt=0; tInt<timeSteps; tInt++) {
                if (pBarFLAG) {
                    percent=(double)tInt/timeSteps*100;
                    progressBar.setValue((int)percent); 
                    progressBar.repaint();
                }
                for (int aInt=0; aInt<assetSteps; aInt++) {
                    dummy=(timeSteps-tInt-1)*deltaT;
                    values[tInt][0][aInt]=
                        option.exactSolution(aInt*deltaS, dummy); 
                    values[tInt][1][aInt]=
                        option.exactTheta(aInt*deltaS, dummy); 
                    values[tInt][2][aInt]=
                        option.exactDelta(aInt*deltaS, dummy); 
                    values[tInt][3][aInt]=
                        option.exactGamma(aInt*deltaS, dummy); 
                } }
        }
        computeFLAG=true;
     }

    /** Use a progress bar from Simplicity for Java and compute the exact solution. */
    public void computeExact(datarep.common.ProgressBar pBar) {
        progressBar=pBar;
        pBar.setValue(0);
        pBarFLAG=true;
        computingExact();
        pBarFLAG = false;            
        pBar.setValue(100);
    }
    /** Compute the exact value of the option and the exact greeks.
        No progress bar used. */
    public void computeExact() {
        pBarFLAG = false;
        computingExact();
    }

    /** <p>Compute the value of the option and the greeks using
        a finite difference approximation: Use an explicit scheme. </p>
        <p>!!!!! ATTENTION:</p>
        The following equation has to hold for stability:
        deltaT <= deltaS*deltaS / (2*a(S,t)) : for all S,t
        with a(S,t) = 0.5*sigma*sigma*Asset(t)*Asset(t)
        for Black-Scholes.
    */
    private void computingFiniteDifferences() {
        double strike, sigma, rate, dividend, endTime;
        double[] asset;
        double sMax, constraint, percent;
        instantiate();

        strike=option.getStrike();
        sigma=option.getVolatility();
        rate=option.getInterestRate();
        dividend=option.getDividend();
        endTime=option.getMaturityTime();

        // instantiate necessary temporary variables for simulation
        asset=new double[assetSteps];

        // initial (final) condition
        for (int i=0; i<assetSteps; i++) {
            asset[i]=i*deltaS;
            values[0][0][i]=option.payoff(asset[i],strike,0); }

        // !!!!! check for the constraint:
        sMax=0;
        for (int i=0; i<assetSteps; i++) {
            if (asset[i]>sMax) sMax=asset[i]; }
        constraint= (deltaS*deltaS)/(sigma*sigma*sMax*sMax);
        System.err.println("dt: "+deltaT+" <= constraint value: "+constraint);
        if (deltaT>constraint) {
            System.err.println("Constraint for explicit finite difference "
                               +"not satisfied, exiting !!");
            System.exit(0); }

        // finite difference time loop
        /** Attention: the time is going backwards here, meaning that
            j*deltaT is the time backwards from the maturity date.
            In most equations you have the time t giving the physical
            time advancing forwards. So most of the time you have
            " t_physical = endTime - t_simulation " */              
        for (int j=1; j<timeSteps+1; j++) {
            // the progress bar if desired
            if (pBarFLAG) {
                percent=(double)j/timeSteps*100;
                progressBar.setValue((int)percent); 
                progressBar.repaint();
            }
            deltaGamma(j);
            for (int i=1; i<assetSteps-1; i++) {
                values[j][0][i]=values[j-1][0][i]+ deltaT*
                    (0.5*sigma*sigma*asset[i]*asset[i]*values[j-1][3][i]
                     + (rate-dividend)*asset[i]*values[j-1][2][i] 
                     - rate*values[j-1][0][i] );
            }
            // boundary conditions !!!!
            option.boundaryConditions(endTime-j*deltaT,values[j][0],asset);

            for (int i=0; i<assetSteps; i++) {
                values[j][1][i]=(values[j-1][0][i]-values[j][0][i])/deltaT; }
            // American or European options ?
            option.freeBoundaries(values[j-1][0], values[j][0], 
                                  assetSteps, asset, endTime-j*deltaT);
        }
        deltaGamma(timeSteps);
        computeFLAG=true;
    }
    /** Use a progress bar from Simplicity for Java and compute a 
        finite difference  solution. */
    public void computeFiniteDifferences(datarep.common.ProgressBar pBar) {
        progressBar=pBar;
        pBar.setValue(0);
        pBarFLAG=true;
        computingFiniteDifferences();
        pBarFLAG = false;            
        pBar.setValue(100);
    }
    /** Do not use a progress bar and compute a 
        finite difference solution. */
    public void computeFiniteDifferences() {
        pBarFLAG = false;
        computingFiniteDifferences();
    }

    /** Convenience routine for finite difference computation. */
    private void deltaGamma(int timeIndex) {
        for (int i=1; i<assetSteps-1; i++) {
            values[timeIndex-1][2][i]=
                (values[timeIndex-1][0][i+1]-values[timeIndex-1][0][i-1])
                / (2*deltaS);
            values[timeIndex-1][3][i]=
                (values[timeIndex-1][0][i+1]-2*values[timeIndex-1][0][i]
                 +values[timeIndex-1][0][i-1])
                / (deltaS*deltaS);
        }        
    }
        
    ////////////////////////////////////////////////////////
    /** Plot the option value in a 2D plot (V(S) for a fixed time t). */
    public void plotOptionValue(ptolemy.plot.Plot plot, double time) {
        plotOptionValue(plot, 0, time); }
    /** Plot the option value in a 2D plot (V(S) for a fixed time t). */
    public void plotOptionValue(ptolemy.plot.Plot plot,
                                int plotNo, double time) {
        plotOption(0, plot, plotNo, time); }

    /** Plot the option theta in a 2D plot for a fixed time t. */
    public void plotOptionTheta(ptolemy.plot.Plot plot, double time) {
        plotOptionTheta(plot, 0, time); }
    /** Plot the option value in a 2D plot (V(S) for a fixed time t). */
    public void plotOptionTheta(ptolemy.plot.Plot plot,
                                int plotNo, double time) {
        plotOption(1, plot, plotNo, time); }
    
    /** Plot the option delta in a 2D plot for a fixed time t. */
    public void plotOptionDelta(ptolemy.plot.Plot plot, double time) {
        plotOptionDelta(plot, 0, time); }
    /** Plot the option value in a 2D plot (V(S) for a fixed time t). */
    public void plotOptionDelta(ptolemy.plot.Plot plot,
                                int plotNo, double time) {
        plotOption(2, plot, plotNo, time); }

    /** Plot the option gamma in a 2D plot for a fixed time t. */
    public void plotOptionGamma(ptolemy.plot.Plot plot, double time) {
        plotOptionGamma(plot, 0, time); }
    /** Plot the option value in a 2D plot (V(S) for a fixed time t). */
    public void plotOptionGamma(ptolemy.plot.Plot plot,
                                int plotNo, double time) {
        plotOption(3, plot, plotNo, time); }

    private void plotOption(int greek, ptolemy.plot.Plot plot,
                            int plotNo, double time) {
        int tInt;
        double[] array=new double[assetSteps];
       
        if (computeFLAG) {
            tInt=(int)(time/deltaT);
            if (tInt > timeSteps || deltaS==0 ) {
                System.out.println(" ERROR ! time to large ! ");
                System.out.println(" time: "+time+" timestep: "+tInt);
                System.exit(1); }
            
            for (int asset=0; asset<assetSteps; asset++) {
                array[asset]=asset*deltaS; }
            Plotting.plot2D(plot, plotNo, array, values[tInt][greek]); } 
        else {
            System.err.println(" NO computation done so far !!"); }
    }

} // OptionValue
