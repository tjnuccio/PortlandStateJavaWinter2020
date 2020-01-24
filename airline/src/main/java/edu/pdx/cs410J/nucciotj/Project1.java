package edu.pdx.cs410J.nucciotj;

import java.util.Arrays;
import java.util.LinkedList;

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

    LinkedList<String> argList = new LinkedList<>(Arrays.asList(args));     //Holds program arguments
    LinkedList<String> optionList = new LinkedList<>();                     //Holds command line options

    for(int i = 0; i < argList.size(); i++) {
      if(argList.get(i).matches("-[a-zA-Z0-9]*")) {     //Extract options from command line
        optionList.add(argList.remove(i));                    //Add options to specific list
        i--;                                                  //Readjust index for removed element
      }
    }

    if (argList.size() < 8) {          //Test cases for when too few command line arguments are supplied
      if (argList.size() == 0) {
        printErrorMessageAndExit("Missing command line arguments");
      } else if (argList.size() == 1) {
        printErrorMessageAndExit("Missing flight number");
      } else if (argList.size() == 2) {
        printErrorMessageAndExit("Missing departure destination");
      } else if (argList.size() == 3) {
        printErrorMessageAndExit("Missing departure date");
      } else if (argList.size() == 4) {
        printErrorMessageAndExit("Missing departure time");
      } else if (argList.size() == 5) {
        printErrorMessageAndExit("Missing arrival destination");
      } else if (argList.size() == 6) {
        printErrorMessageAndExit("Missing arrival date");
      } else if (argList.size() == 7) {
        printErrorMessageAndExit("Missing arrival time");
      }
    } else {

      if(optionList.contains("-README")) {
        System.out.println("This is a Project 1. The purpose of project 1 is to read in options and arguments from the command line,\n" +
                            "parse them, construct two objects, and possibly print to standard output if the option is specified. \n" +
                            "The program accepts two options: -print and -README. The -README option will print the README associated with \n" +
                            "the program and exit. The -print option will assemble the program objects, and display the data associated with \n" +
                            "them both. The command line arguments are: int flightNum, String sourceDestination, String departTime, String departDate, \n" +
                            "String arrivalDestination, String arrivalTime, String arrivalDate. The program will take these arguments, build a flight object,\n" +
                            "build an airline object, and add the flight to the airline. Data pertaining to both is displayed with the -print option.");
        System.exit(0);
      } else {
        String flightNumString = argList.get(1);

        try {
          int flightNumber = Integer.parseInt(flightNumString);

          Airline<Flight> airline = new Airline<>(argList.get(0));
          Flight flight = new Flight(flightNumber, argList.get(2), argList.get(4), argList.get(3), argList.get(5), argList.get(7), argList.get(6));

          airline.addFlight(flight);

          if(optionList.contains("-print")) {
            System.out.println(flight.toString());
          }

        } catch (NumberFormatException ex) {
          printErrorMessageAndExit(flightNumString + " is incorrectly formatted. " + flightNumString + " should be an integer");

        } catch (IllegalArgumentException ex) {
          printErrorMessageAndExit(ex.getMessage());
        }
      }

      System.exit(0);
    }
  }

  public static void printErrorMessageAndExit(String msg) {
    System.err.println(msg);
    System.exit(1);
  }
}

