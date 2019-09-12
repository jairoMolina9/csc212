import java.util.Scanner;
import java.io.*;

public class main {
  public static void main(String [] args) throws FileNotFoundException {

    int lines = 1;
    int times = 0;
    String dummy = "";
    Scanner scan = new Scanner (new File("C:\\Users\\molin\\Documents\\csc212\\"+args[1]));

    while (scan.hasNextLine()) {
      dummy = scan.nextLine();
      if(dummy.contains(args[0])){
        System.out.println("found at line: " + lines + "\nfound at index: " + dummy.indexOf(args[0]));
      }
      lines++;
     }
     scan.close();

  }
}
