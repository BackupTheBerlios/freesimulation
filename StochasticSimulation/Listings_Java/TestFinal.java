
/**
 * TestFinal.java
 *
 *
 * Created: Thu Mar 25 11:59:28 1999
 *
 * @author Peter Biechele
 * @version
 */

public class TestFinal {
    
    public static final double Pi = computePi(); //   <-- call method

    public static double computePi() {
      System.out.println("I am doing precalculations !");
      return Math.PI;
    }
    
    public static void main(String[] args) {
        System.out.println(Pi);
        System.out.println(Pi);
    }
    
} // TestFinal
