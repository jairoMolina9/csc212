/*
Jairo Molina
23499086
CSC 212-12A
Fall 2019
Assignment 3: Theater Simulation
Java program simulating a movie theater ticketing system
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
  public static void main (String [] args) throws FileNotFoundException {

    ArrayList<Movie> mov = new ArrayList<Movie> ();
    ArrayList<Auditorium> audi = new ArrayList<Auditorium> ();
    setData(mov, audi);

    Theater tr = new Theater(mov, audi);

    for(int i = 1; i <= 3; i++) {
      System.out.println("\n\t\t\t Day " + i + "\n"); //only prints day for beter aesthetics
      tr.schedule();
      tr.sellTickets();
    }
  } // end of main function

  public static void setData(ArrayList<Movie> mov, ArrayList<Auditorium> audi) throws FileNotFoundException {
    Scanner inFile = new Scanner(new File("auditoriums.txt"));

    while(inFile.hasNext()) {
      String []split_one = inFile.nextLine().split("\t");//splits by line
      String []split_two = split_one[0].split("\\s");//splits by space
      Auditorium a = new Auditorium(Integer.parseInt(split_two[0]), Integer.parseInt(split_two[1]));
      audi.add(a);
    }

    Scanner inFile2 = new Scanner (new File("movies.txt"));

    while(inFile2.hasNext()) {
      String []split_one = inFile2.nextLine().split("\t");//splits by line
      String []split_two = split_one[0].split("\\s");//splits by space

      if(Double.parseDouble(split_two[2]) > 10.0 || Double.parseDouble(split_two[2]) < 0) {
        System.out.println("The popularity input for movie '"+ split_two[0]+ "' is incorrect. Adjust and try again.");
        System.exit(0);
      }

      Movie m = new Movie(split_two[0],Integer.parseInt(split_two[1]), Double.parseDouble(split_two[2]));
      mov.add(m);
    }
  }//end of setData function

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

  public void setID(int i) {
    id = i;
  }

  public void setCapacity(int c) {
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
    if( !(showings.isEmpty()) ) showings.clear();

    for(Auditorium audi : auditoriums) {
      while(firstShowtime.compareTo(lastShowtime) != 0) { //until times are not equal
        Showing show = new Showing(randomMovie(), audi, firstShowtime);
        showings.add(show);
        firstShowtime = firstShowtime.plusMinutes(timeBetweenShowings);
      }
      firstShowtime = LocalTime.of(10,00);//resets time for next auditorium
    }
  }

  public void sellTickets(){
    DecimalFormat formatter = new DecimalFormat("#,###.00");
    double totalTickets = 0.0; //tickets are rounded up.
    double totalRevenue = 0.0;

    for(Showing show : showings) {
      double percentage = show.getMovie().getPopularity() * 10;
      double showingTickets = (show.getAuditorium().getCapacity() * percentage) / 100;
      double showingRevenue = showingTickets * ticketPrice;

      totalTickets += showingTickets;
      totalRevenue += showingRevenue;

      System.out.println(show);
      System.out.println("Tickets Sold: " + showingTickets + "\tTotal Revenue: $" + formatter.format(showingRevenue));
    }

    System.out.println("\nToday's total tickets sold: " + formatter.format(totalTickets));
    System.out.println("Today's total revenue: $" + formatter.format(totalRevenue));
  }

}
