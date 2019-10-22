public class prob13 {
  public static void main(String [] args){
    String x = "12345678912";
    System.out.println(checkDigit(x));
    System.out.println(fastPower(2,2));
  }
  public static int checkDigit(String str) {
    int x_12 = 0;
    for(int i = 0; i < str.length(); i++){
      if(i % 2 == 0) {
        x_12 += str.charAt(i) - '0';
      } else {
        x_12 += 3 * (str.charAt(i) - '0');
      }
    }
    return x_12%10;
  }
  public static int fastPower(int a, int n) {
    if(n == 0) return 1;

    return a * fastPower(a, n-1);

  }
}
