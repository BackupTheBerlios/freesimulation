/* <applet code="PlotEasy.class" width=400 height=400> 
 	Run Applet </applet> */
import java.applet.Applet;
import java.awt.*;

/** The easisest way to plot a Chart in Java,
    use it as Applet or as an application
    There is no way to stop the program or to close the window, 
    you have to kill the program. */
public class PlotEasy extends Applet {
    public static void main(String[] args) {
	Applet a = new PlotEasy();
	Frame f = new Frame("Easy Plotting");
	f.add("Center",a);
	f.setSize(400,400);
	f.show();
	a.init();
    }

    /* Here we define the function to be plotted */
    double f(double x) {
	return (Math.cos(x/2)+Math.sin(x/7)+2)*getSize().height/4;
    }
    /* Here we plot the function 
       because we are using the actual size of the canvas,
       the plot gets resized each time you resize the window */
    public void paint(Graphics g) {
	for (int x=0; x<getSize().width; x++) {
	    g.drawLine(x,(int)f(x), x+1,(int)f(x+1));
	}
    }
}
