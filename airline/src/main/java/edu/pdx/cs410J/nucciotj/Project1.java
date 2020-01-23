package edu.pdx.cs410J.nucciotj;

/**
 * @author TJ Nuccio
 * The main class for the CS410J airline Project
 * The main class reads in 7 command line arguments constituted of information pertaining to an airline
 * and corresponding flight. A -print and -README option is available as well. -print will print a string
 * containing all of the information supplied by the command line is a way that is neatly formatted. The -README
 * option will, instead of running the program, print to standard output the readme file associated with the program.
 *
 * Command Line Args: (int flightNum, String sourceDestination, String departTime, String departDate, String arrivalDestination, String arrivalTime, String arrivalDate)
 */
public class Project1 {

  public static void main(String[] args) {


    if(args.length != 8) {          //Test cases for when too few command line arguments are supplied
      if(args.length == 0) {
        printErrorMessageAndExit("Missing command line arguments");
      } else if(args.length == 1) {
        printErrorMessageAndExit("Missing flight number");
      } else if(args.length == 2) {
        printErrorMessageAndExit("Missing departure destination");
      } else if(args.length == 3) {
        printErrorMessageAndExit("Missing departure date");
      } else if(args.length == 4) {
        printErrorMessageAndExit("Missing departure time");
      } else if(args.length == 5) {
        printErrorMessageAndExit("Missing arrival destination");
      } else if(args.length == 6) {
        printErrorMessageAndExit("Missing arrival date");
      } else if(args.length == 7) {
        printErrorMessageAndExit("Missing arrival time");
      }
    } else {
      String flightNumString = args[1];
      try {
        int flightNumber = Integer.parseInt(flightNumString);
        Flight flight = new Flight(flightNumber, args[2], args[4], args[3], args[5], args[7], args[6]);
        Airline<Flight> airline = new Airline<>(args[0]);
        airline.addFlight(flight);
        flight.toString();
      } catch (NumberFormatException ex) {
        printErrorMessageAndExit(flightNumString + " is incorrectly formatted. "  + flightNumString + " should be an integer");
      } catch(IllegalArgumentException ex) {
        printErrorMessageAndExit(ex.getMessage());
      }
    }




    System.exit(0);

  }

  public static void printErrorMessageAndExit(String msg) {
    System.err.println(msg);
    System.exit(1);
  }

}