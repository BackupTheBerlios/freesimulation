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
