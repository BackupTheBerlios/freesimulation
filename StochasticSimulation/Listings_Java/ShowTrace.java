
/**
 * ShowTrace.java
*/
/* <HTML> <applet code="ShowTrace.class" width=200 height=200> 
 	</applet> </HTML> */
import java.applet.*;

public class ShowTrace extends Applet{    
    public ShowTrace() {
        System.out.println("Constructor !");
    }    
    public static void main(String[] args) {
        System.out.println("Main Method !");
    }
    public void init() {
        System.out.println("Init Method !");
    }
    public void start() {
        System.out.println("Applet Start Method !");
    }
    public void stop() {
        System.out.println("Applet Stop Method !");
    }
} 
