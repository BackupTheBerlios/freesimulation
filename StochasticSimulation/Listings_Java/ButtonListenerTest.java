/** The implement and override version of
    the Event Listener Demonstration */

import java.awt.*;
import java.awt.event.*;

public class ButtonListenerTest implements ActionListener {
    // overriding the appropriate method
    public void actionPerformed(ActionEvent e) {
        // here we could react to the pushed button 
        System.out.println(" You pushed the Button");
    }
    // empty constructor
    public ButtonListenerTest() {}

    public static void main (String[] args) {
        // get a reference to the class 
        // with the Listener implementation
        ButtonListenerTest blt = new ButtonListenerTest();
        // create a window to position the button inside it
        Frame f = new Frame("TestButtonListener");
        f.setSize(200,200);
        // create the event source
        Button but = new Button("Push Me");
        // register the Listener
        but.addActionListener(blt);
        // display the window with the button
        f.add(but);
        f.show();
        // Wait for a long time
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {}
    }
}
