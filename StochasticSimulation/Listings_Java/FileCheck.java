/** FileCheck Demonstration class */

import java.io.*;

public class FileCheck {
    public static void main(String args[]) {
        if (args.length==0) {
            System.out.println(" Please supply a filename/directory !");
            System.exit(1);  } 
        
	String filename = args[0]; // set the filename
        File file = new File(filename);

        /* Check for existence of file */
        if ( file.exists() == true ) {
            System.out.println(" File "+filename+" exists!");
        } else {
            System.out.println(" File "+filename+" does not exist!");}
        /* Check for existence AND not directory of a file */
        if ( file.isFile() == true) {
            System.out.println(" File "+filename+" exists!");
        } else {
            System.out.println(" File "+filename+" does not exist or "+
                               "is a directory!");}
        /* Check for existence AND not directory of a file */
        if ( file.isDirectory() == true) {
            System.out.println(" File "+filename+" is a directory!");
            String[] filelist = file.list();
            for (int i=0; i<filelist.length; i++) {
                System.out.println(filelist[i]); }
        } else {
            System.out.println(" File "+filename+" is not a directory!");}
    }
}
