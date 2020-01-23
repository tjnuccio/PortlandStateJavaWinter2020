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
        System.err.println("Missing command line arguments");
        System.exit(1);
      } else if(args.length == 1) {
        System.err.println("Missing flight number");
        System.exit(1);
      } else if(args.length == 2) {
        System.err.println("Missing departure destination");
        System.exit(1);
      } else if(args.length == 3) {
        System.err.println("Missing departure date");
        System.exit(1);
      } else if(args.length == 4) {
        System.err.println("Missing departure time");
        System.exit(1);
      } else if(args.length == 5) {
        System.err.println("Missing arrival destination");
        System.exit(1);
      } else if(args.length == 6) {
        System.err.println("Missing arrival date");
        System.exit(1);
      } else if(args.length == 7) {
        System.err.println("Missing arrival time");
        System.exit(1);
      }
    }

    Flight flight = new Flight(Integer.parseInt(args[1]), args[2], args[4], args[3], args[5], args[7], args[6]);

    System.out.println(flight.toString());




    System.exit(0);

  }

}