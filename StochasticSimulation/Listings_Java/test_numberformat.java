public class test_numberformat {
  public static void main(String[] args) {
    float f;
    double d;

    f=12F-2;
    d=12d-2;
    System.out.println(f+"  "+d);

    d=12E-2;
    f=(float)12d-2;
    System.out.println(f+"  "+d);

  }
}
