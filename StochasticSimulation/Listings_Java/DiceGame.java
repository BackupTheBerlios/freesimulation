/** Application to show the switch-case statement */
public class DiceGame{
    public static void main (String[] args){
	int face;
	for (int i=1; i<7; i++){
	    face = 1 + (int) (Math.random()*6);
	    switch (face%6) { 
	    case 0:   // print 6 if the remainder is zero
		System.out.println("Face equals 6");
		break;
	    default: 
		System.out.println("Face equals 1,2,3,4 or 5");
		break;
	    }
	}
    }
}
