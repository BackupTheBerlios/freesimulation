public class Data {
  double[] data;  

  // The constructor: called by : new Data(array);
  Data(double[] array) {
    data = new double[100];
    for (int i=0; i<array.length; i++) {
      data[i]=array[i]; }
  }

  // The method for computing the mean (average)
  double average() {
    double sum=0;

    for (int i=0; i<data.length; i++) {
      sum = sum + data[i]; }
    sum = sum / data.length;
    return sum;
  }
}
