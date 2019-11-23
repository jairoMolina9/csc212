public class prob6 {
  public static void main (String [] args) {
      /*
      12
      square
      square
      9
      20
      36
      */
      Square s = new Square(3);
      System.out.println(s.perimeter()); //---------(a)
      Shape p = new Square(5);
      System.out.println(p.toString()); //----------(b)
      Rectangle r = new Square(6);
      System.out.println(r); //----------------------(c)
      Square s1 = new Square(3);
      System.out.println(s1.area()); //--------------(d)
      Shape p1 = new Square(5);
      System.out.println(p1.perimeter()); //---------(e)
      Rectangle r1 = new Square(6);
      System.out.println(r1.area()); //---------------(f)
  }
}
interface Shape {
  public int area();
  public int perimeter();
}
class Rectangle implements Shape {
  private int width;
  private int length;

  public Rectangle(int length, int width) {
    this.width = width;
    this.length = length;
  }

  public int area() {
    return length * width;
  }

  public int perimeter() {
    return 2 * (length + width);
  }

  public String toString() {
    return "rectangle";
  }
}
class Square extends Rectangle implements Shape {
  private int count;

  public Square(int s) {
    super(s, s);
    count++;
  }

  public String toString() {
    return "square";
  }
}
