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
import java.awt.*;

/**
 * ScrollPaneDemo.java
 *
 *
 * Created: Tue Mar 23 10:59:49 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

public class ScrollPaneDemo {
    
    public ScrollPaneDemo() {
        
    }
    
    public static void main(String[] args) {
        Frame f = new Frame("ScrollPane Demo");
        f.setSize(50,100);

        ScrollPane sp = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);

        Label l = new Label("A very long test text for "+
                            "demonstration purposes.");
        sp.add(l);

        f.add(sp);
        f.show();        
    }    
} // ScrollPaneDemo
