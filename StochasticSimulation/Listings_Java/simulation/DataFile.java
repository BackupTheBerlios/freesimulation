package simulation;

/**
 * DataFile.java
 *
 *
 * Created: Wed Jun 16 10:42:22 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

import java.io.*;
import java.util.*;
import simulation.*;

/** A new class containing data and its dimensions. 
    Used to return both from the read routine. */
public class DataFile {
    
    // fields of object
    public int maxrows, maxcolumns;
    public int columns;
    public int rows;
    public double[][] data;
    /* set it to true, if you want to read Yahoo data files */
    public boolean yahooData;

    // variables 
    private String delimiter;

    public DataFile(int maxcolumns, int maxrows) {
        this.maxcolumns=columns;
        this.maxrows=maxrows;
        this.data=new double[maxcolumns][maxrows];
        this.columns=0;
        this.rows=0;
        // default delimiters: space, tab, CR, LF, form feed
        this.delimiter=" \t\n\r\f";
    } 

    /** Read text data file using a different delimiter. */
    public int readTextFile(String file, String seperator) 
        throws IOException {
        int returnValue;

        this.delimiter=seperator;
        returnValue = this.readTextFile(file);
        this.delimiter=" \t\n\r\f";
        return returnValue;
    }

    /** Read a certain data file containing double values into an array. </p>
        All points are read into a double array, which is stored in the object..
        The return value is the number of rows actually read. </p>
        The seperator between elements in a row is assumed to be
        a blank (space), tab, CR, LF or form feed.
    */
    public int readTextFile(String file) 
        throws IOException {
        FileIn in;
        String dummy, dummyString;
        int num=0, nummax=0, point;        

        // read in the data from file
        System.out.print(" Reading File .");
        in = new FileIn(file);
        point=0;
        while ( (dummy=in.readLine()) != null ) {
            if (yahooData && point==0) { 
                point++; }
            else {
                StringTokenizer st = new StringTokenizer(dummy,delimiter);
                num=0;
                while (st.hasMoreTokens()) {
                    dummyString=st.nextToken();
                    if (yahooData && num==0) {
                        num++; }
                    else {
                        this.data[num][point]=
                            new Double(dummyString).doubleValue();
                        num++; }
                }
                if (num>nummax) nummax=num;
                point++;
                if (point == maxrows) {
                    System.out.println();
                    System.out.println("Maximum number of rows read, I exit now.");
                    break; }
                if (point%100==0) System.out.print(".");
            }
        }
        in.close();
        System.out.println();        
        System.out.println(" I have read "+point+" lines of data with "
                           +num+" columns each.");
        System.out.println(" Filename was "+file);        
        System.out.println();            
        this.columns=num;
        this.rows=point;
        return point;
    }


    /** Read files, which contain random numbers in a binary
        format produced by the UNIX /dev/rand or /dev/urand devices. 
        The random numbers returned are uniformly distributed 
        double values between 0 and 1. </p>
        The return value of the method is the number of double 
        values read. */
    public int readRandomDeviceFile(String file) 
        throws IOException {
        long lenFile;
        FileInBin inBin;

        // determine size of necessary array from file length
        lenFile=new File(file).length();
        lenFile/=8;
        /* exit program if not enough space in array. */
        if (lenFile>this.maxrows) {
            System.out.println(" There are "+lenFile+" rows in the "+
                               "data file, use a bigger object.");
            System.exit(0); }
        inBin = new FileInBin(file);

        // read the data
        for(int i=0; i<lenFile; i++) {
            // value=inBin.readUnsignedByte();  /* 1 byte each */
            // System.out.println(value); }
            this.data[0][i]=(double)Math.abs(inBin.readLong())/Long.MAX_VALUE;  
            /* 8 bytes = 1 long */
            // System.out.println(this.data[0][i]);
        }
        inBin.close();
        this.rows=(int)lenFile;
        this.columns=1;
        return (int)lenFile;
    }


} // DataFile

