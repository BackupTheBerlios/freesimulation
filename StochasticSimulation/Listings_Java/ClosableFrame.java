/** A closable Frame to demonstrate the use of listener and events */
import java.awt.*;
import java.awt.event.*;

public class ClosableFrame {  
    public static void main(String[] args) {
        Frame f;

        f = new Frame("Closable Frame");
        f.setSize(200,200);
        f.show();
        // Close Window event
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
                { System.exit(0);} });
    }
}
