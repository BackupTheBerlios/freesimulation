package simulation;

import java.awt.*;
import java.awt.event.*;

/** 
 * <p>
 * This class is a convenient subclass of Frame that knows how to
 * handled the WindowClosing event generated when the user requests
 * that the window be closed. </p>  
 * <p> It does NOT exit the program !! </p>
 * <p> By default it simply closes itself,
 * which makes it useful for things like modeless dialogs that can be
 * closed without affecting the rest of the application.  Subclasses
 * of CloseableFrame can override the windowClosing() method if they
 * want to perform additional actions.  Applications that use the
 * CloseableFrame class for a main window may want to exit when a
 * CloseableFrame actually closes.  They can do this by overriding
 * windowClosed() or by registering a separate WindowListener to
 * receive the windowClosed() event. </p>
 **/
public class CloseableFrame extends Frame implements WindowListener {
  // There are two versions of the constructor.  Both register the Frame
  // as its own WindowListener object
  public CloseableFrame() { this.addWindowListener(this); }
  public CloseableFrame(String title) { 
    super(title); 
    this.addWindowListener(this);
  }

  // These are the methods of the WindowListener object.  Only 
  // windowClosing() is implemented
  public void windowClosing(WindowEvent e) { this.dispose(); }
  public void windowOpened(WindowEvent e) {}
  public void windowClosed(WindowEvent e) {}
  public void windowIconified(WindowEvent e) {}
  public void windowDeiconified(WindowEvent e) {}
  public void windowActivated(WindowEvent e) {}
  public void windowDeactivated(WindowEvent e) {}
}
