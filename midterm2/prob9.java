public class prob9 {
  public static void main (String [] args) {
    Rectangle [] r = new Rectangle[2];
    r[0] = new Rectangle(1,2);
    r[1] = new Rectangle(3,4);
    if(r[0].equals(r[1])) {
      System.out.println("equals");
    } else {
      System.out.println("does not equal");
    }
  }
}

class Rectangle {
  private int width;
  private int length;

  public Rectangle() {
    width = length = 0;
  }

  public Rectangle(int w, int l) {
    width = w;
    length = l;
  }

  public void setWidth(int w) {
    width = w;
  }

  public void setLength(int l) {
    length = l;
  }

  public int getWidth() { return width; }
  public int getLength() { return length; }

  public Boolean equals(Rectangle o) {
    boolean flag = false;

    if(width == o.getWidth() && length == o.getLength())
      flag = true;

    return flag;
  }
}
