/*
Write a function called shorten that is defined in the class P8 and shortens each element of
an array of strings. Every string with more than two characters is cut down to its first two
caharacters.
*/

public class prob1 {
  public static void main (String [] args) {
    String x[] = {"CSCI", "1", "11", "Queens", "College", "CUNY"};

    shorten(x);

    for(int i = 0; i < 6; i++)
      System.out.print(x[i] + " ");
    }

    public static void shorten(String x[]) {
      for(int i = 0; i < 6; i++){
        if(x[i].length() > 2)
          x[i] = x[i].substring(0,2);
        }
      }
    }
