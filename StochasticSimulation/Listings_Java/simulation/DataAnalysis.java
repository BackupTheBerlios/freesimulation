package simulation;

import java.util.Random;

public final class DataAnalysis {

    /** Calculate the factorial of an integer. 
        Possible up to factorial of 170, then it returns zero. */
    public static double factorial(int fact) {
        if (fact>170) {
            return 0;
        }
        if (fact>=0) {
            double prod=1;
            for (int i=2; i<=fact; i++) {
                prod*=i;
            }
            return prod;
        }
        else {
            return 0;
        }
    }

}
