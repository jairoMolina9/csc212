import java.util.ArrayList;
import java.util.Scanner;

public class prob4{
  public static void main (String [] args) {
    Circle d [] = new Circle [5];
    partD(d);

    for(Circle c : d)
      System.out.println(c.getRadius());
  }

  public static void partB() {
    ArrayList<String> carModel = new ArrayList<String>();
    carModel.add("BMW");
    carModel.add("Nissan");
    carModel.add("Ford");

    carModel.remove(1);
    carModel.add(0,"Prius");
    carModel.set(2,"Ferrari");
  }

  public static void partC() {
    try{
      String list [] = new String[10];
      System.out.println(list[10]);
    } catch (ArithmeticException e) {
      System.out.println("A.E error");
    } catch (RuntimeException e) {
      System.out.println("R.E error");
    }
  }

  private static void partD(Circle [] d) {
    Scanner sc = new Scanner(System.in);
    for(int i = 0; i < d.length; i++) {
      System.out.println("Enter radius for: " + (i+1));
      d[i] = new Circle(); //must do this
      d[i].setRadius(sc.nextDouble());
    }
  }
}

class Circle {
  private double radius;

  public Circle() {
    radius = 0;
  }

  public void setRadius(double r) {
    radius = r;
  }

  public double getRadius() {
    return radius;
  }
}
