/**
 * TestPassingFunctions.java
 */
public class TestPassingFunctions {
    public static void main (String[] args) {
        SquareFunc f = new SquareFunc();
        int points = 10;
        double integral = integrate(f,0,1,points);
        System.out.println(" The integral is : "+integral);
    }

    private static double integrate (function f, double a, double b, int p) {
        double integral=0;
        double dx = (b-a)/p;
        
        for (double x=a+dx/2; x<b; x+=dx) {
            integral+=f.f(x);
        }
        return integral*dx;
    }
} // TestPassingFunctions


