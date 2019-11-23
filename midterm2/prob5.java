public class prob5 {
  public static void main (String [] args) {
    RoomCounter rc = new RoomCounter();
    rc.addPerson();rc.addPerson();rc.addPerson();

    try{
      for(int i = 0; i < 5; i++) {
        rc.removePerson();
        if(rc.getCount() < 0){
          rc.addPerson();
          throw new NegativeCounterException("negative counter");
        } else {
          System.out.println(rc.getCount());
        }
      }
    } catch (NegativeCounterException e) {
      System.out.println(e.getMessage());
    }
  }
}

class RoomCounter {
  private int count;

  public RoomCounter() {
    count = 0;
  }

  public void addPerson() {
    count++;
  }

  public void removePerson() {
    count--;
  }

  public int getCount() {
    return count;
  }
}

class NegativeCounterException extends Exception {
  public NegativeCounterException (String s) {
    super(s);
  }
}
