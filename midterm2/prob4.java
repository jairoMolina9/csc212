import java.util.ArrayList;

public class prob4{
  public static void main (String [] args) {
    ArrayList<String> carModel = new ArrayList<String>();
    carModel.add("BMW");
    carModel.add("Nissan");
    carModel.add("Ford");

    carModel.remove(1);
    carModel.add(0,"Prius");
    carModel.set(2,"Ferrari");

  }
}
