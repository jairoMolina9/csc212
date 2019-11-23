public class testing{
  public static void main (String [] args) {
    Circle a = new Circle(2);
    Circle b = new Circle(3);
    Circle c = a;
    System.out.println("BEFORE");
    System.out.println(a);
    c.setRadius(4);
    System.out.println("\nAFTER");
    System.out.println(a);
  }
}

class Circle{
private int radius;

public Circle() { radius = 0; }
public Circle(int r) { radius = r; }

public String toString(){
  return " " + radius;
}
public void setRadius(int r) { radius = r;}
}
