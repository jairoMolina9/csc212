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

  public static void main (String [] args) {
    ArrayList<Movie> mov = new ArrayList<Movie> ();
    ArrayList<Auditorium> audi = new ArrayList<Auditorium> ();

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
  }

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
      System.out.println("Number of Tickets Sold: " + showTickets);
      System.out.println("Total Revenue: $" + formatter.format(showRevenue));
    }

    System.out.println("\nToday's total tickets sold: " + totalTickets);
    System.out.println("Today's total revenue: $" + formatter.format(totalRevenue));
  }

}
