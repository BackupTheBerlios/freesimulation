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
import java.applet.*;
import java.awt.*;

/* Create an Applet */
public class ParamApplet extends Applet {
  public int NumberPoints;
  public String text_param;

  /* Get the parameters from the HTML file */
  public void init() {
    NumberPoints=Integer.parseInt(this.getParameter("NumberofPoints"));
    text_param=this.getParameter("DisplayText");
  } 

  /* Display the parameters in the window */
  public void paint(Graphics g) {
    g.drawString("Parameter NumberofPoints is "+NumberPoints,20,50);
    g.drawString("Parameter DisplayText is \""+text_param+"\"",20,80);
  }

}

