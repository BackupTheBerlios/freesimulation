
/**
 * DataUsage.java
 *
 *
 * Created: Thu Jul 22 11:49:30 1999
 *
 * @author Peter Biechele
 * @version
 */
import VisualNumerics.math.*;

public class DataUsage {
    
    public DataUsage() {
        
    }
    
    public static void main(String[] args) {
        double[] array = new double[100]; 
        for (int i=0; i<100; i++) {
            array[i]=Math.random(); }

        double result = Statistics.average(array); 
        System.out.println(result);
        
        Data dat = new Data(array); 
        result = dat.average();     
        System.out.println(result);
    }
    
} // DataUsage
