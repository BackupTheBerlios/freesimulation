package simu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
// import java.applet.*;


public class RandomPoints {
  public static void main(String[] args) {

    Random rand = new Random();

    if (args.length != 1) {
      System.err.println(" Error: Wrong number of arguments !");
      System.exit(1);
    }

    int N=Integer.parseInt(args[0]);
    if (N > 10000) {
      System.err.println(" Error: Too many Points to plot !");
      System.exit(1);
    }
    System.out.println(N+" Points are plotted !");

    // Define and allocate the 2D array
    double[][] points = new double[N][2]; 
    
    // Create the random points in 2 dimensions 
    for (int i=0; i<N; i++) {
      points[i][0]=rand.nextDouble();
      points[i][1]=rand.nextDouble();
    }

    // Calulate average
    double[] avg = new double[2];
    for (int j=0; j<N; j++) {
      avg[0]+=points[j][0];
      avg[1]+=points[j][1];
    }
    avg[0]/=N;
    avg[1]/=(double)N;

    System.out.println("Results:");
    for (int i=0;i<2;i++) {
      System.out.println(" Average of Coordinate "+i+": "+avg[i]);
    }

    // Plot the points
    Frame f = new Frame();
    f.setBackground(Color.white);
    f.setSize(200,200);

    // f.pack();
    f.show();

    // Make sure we can close the window and exit the program
    f.addWindowListener(new WindowAdapter() {  // Handle window close
      public void windowClosing(WindowEvent e) { System.exit(0); }
    });


  }
}
