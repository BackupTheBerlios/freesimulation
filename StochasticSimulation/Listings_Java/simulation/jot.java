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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class jot {

   // Peter van der Linden, http://www.afu.com,    Dec 23 1998.
   // Author of Java Programmers FAQ, and book "Just Java 1.2"
   // A class for non-blocking char-by-char I/O without pressing "enter".
   // This code is licensed to you under the GNU Public license, version 2.
   // See http://www.gnu.org for license wording.    pvdl@best.com

   // compile the source with your program, like this
   //     javac  yourcode.java  jot.java
   //
   // In yourcode.java, use it like this:       
   //        jot j = new jot("do you feel lucky: Y/N?");
   //        int c = j.kbhit()
   //        j.popDown();      // when you're done with it.
   //
   // At runtime, it pops up a jdrame labelled "jotting pad"
   // You will get the chars you type into the Jotting pad when you call
   //             j.getch()     or    j.kbhit()
   // If no characters are available, it will return -1 immediately.
   // If a character is available, it returns its value.
   // some characters, like cursor arrows, only have key codes, not
   // key values, so are not noticed.   You could modify this code to 
   // return key codes if you need them.
   // You can look at the 400-odd KeyCode values in the file
   //  $JAVAHOME/src/java/awt/event/KeyEvent.java     In many cases,
   // the ASCII code is the same as KeyCode.
   //
   // You must type into the jotting pad (not elsewhere on the screen).
   // Since the characters aren't echoed you can also use it for passwords.
   // If you want to enter entire Strings, you can but it is your job to
   // assemble the String from individual keystrokes (better to use JTextField).
   //
   // (For performance) this is not thread safe, but is easy to modify so it is. 
   // The pad allows unbounded input, and has correct behavior 
   // when you type more than you ask for, or
   // when you ask for more than you have typed.

    private final ArrayList buffer = new ArrayList();
    private int nextRead=0, nextWrite=0;

    public final int kbhit() {
        // some use this alternative name
        return getch();
    }

    public final int getch() {
        if (nextRead >= nextWrite) return -1;
        int c = ((Character) buffer.get(nextRead)).charValue();
        buffer.remove(nextRead);
           // removing shifts the remaining elements down by one!
           // so decrement nextWrite, and don't increment nextRead.
        nextWrite--;
        return c;
    }

    public final void popDown() {
        // some use this alternative name
        jd.setVisible(false);
        jd=null;
    }

    ////////////////////////////
    //// This constructor is for the "no prompt string" case.
    public jot() {
        this("Type input below");
    }

    //   use the constructor to provide a prompt message
    public jot(final String str) {
        jd = new JDialog(new Frame(), str);
        int width = 250; //30+jd.getFontMetrics(jd.getFont()).stringWidth(str);
        jd.setSize(width, 80);
        jd.addMouseListener( new MouseAdapter() {
             public void mouseEntered(MouseEvent e) {
                 // ask for focus on mouse entry
                 jd.requestFocus();
             }
        } );

        jd.addKeyListener( new KeyAdapter() {
             public void keyTyped(KeyEvent e) {
                 buffer.add(nextWrite++, new Character( e.getKeyChar()));
             }
        } );
        jd.show();
        // ask for focus to begin with
        jd.requestFocus();
    }

    private JDialog jd;

    public static void main(String args[]) {
    // a simple test harness, do 25 getch() spaced out 1.5 secs       
    // if there's a char there, it gets it.
      jot jo = new jot("Type below:");

      try {
        for (int i=0; i<25; i++) {
            int j = jo.kbhit();
            System.out.println( "j="+j +", char="+ (char)j );
            Thread.currentThread().sleep(1500);
        }
        jo.popDown();
      } catch(Exception e) {System.out.println("Excpn: "+e);}
      System.exit(0);
    }

}
