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
package de.berlios.StochasticSimulation;

import java.io.*;
import java.net.*;
import java.util.*;

/**
<pre>
Java code to read/write files on the server from an applet!
This is the famous Linlyn code.

Use:  
  compile this file, and have your applet call it as below.

  to upload a file:  
import de.berlios.StochasticSimulation.*;
        Linlyn ftp = new Linlyn( <servername>, <user>, <password> );
        ftp.upload( <directory>, <filename>, <contents of file> );

  to download a file:  
import de.berlios.StochasticSimulation.*;
        Linlyn ftp = new Linlyn( <servername>, <user>, <password> );
        String contents = ftp.download( <directory>, <filename> );

        the default is ASCII transfer, an overloaded method does bin.

  All parameters and return values are Strings. E.g.
        Linlyn ftp = new Linlyn( "rtfm.mit.edu", "anonymous", "linden@" );
        String contents = ftp.download( 
                      "/pub/usenet-by-group/comp.lang.java.programmer"
                      "Java_Programmers_FAQ" );

        [the actual values above are not generally valid, substitute
         your own server for your first attempt, see note 1.]

  Notes:
    1.  Usual applet security rules apply: you can only get a file
        from the server that served the applet.
    2.  The applet server must also be an FTP server.  This is NOT true
        for some ISPs, such as best.com.  They have separate FTP and
        http machines.  This code may work on such a setup if you put
        the classfiles into the ftp area, and in the HTML file say:
          applet  codebase="ftp:///home/linden/ftp"  code="t.class" 
    3.  This code does not break Java security.
        It uses FTP to transfer files.  If the author of the applet
        has FTP disabled you are out of luck.
        It breaks regular system security however, as it publishes
        (effectively) your ftp password.  Only use on an Intranet and
        with authorization.
    4.  Compiling this causes some deprecation warnings.   We wanted to 
        stick with code that would work in JDK 1.0 browsers.
    5.  Each upload or download creates, uses, and terminates a new
        ftp session.  This is intended for low volume transfer, such
        as the ever popular high-score files.
    6.  Look at the source for the methods for binary transfers.

  Version 1.0   May 6 1998.
  Version 1.1   May 20 1998. -- added a debugging flag
  Version 1.1a  May 26 1998. -- fixed the ASCII/BIN flag inversion
  Version 1.1b  May 29 1998. -- added the security warning.
  Version 2.0   Jul 1, 1998. -- Updated to parse multi-string responses 
                                a la RFC 959
  Version 2.1   Aug 5, 1998. -- Updated to work with VMS ftp servers
                                VMS does not send either a ")" OR a ")."
                                terminating the IP number, port sequence 
                                in response to PASV.
  Version 2.1a  Aug 6, 1998  -- more than one line as a "hello" message.
                                (tvalesky@patriot.net)
  Version 2.2   Sep 22 1998  -- added a flush() in ftpSendCmd.

  Authors:
        Robert Lynch
        Peter van der Linden  (Author of "Just Java 1.1" book).
 
  Support:
        Unsupported: That's why we give you the source.
        Help may be available on time & materials basis only.
			You can get copious debug information by changing to
			DEBUG=true     below and recompiling.

  Copyright 1998 Robert Lynch, Peter van der Linden
  This work is distributed under the GNU GPL, version 2.

  Those using the code do so at their own risk and the authors 
  are not responsible for any costs, loss, or damage which may
  thereby be incurred. 
</pre>
*/
public class Linlyn {

    // FOR INITIAL DEBUGGING: set the variable to "true"
    private boolean DEBUG = false;

    // constructor needs servername, username and passwd
    public Linlyn(String server, String user, String pass) {
        try { 
            ftpConnect(server);
            ftpLogin(user, pass);
        } catch(IOException ioe) {ioe.printStackTrace();}
    }

    public String download(String dir, String file)
        throws IOException { return download(dir, file, true); }

    public String download(String dir, String file, boolean asc)
        throws IOException {
        ftpSetDir(dir);
		ftpSetTransferType(asc);
        dsock = ftpGetDataSock();
        InputStream is = dsock.getInputStream();
        ftpSendCmd("RETR "+file);
        
        String contents = getAsString(is);
        ftpLogout();
        return contents;
    }

    public void upload(String dir, String file, String what)
        throws IOException { upload(dir, file, what, true); }

    public void upload(String dir, String file, String what, boolean asc)
        throws IOException {
        ftpSetDir(dir);
		ftpSetTransferType(asc);
        dsock = ftpGetDataSock();
        OutputStream os = dsock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        ftpSendCmd("STOR "+file);
        dos.writeBytes(what);    
        dos.flush();
        ftpLogout();
    }

    ///////////////// private fields ////////////////////
    private String getAsString(InputStream is) {
        int c=0;
        char lineBuffer[]=new char[128], buf[]=lineBuffer;
        int room= buf.length, offset=0;
        try {
          loop: while (true) {
              // read chars into a buffer which grows as needed
                  switch (c = is.read() ) {
                      case -1: break loop;

                      default: if (--room < 0) {
                                   buf = new char[offset + 128];
                                   room = buf.length - offset - 1;
                                   System.arraycopy(lineBuffer, 0, 
                                            buf, 0, offset);
                                   lineBuffer = buf;
                               }
                               buf[offset++] = (char) c;
                               break;
                  }
          }
        } catch(IOException ioe) {ioe.printStackTrace();}
        if ((c == -1) && (offset == 0)) {
            return null;
        }
        return String.copyValueOf(buf, 0, offset);
    }

    private void ftpConnect(String server)
        throws IOException {
        // Set up socket, control streams, connect to ftp server
        // Open socket to server control port 21
        csock = new Socket(server, CNTRL_PORT);
        // Open control streams
        InputStream cis = csock.getInputStream();
        dcis =  new BufferedReader(new InputStreamReader(cis));
        OutputStream cos = csock.getOutputStream();
        pos = new PrintWriter(cos, true); // set auto flush true.

        // handle more than one line returned
        String reply = dcis.readLine();
       	String numerals = reply.substring(0, 3);
        String hyph_test = reply.substring(3, 4);
        String next = null;
        if(hyph_test.equals("-")) {
            boolean done = false;
            while(!done) { // read lines til find "" -> last line
                next = dcis.readLine();
                if(next.substring(0,3).equals(numerals) && 
                    next.substring(3, 4).equals(" "))
                    done = true;
            }
        }

       if(numerals.substring(0,3).equals("220")) // ftp server alive
            ; // System.out.println("Connected to ftp server");
        else System.err.println("Error connecting to ftp server.");
    }

    private void ftpLogin(String user, String pass)
        throws IOException {
        ftpSendCmd("USER "+user);
        ftpSendCmd("PASS "+pass);
    }

    private void ftpSetDir(String dir)
        throws IOException { 
        // cwd to dir
        ftpSendCmd("CWD "+dir);
    }

	private void ftpSetTransferType(boolean asc)
		throws IOException {
		// set file transfer type
		String ftype = (asc? "A" : "I");
		ftpSendCmd("TYPE "+ftype);
	}    

    private Socket ftpGetDataSock()
        throws IOException {
         // Go to PASV mode, capture server reply, parse for socket setup
         // V2.1: generalized port parsing, allows more server variations
        String reply = ftpSendCmd("PASV");

         // New technique: just find numbers before and after ","!
        StringTokenizer st = new StringTokenizer(reply, ",");
        String[] parts = new String[6]; // parts, incl. some garbage
        int i = 0; // put tokens into String array
        while(st.hasMoreElements()) {
            // stick pieces of host, port in String array
            try {
                parts[i] = st.nextToken();
                i++;
            } catch(NoSuchElementException nope){nope.printStackTrace();}
        } // end getting parts of host, port

        // Get rid of everything before first "," except digits
        String[] diggies = new String[3];
        for(int j = 0; j < 3; j++) {
            // Get 3 characters, inverse order, check if digit/character
            diggies[j] = parts[0].substring(parts[0].length() - (j + 1),
                parts[0].length() - j); // next: digit or character?
            if(!Character.isDigit(diggies[j].charAt(0)))
                diggies[j] = "";
        }
        parts[0] = diggies[2] + diggies[1] + diggies[0];
        // Get only the digits after the last ","
        String[] porties = new String[3];
        for(int k = 0; k < 3; k++) {
            // Get 3 characters, in order, check if digit/character
            // May be less than 3 characters
            if((k + 1) <= parts[5].length())
                porties[k] = parts[5].substring(k, k + 1);
            else porties[k] = "FOOBAR"; // definitely not a digit!
            // next: digit or character?
            if(!Character.isDigit(porties[k].charAt(0)))
                    porties[k] = "";
        } // Have to do this one in order, not inverse order
        parts[5] = porties[0] + porties[1] + porties[2];
        // Get dotted quad IP number first
        String ip = parts[0]+"."+parts[1]+"."+parts[2]+"."+parts[3];

        // Determine port
        int port = -1;
        try { // Get first part of port, shift by 8 bits.
            int big = Integer.parseInt(parts[4]) << 8;
            int small = Integer.parseInt(parts[5]);
            port = big + small; // port number
        } catch(NumberFormatException nfe) {nfe.printStackTrace();}
        if((ip != null) && (port != -1))
            dsock = new Socket(ip, port);
        else throw new IOException();
        return dsock;
    }

    private String ftpSendCmd(String cmd)
        throws IOException
    { // This sends a dialog string to the server, returns reply
	  // V2.0 Updated to parse multi-string responses a la RFC 959
	  // Prints out only last response string of the lot.
	pos.print(cmd + "\r\n" );
	pos.flush(); 
	String reply = dcis.readLine();
	String numerals = reply.substring(0, 3);
	String hyph_test = reply.substring(3, 4);
	String next = null;
	if(hyph_test.equals("-")) {
		boolean done = false;
		while(!done) { // read lines til find "" -> last line
			next = dcis.readLine();
			if(next.substring(0,3).equals(numerals) && 
				next.substring(3, 4).equals(" "))
				done = true;
		}
		if(DEBUG)
			System.out.println("Response to: "+cmd+" was: "+next);
		return next;
	} else
		if(DEBUG)
			System.out.println("Response to: "+cmd+" was: "+reply);
        return reply;
    }

    private void ftpLogout() {// logout, close streams
        try { 
           if(DEBUG) System.out.println("sending BYE");
            pos.print("BYE" + "\r\n" );
            pos.flush();
            pos.close();
            dcis.close();
            csock.close();
            dsock.close();
        } catch(IOException ioe) {ioe.printStackTrace();}
    }

    private static final int CNTRL_PORT = 21;
    private Socket csock = null;
    private Socket dsock = null;
    private BufferedReader dcis;
    private PrintWriter pos;
}
