/*
Jairo Molina
23499086
CSC 212-12A
Fall 2019

Assignment 1: File Search
Java program that simulates "findstr" / "grep" command

Test by running command:
java nameoffile word myfile.txt
*/

import java.util.Scanner;
import java.io.*;

public class main {
  public static void main(String [] args) throws FileNotFoundException {
    //substitute for the path where your file is located and substitute the name of the file with args[1]
    Scanner scan = new Scanner (new File("C:\\Users\\molin\\Documents\\csc212\\"+args[1]));
    int line = 1;
    int index = 0; //index of word instance
    String tmp = "";

    while (scan.hasNextLine()) { //DO: Until the end of file
      tmp = scan.nextLine();//holds next string

      if(tmp.contains(args[0])){ //argument is the word to be search
        index = tmp.indexOf(args[0]);//find index of first occurrence
        while(index >= 0) {//DO: if there is an index, END: -1 when there is no index
          System.out.println(line + ":" + index + " " + tmp);
          index = tmp.indexOf(args[0], index + 1);//find index of next occurence or -1 if none
        }
      }
      line++;
     }
     scan.close();
  }
}
