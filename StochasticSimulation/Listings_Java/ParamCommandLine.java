public class ParamCommandLine {
  public static void main(String[] args) {
    int N;

    // Number of parameters on the command line
    N=args.length;

    if (N > 0) {
      /* Output all parameters each in a line */
      for (int i=0; i<N;i++) {
        System.out.println(" Parameter No. "+i+" : "+args[i]);
      }
    }
    else {
      /* No parameters are given */
      System.out.println(" NO parameters specified !");
    }

  }
}
