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
/* <applet archive="SDE.jar" code="SDE.class" 
   width=900 height=600 > </applet>
 */

/**
 * SDE.java
 *
 * Euler Method for SDEs
 *
 * Created: Mon May 10 08:12:15 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Random;

import simulation.*;
import simulation.SDE.*;
import VisualNumerics.math.*;
import ptolemy.plot.*;

/**
 * Stochastic Differential Equations <p>
 *
 * A GUI for simulating SDEs of different kinds.
 * 
 *
 */
public class SDE extends Applet 
    implements ActionListener , Runnable {

    private void output() {
        double std, yLow, yHigh;

        // Output
        realPlot.setTitle("One Realization");
        realPlot.setXLabel("time t");
        realPlot.setYLabel("x(t)");
        meanPlot.setTitle("Mean <x(t)>");
        meanPlot.setXLabel("time t");
        meanPlot.setYLabel("<x(t)>");
        boolean connect=false;
        for (int k=0; k<nstep; k++) {
            // compute standard deviation of mean
            std=Math.sqrt((xstd[k]-Math.pow(xpos[k],2))/nReal);
            yLow=xpos[k]-std;
            yHigh=xpos[k]+std;
            meanPlot.addPointWithErrorBars(color,deltat*k,xpos[k],
                                           yLow,yHigh,connect);
            realPlot.addPoint(color,deltat*k,xreal[k],connect);
            if (connect==false) connect=true;
        }
        realPlot.repaint();
        meanPlot.repaint();
        color++;
        if (color>10) {
            color=0; }
    }
    
    // The three possible parameters for the different SDEs
    private static double param1 = 1;
    private static double param2 = 1;
    private static double param3 = 1.0;
    private static double param4 = 1.0;
    private static SDEfunction process = new OrnsteinUhlenbeck();;

    private static double xstart = 1;
    private static double tend = 5;
    //    public int istep = 4;
    private static double deltat = 0.1;
    private static int nReal = 1000;
    private static double tstart = 0;

    private static double t,x,normal,sigma,muconst;
    private static int nstep = (int) (tend/deltat);
    private static double[] xpos = new double[nstep];
    private static double[] xstd = new double[nstep];
    private static double[] xfour = new double[nstep];
    private static double[] xreal = new double[nstep];
    private static Random rand;

    private void compute() {
        double dW;
        double[] terms = new double[2];
        
        //  for (int is=1; is<=istep; is++) {
        // nstep = tend/deltat[is];
        //}

        border4.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        
        for (int i=1; i<nstep; i++) {
            xpos[i]=0;
            xstd[i]=0; }
        xpos[0]=xstart;
        xreal[0]=xstart;
        xstd[0]=0;

        // realization loop
        rand = new Random();
        for (int j=0; j<nReal; j++) {
            t=tstart;
            x=xstart;            
            if (threadActive==false) return;
            // time loop
            for (int i=1; i<nstep; i++) {
                t+=deltat;
                /* get the terms:
                   drift    :   a(x,t) = terms[0]
                   diffusion:   b(x,t) = terms[1]  */
                terms = process.SDEterms(x,t);
                dW=Math.sqrt(deltat)*rand.nextGaussian();
                x+= (terms[0]*deltat+terms[1]*dW);
                // store values in arrays for statistics
                xpos[i]+=x;
                xstd[i]+=x*x;
                xfour[i]=x*x*x*x;
                if (j==0) { 
                    xreal[i]=x; }
            }
        } // end realizations
        
        for (int i=1; i<nstep; i++) {
            xpos[i]/=nReal; 
            xstd[i]/=nReal;
            xfour[i]/=nReal; }
        border4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }


    private static Plot meanPlot, realPlot;
    private static TextField textFieldParam1,textFieldParam2,textFieldReal,
        textFieldDT,textFieldTend,textFieldXstart,textFieldParam3,
        textFieldParam4;
    private Choice choiceProcess;
    private Button gobutton = new Button("go");
    private Button stopbutton = new Button("stop");
    private Button clearbutton1 = new Button("clear left plot");
    private Button clearbutton2 = new Button("clear right plot");
    private Button buttonPrintLeft = new Button("print left");
    private Button buttonPrintRight = new Button("print right");
    private Panel grid13 = new Panel(new GridLayout(1,4,5,5));
    private Label tendlabel = new Label("t end",Label.LEFT);
    private Label xstartlabel = new Label("x start",Label.LEFT);
    private Label dtlabel = new Label("delta t",Label.LEFT);
    private Label labelParam1 = new Label("Param1",Label.LEFT);
    private Label labelParam2 = new Label("Param2",Label.LEFT);
    private Label labelParam3 = new Label("Param3",Label.LEFT);
    private Label labelParam4 = new Label("Param4",Label.LEFT);
    private Label labelReal = new Label("N Real",Label.LEFT);
    private Panel gridSouth = new Panel(new GridLayout(2,1,5,5));
    private Panel flow7 = new Panel(new FlowLayout(FlowLayout.LEFT,5,5));
    private Panel flow8 = new Panel(new FlowLayout(FlowLayout.LEFT,5,5));
    private Panel border4 = new Panel(new BorderLayout(5,5));
    private Label labelProcess = new Label("Which SDE ?",Label.LEFT);
    // private static ProgressBar progress = new ProgressBar(0,100);
   
    public void actionPerformed(ActionEvent evt) {
        if ( evt.getSource() == clearbutton1 ) {
            realPlot.clear(true);
            realPlot.repaint();
        }
        if ( evt.getSource() == clearbutton2 ) {
            meanPlot.clear(true);
            meanPlot.repaint();
        }
        if ( evt.getSource() == stopbutton ) {
            if (threadActive==true) {
                threadActive=false;
                border4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                // progress.setValue(0); // progress bar
            }
        }
        if (application==true) {
            if ( evt.getSource() == buttonPrintLeft ) {
                PrintComponent.Dialog(frame,realPlot,"Print Left Plot");
            } 
            if ( evt.getSource() == buttonPrintLeft ) {
                PrintComponent.Dialog(frame,meanPlot,"Print Right Plot");
            } 
        }
        if ( evt.getSource() == gobutton ) {
            if (threadActive==false) {
                // derefernece the old thread to stop it completely !
                calcThread=null;
                calcThread = new Thread(prg);
                param1=Double.valueOf(textFieldParam1.getText()).doubleValue(); 
                param2=Double.valueOf(textFieldParam2.getText()).doubleValue(); 
                param3=Double.valueOf(textFieldParam3.getText()).doubleValue(); 
                param4=Double.valueOf(textFieldParam4.getText()).doubleValue(); 
                nReal=Integer.valueOf(textFieldReal.getText()).intValue();
                deltat=Double.valueOf(textFieldDT.getText()).doubleValue();
                tend=Double.valueOf(textFieldTend.getText()).doubleValue();
                xstart=Double.valueOf(textFieldXstart.getText()).doubleValue();
                nstep = (int) (tend/deltat);
                xpos = new double[nstep];
                xstd = new double[nstep];
                xfour = new double[nstep];
                xreal = new double[nstep];
                // create the desired process
                String dummyString = choiceProcess.getSelectedItem();
                if (dummyString.equals("Mean Reverting Random Walk")) {
                    process = new MeanRevertingRW(param1, param2, param3); }
                else if (dummyString.equals("Ornstein Uhlenbeck Process")) {
                    process = new OrnsteinUhlenbeck(param1, param2); }
                else if (dummyString.equals("Lognormal Random Walk")) {
                    process = new LognormalRW(param1, param2); }
                else if (dummyString.equals("Noise Induced Transition")) {
                    process = new NoiseInducedTransition(param1); }
                else if (dummyString.equals("Stochastic Resonance")) {
                    process = new StochasticResonance
                        (param1, param2, param3, param4); }
                threadActive=true;
                calcThread.start();
            }
        }
    }

    public static Thread calcThread, current;
    private static boolean threadActive = false;  // control thread execution 
    public static int color = 0;

    public static SDE prg;
    public static Frame frame;
    public static boolean application=false;

    public SDE() {         
        this.setLayout(new BorderLayout());

        meanPlot = new ptolemy.plot.Plot();
        realPlot = new ptolemy.plot.Plot();
        choiceProcess = new Choice();

        meanPlot.setLabelFont("Serif-bold-16"); 
        meanPlot.setTitleFont("Serif-bold-24"); 

        realPlot.setLabelFont("Serif-bold-16"); 
        realPlot.setTitleFont("Serif-bold-24"); 

        textFieldTend = new TextField(new Double(tend).toString(),5);
        textFieldXstart = new TextField(new Double(xstart).toString(),5);
        textFieldDT = new TextField(new Double(deltat).toString(),6);
        textFieldReal = new TextField(new Integer(nReal).toString(),8);
        textFieldParam1 = new TextField(new Double(param1).toString(),5);
        textFieldParam2 = new TextField(new Double(param2).toString(),5);
        textFieldParam3 = new TextField(new Double(param3).toString(),5);
        textFieldParam4 = new TextField(new Double(param4).toString(),5);

        grid13.add(gobutton);
        grid13.add(stopbutton);
        grid13.add(clearbutton1);
        grid13.add(clearbutton2);
        if (application==true) {
            grid13.add(buttonPrintLeft);
            grid13.add(buttonPrintRight); }
        // Action Listener
        stopbutton.addActionListener(this);
        gobutton.addActionListener(this);
        clearbutton1.addActionListener(this);
        clearbutton2.addActionListener(this);
        buttonPrintLeft.addActionListener(this);
        buttonPrintRight.addActionListener(this);

        textFieldTend.setBackground(SystemColor.window);
        textFieldTend.setForeground(SystemColor.windowText);
        textFieldDT.setBackground(SystemColor.window);
        textFieldDT.setForeground(SystemColor.windowText);
        textFieldParam1.setBackground(SystemColor.window);
        textFieldParam1.setForeground(SystemColor.windowText);
        textFieldParam2.setBackground(SystemColor.window);
        textFieldParam2.setForeground(SystemColor.windowText);
        textFieldReal.setBackground(SystemColor.window);
        textFieldReal.setForeground(SystemColor.windowText);
        gridSouth.add(flow7);
        gridSouth.add(flow8);
        flow7.add(labelParam1);
        flow7.add(textFieldParam1);
        flow7.add(labelParam2);
        flow7.add(textFieldParam2);
        flow7.add(labelParam3);
        flow7.add(textFieldParam3);
        flow7.add(labelParam4);
        flow7.add(textFieldParam4);
        choiceProcess.addItem("Ornstein Uhlenbeck Process");
        choiceProcess.addItem("Lognormal Random Walk");
        choiceProcess.addItem("Mean Reverting Random Walk");
        choiceProcess.addItem("Noise Induced Transition");
        choiceProcess.addItem("Stochastic Resonance");
        flow7.add(labelProcess);
        flow7.add(choiceProcess);
        flow8.add(labelReal);
        flow8.add(textFieldReal);
        flow8.add(dtlabel);
        flow8.add(textFieldDT);
        flow8.add(tendlabel);
        flow8.add(textFieldTend);
        flow8.add(xstartlabel);
        flow8.add(textFieldXstart);
        realPlot.setButtons(true);
        realPlot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        meanPlot.setButtons(true);
        meanPlot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        border4.add("North",grid13);
        border4.add("South",gridSouth);
        Panel grid = new Panel(new GridLayout(1,2));
        grid.add(realPlot);
        grid.add(meanPlot);
        border4.add("Center",grid);
        add("Center",border4);
    }
    
    public static void main(String[] args) {
        application=true;
        prg = new SDE();
        frame = new Frame("Stochastic Differential Equations");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
        frame.add(BorderLayout.CENTER,prg);    
        frame.pack();
        frame.setVisible(true);                
        prg.init();                 // start applet        
        prg.start();                // start Thread
    }
    
    public void init() {
        if (prg==null) {
            prg = new SDE(); }
        current = Thread.currentThread();
        calcThread = new Thread(prg);
        calcThread.setPriority(current.getPriority()-1);
        threadActive=true;
        calcThread.start();  // calls the run() method !!
    }
    
    public void start() {
        border4.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        threadActive=true;
    }

    public void stop() {
        border4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        threadActive=false;
    }

    public void run() {
        if (threadActive==false) return;
        // progress.setValue(0); // progress bar
        compute();
        if (threadActive==false) return;
        output();
        threadActive=false;
    }
     
} // SDE
