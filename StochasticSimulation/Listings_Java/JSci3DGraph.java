import JSci.awt.*;
import java.awt.*;

public class JSci3DGraph {
    public static void main(String[] args) {
	ContourPlot contourGraph;
        LineGraph3D line3DGraph;
	final int N = 20;
	double[][] array = new double[N][N];

	for (int i=0; i<N; i++) {
	    for (int j=0; j<N; j++) {
		array[i][j]=Math.random();
	    }
	}

	Frame f = new Frame("Test");
	f.setSize(400,500);

	line3DGraph = new LineGraph3D(array);
	contourGraph = new ContourPlot(array);

	f.setLayout(new GridLayout(2,2));
	f.add(contourGraph);
	f.add(line3DGraph);
	f.show();
	// simulation.PrintComponent.Dialog(f,f,"Print It");
    }
}
