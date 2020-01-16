package edu.pdx.cs410J.nucciotj;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {

  public static void main(String[] args) {

    if(args.length != 8) {
      System.err.println("Incorrect number of command line arguments");
      System.exit(1);
    }
    else {

      Flight flight = new Flight(Integer.parseInt(args[1]), args[2], args[4], args[3], args[5], args[7], args[6]);

      flight.toString();

    }


    System.exit(0);
  }

}