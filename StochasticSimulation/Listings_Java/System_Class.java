/** A program to demonstrate keyboard input, escape codes
    and string (object) comparisons   */

import java.io.*;

public class System_Class  {
    public static void main (String[] args) throws IOException {
        /* create object to read lines of text from the user
           first: a reader object for System.in (keyboard) */
        Reader read_buffer = new InputStreamReader(System.in);
        /* second: a buffered input for the keyboard */
        BufferedReader input = new BufferedReader(read_buffer);
        System.out.println("To exit please type the word \"Java\".");

        for(;;) { // Loop forever until input of the word "Java"
            System.out.print("Please type a word: ");
            /* Read a line from the user using readLine() */
            String line = input.readLine();
            /* If we reach the end-of-file, 
               or if the user types "Java", then quit */
            if ((line == null) || line.equals("Java")) break;
            /* Otherwise just echo the word */
            System.out.println("You typed : "+line+"\n");
        }
    }
}

