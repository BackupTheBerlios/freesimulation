
/**
 * ConvertSymphony.java
 *
 *
 * Created: Tue Jan 26 10:39:01 1999
 *
 * @author Peter Biechele
 * @version
 */

import java.io.*;

public class ConvertSymphony  {
    
    // Files for read and write
    File filein = new File ("event.log"); 
    File fileout = new File ("gnu.out");

    // Parameters to read out: also the output order in the new file
    String[] p = {"n0d" , "Nd", "na", "nb"}; 

    public ConvertSymphony() {	
    }
    
    public static void main(String[] args) throws IOException {
	ConvertSymphony a= new ConvertSymphony();
	a.run();
    }

    public void run() throws IOException {
	FileReader fin = new FileReader(filein);
	FileWriter fout = new FileWriter(fileout);
	BufferedReader in = new BufferedReader(fin);
	BufferedWriter out = new BufferedWriter(fout);
	
	String line, lineout, sdummy;
	char schar;
	int pos, start, end, flag;

	out.write("##");
	for (int i=0; i<p.length; i++) {
	    out.write(" "+p[i]);	    
	    p[i]=p[i]+"=";
	}
	out.newLine();

	while ( (line=in.readLine()) != null) {
	    // System.out.println(line);
	    flag=0; 
	    lineout=new String();
	    for (int j=0; j<p.length; j++) {
		pos=line.indexOf(p[j],0);
		start=pos+p[j].length();

		end=start;
	        schar=line.charAt(end);
		while (schar != ';') {
		    end++;
		    schar=line.charAt(end);
		}
		sdummy=" "+line.substring(start,end);		
		// System.out.println(j+" : "+p[j]+" :: "+sdummy);
		if (j != 1) { 
		    lineout=lineout.concat(sdummy); }
		else if (Double.valueOf(sdummy).intValue()==1000) {
		    flag=1; }
	    }
	    if (flag==1) {
		out.write(lineout);
		out.newLine(); 
	    }
	}
	in.close(); fin.close();
	out.close(); fout.close();
    }

} // ConvertSymphony
