import java.util.ArrayList;

public class prob4{
  public static void main (String [] args) {
    partC();
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

}
