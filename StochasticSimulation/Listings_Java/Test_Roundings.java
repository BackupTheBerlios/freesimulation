public class Test_Roundings {
  public static void main(String[] args) {
    double a1=7.49;
    double b1=7.5;
    double c1=7.51;
    
    double a2=-7.49;
    double b2=-7.5;
    double c2=-7.51;
    
    System.out.println("round: "+Math.round(a1)+" "+a1);
    System.out.println("round: "+Math.round(a2)+" "+a2);
    System.out.println("round: "+Math.round(b1)+" "+b1);
    System.out.println("round: "+Math.round(b2)+" "+b2);
    System.out.println("round: "+Math.round(c1)+" "+c1);
    System.out.println("round: "+Math.round(c2)+" "+c2);
    
    System.out.println("ceil: "+Math.ceil(a1)+" "+a1);
    System.out.println("ceil: "+Math.ceil(a2)+" "+a2);
    System.out.println("ceil: "+Math.ceil(b1)+" "+b1);
    System.out.println("ceil: "+Math.ceil(b2)+" "+b2);
    System.out.println("ceil: "+Math.ceil(c1)+" "+c1);
    System.out.println("ceil: "+Math.ceil(c2)+" "+c2);
    
    System.out.println("floor: "+Math.floor(a1)+" "+a1);
    System.out.println("floor: "+Math.floor(a2)+" "+a2);
    System.out.println("floor: "+Math.floor(b1)+" "+b1);
    System.out.println("floor: "+Math.floor(b2)+" "+b2);
    System.out.println("floor: "+Math.floor(c1)+" "+c1);
    System.out.println("floor: "+Math.floor(c2)+" "+c2);

    System.out.println("rint: "+Math.rint(a1)+" "+a1);
    System.out.println("rint: "+Math.rint(a2)+" "+a2);
    System.out.println("rint: "+Math.rint(b1)+" "+b1);
    System.out.println("rint: "+Math.rint(b2)+" "+b2);
    System.out.println("rint: "+Math.rint(c1)+" "+c1);
    System.out.println("rint: "+Math.rint(c2)+" "+c2);
  }
}
