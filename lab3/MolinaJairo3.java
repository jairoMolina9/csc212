/*
Jairo Molina
23499086
CSC 212-12A
Fall 2019
Assignment 3: Theater Simulation
Java program simulating a movie theater infrastructure
*/

import java.time.LocalTime;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MolinaJairo3 {
  static Scanner scan = new Scanner(System.in);
  public static void main (String [] args) throws FileNotFoundException {

  //  testMovie();
  //  testAud();
  //  testShowing();
  //  testTheater();

    ArrayList<Movie> mov = new ArrayList<Movie> ();
    ArrayList<Auditorium> audi = new ArrayList<Auditorium> ();
    setData(mov, audi);

    Theater tr = new Theater(mov, audi);

    for(int i = 1; i <= 3; i++) {
      System.out.println("\n\t\t\t Day " + i + "\n");
      tr.schedule();
      tr.sellTickets();
    }
  }

  public static void setData(ArrayList<Movie> mov, ArrayList<Auditorium> audi) throws FileNotFoundException {
    Scanner in = new Scanner(new File("auditoriums.txt"));

    while(in.hasNext()) {
      String []split_one = in.nextLine().split("\t");
      String []split_two = split_one[0].split("\\s");
      Auditorium a = new Auditorium(Integer.parseInt(split_two[0]), Integer.parseInt(split_two[1]));
      audi.add(a);
    }

    Scanner in2 = new Scanner (new File("movies.txt"));

    while(in2.hasNext()) {
      String []split_one = in2.nextLine().split("\t");
      String []split_two = split_one[0].split("\\s");
      if(Double.parseDouble(split_two[2]) > 10.0 || Double.parseDouble(split_two[2]) < 0) {
        System.out.println("The popularity input for movie '"+ split_two[0]+ "' is incorrect. Adjust and try again.");
        System.exit(0);
      }
      Movie m = new Movie(split_one[0],Integer.parseInt(split_two[1]), Double.parseDouble(split_two[2]));
      mov.add(m);
    }
  }//end of setData function

  public static void testMovie(){
      String title ;
      int duration;
      double popularity;
      Movie test = null;
      boolean check_error = true;

      //Asks user for input instead of reading from files
      while(check_error) {
        try {
          System.out.println("Enter title:");
          title = scan.nextLine();
          System.out.println("Enter duration (minutes):");
          duration = scan.nextInt();

          do{
            System.out.println("Enter popularity:");
            popularity = scan.nextDouble();
          }while(popularity < 0 || popularity > 10);

          test = new Movie(title,duration,popularity);
          check_error = false;

        } catch(InputMismatchException e) {
          System.out.println("Enter correct data type");
        }
      }

      System.out.println(test);
    }//end of testMovie function

    public static void testAud(){
      int id;
      int capacity;
      Auditorium test = null;
      boolean check_error = true;

      //Asks user for input instead of reading from files
      while(check_error) {
        try {
          System.out.println("Enter id:");
          id = scan.nextInt();
          System.out.println("Enter capacity:");
          capacity = scan.nextInt();

          test = new Auditorium(id,capacity);
          check_error = false;

        } catch(InputMismatchException e) {
          System.out.println("Enter correct data type");
        }
      }

      System.out.println(test);
    }//end of testAud function

    public static void testShowing(){
      Movie m = new Movie("spider-man", 120, 5.5);
      Auditorium a = new Auditorium(1,250);
      LocalTime s = LocalTime.of(10,43,12);

      Showing test = new Showing(m, a, s);

      System.out.println(test);
    }//end of testShowing function

    public static void testTheater() {
      ArrayList<Movie> m = new ArrayList<Movie> (4);
      ArrayList<Auditorium> a = new ArrayList<Auditorium> (4);
      ArrayList<LocalTime> l = new ArrayList<LocalTime> (4);

      Movie movie1 = new Movie("spider-man",120,5.4);
      Movie movie2 = new Movie("titanic",115,6.2);
      Movie movie3 = new Movie("transformer",110,4.1);
      Movie movie4 = new Movie("harrypotter",150,6.5);

      Auditorium aud1 = new Auditorium(1,300);
      Auditorium aud2 = new Auditorium(2,133);
      Auditorium aud3 = new Auditorium(3,255);
      Auditorium aud4 = new Auditorium(4,286);

      m.add(movie1);
      m.add(movie2);
      m.add(movie3);
      m.add(movie4);

      a.add(aud1);
      a.add(aud2);
      a.add(aud3);
      a.add(aud4);

      Theater tr = new Theater(m, a);

      tr.schedule();
      tr.sellTickets();
    }//end of testTheater function

}//end of Main class

class Movie {
  private String title;
  private int duration;
  private double popularity;

  public Movie() {
    title = "";
    duration = 0;
    popularity = 0.0;
  }

  public Movie(String t, int d, double p) {
    title = t;
    duration = d;
    popularity = p;
  }

  public void setDuration(int d) {
    duration = d;
  }

  public void setPopularity(double p) {
    popularity = p;
  }

  public int getDuration() { return duration; }
  public double getPopularity() { return popularity; }

  public String toString() {
    return title + " (" + duration + " minutes)";
  }
} //end of Movie class

class Auditorium {
  private int id;
  private int capacity;

  public Auditorium() {
    id = capacity = 0;
  }

  public Auditorium(int i, int c) {
    id = i;
    capacity = c;
  }

  public int getCapacity() { return capacity; }

  public String toString() { return "Auditorium #" + id; }
}// end of Auditorium class

class Showing {
  private Movie movie;
  private Auditorium auditorium;
  private LocalTime showtime;

  public Showing() {
    movie = null;
    auditorium = null;
    showtime = null;
  }

  public Showing(Movie m, Auditorium a, LocalTime s) {
    movie = m;
    auditorium = a;
    showtime = s;
  }

  public Movie getMovie() { return movie; }
  public Auditorium getAuditorium() { return auditorium; }
  public LocalTime getShowtime() { return showtime; }

  public String toString() {
    return movie + " in " + auditorium + " at " + showtime;
  }
}//end of Showing class

class Theater {
  static Random random = new Random();
  static int timeBetweenShowings = 15;

  private LocalTime firstShowtime;
  private LocalTime lastShowtime;

  private double ticketPrice;

  private ArrayList <Movie> movies;
  private ArrayList <Auditorium> auditoriums;
  private ArrayList <Showing> showings;

  public Theater() {
    firstShowtime = null;
    lastShowtime = null;
    ticketPrice = 0;
    movies = null;
    auditoriums = null;
    showings = null;
  }

  public Theater(ArrayList<Movie> m, ArrayList<Auditorium> a) {
    firstShowtime = LocalTime.of(10,00);
    lastShowtime = LocalTime.of(23,00);
    ticketPrice = 12.50;
    movies = m;
    auditoriums = a;
    showings = new ArrayList<Showing>();
  }

  private Movie randomMovie() {
    return movies.get(random.nextInt(movies.size()));
  }

  public void schedule() {
    if(!(showings.isEmpty()))
      showings.clear();

    for(Auditorium audi : auditoriums) {
      while(firstShowtime.compareTo(lastShowtime) != 0) {
        Showing show = new Showing(randomMovie(), audi, firstShowtime);
        showings.add(show);
        firstShowtime = firstShowtime.plusMinutes(timeBetweenShowings);
      }
      firstShowtime = LocalTime.of(10,00);//resets time for next auditorium
    }

    // Display schedule
    // for(Showing s_index : showings){
    //   System.out.println(s_index);
    // }

    firstShowtime = LocalTime.of(10,00); //resets time
  }

  public void sellTickets(){
    DecimalFormat formatter = new DecimalFormat("#,###.00");
    int totalTickets = 0; //tickets round up or not
    double totalRevenue = 0.0;

    for(Showing show : showings) {
      double percentage = show.getMovie().getPopularity() * 10;
      double showTickets = (show.getAuditorium().getCapacity() * percentage) / 100; //double or int rounding up
      double showRevenue = showTickets * ticketPrice;
      totalTickets += showTickets;
      totalRevenue += showRevenue;

      System.out.println(show);
      //System.out.println("Percentage of Tickets Sold: " + percentage + "%");
      System.out.println("Tickets Sold: " + showTickets + "\tTotal Revenue: $" + formatter.format(showRevenue));
    }

    System.out.println("\nToday's total tickets sold: " + formatter.format(totalTickets));
    System.out.println("Today's total revenue: $" + formatter.format(totalRevenue));
  }

}
