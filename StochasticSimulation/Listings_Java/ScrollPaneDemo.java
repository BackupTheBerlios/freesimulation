import java.awt.*;

/**
 * ScrollPaneDemo.java
 *
 *
 * Created: Tue Mar 23 10:59:49 1999
 *
 * @author Peter Biechele
 * @version 1.0
 */

public class ScrollPaneDemo {
    
    public ScrollPaneDemo() {
        
    }
    
    public static void main(String[] args) {
        Frame f = new Frame("ScrollPane Demo");
        f.setSize(50,100);

        ScrollPane sp = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);

        Label l = new Label("A very long test text for "+
                            "demonstration purposes.");
        sp.add(l);

        f.add(sp);
        f.show();        
    }    
} // ScrollPaneDemo
