
/*
Jairo Molina
23499086
CSC 212-12A
Fall 2019

Assignment 2: Circle Class
Java program implementing a class and few functions

Test by running command:
java MolinaJairo2.java
*/
import java.util.Scanner;

public class MolinaJairo2{
  public static void main (String [] args) {
    Scanner scan = new Scanner(System.in);

      System.out.println("Enter radius for circle 1:");
      Circle first = new Circle(scan.nextDouble());

      System.out.println("Enter radius for circle 2:");
      Circle second = new Circle(scan.nextDouble());

      if(first.equals(second)) {
        System.out.println("Both circles are equal \nArea: " + first.area() + "\nCircumference: " + first.circumference());
      } else {
        System.out.println("The circles are not equal \nCircle 1:\nArea:" + first.area() + "\nCircumference: " + first.circumference() + "\nCircle 2:\nArea: "+ second.area() + "\nCircumference: " + second.circumference());
      }

    }
}

class Circle {
private double radius;

Circle(){
  radius = 1;
}

Circle(double r){
  radius = r;
}

public void setRadius(double r) {
    radius = r;
  }
public double getRadius() {
  return radius;
}

public double circumference() {
  return
  
}

public double area() {
  return Math.PI * Math.pow(radius,2);
}

public String toString() {
  return "Circle "+ Double.toString(radius);
}

public boolean equals(Circle c) {
  return (c.getRadius() == radius) ? true : false;
}

}
