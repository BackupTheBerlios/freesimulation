package simulation;

import java.awt.*;
import java.awt.event.*;

/** 
 * <p> This class is a convenient subclass of Frame that knows how to
 * handled the WindowClosing event generated when the user requests
 * that the window be closed. </p>
 * <p> This closes the window and exits the whole program, should be used
 * for a main window. </p>
 * <p> By default it closes the window AND exits the program. </p>
 **/
public class ClosingFrame extends Frame implements WindowListener {
  // There are two versions of the constructor.  Both register the Frame
  // as its own WindowListener object
  public ClosingFrame() { this.addWindowListener(this); }
  public ClosingFrame(String title) { 
    super(title); 
    this.addWindowListener(this);
  }

  // These are the methods of the WindowListener object.  Only 
  // windowClosing() is implemented
  public void windowClosing(WindowEvent e) { this.dispose(); }
  public void windowOpened(WindowEvent e) {}
  public void windowClosed(WindowEvent e) { System.exit(0); }
  public void windowIconified(WindowEvent e) {}
  public void windowDeiconified(WindowEvent e) {}
  public void windowActivated(WindowEvent e) {}
  public void windowDeactivated(WindowEvent e) {}
}
