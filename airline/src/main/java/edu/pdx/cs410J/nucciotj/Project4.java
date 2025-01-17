package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.ParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class Project4 {

  private static LinkedList<String> argList;
  private static LinkedList<String> optionList;

  public static void main(String[] args) throws FileNotFoundException, ParserException {

    argList = new LinkedList<>(Arrays.asList(args));     //Holds program arguments
    optionList = new LinkedList<>();                     //Holds command line options

    commandLineParser(argList, optionList);       //Separate args from options
    checkForReadMeOption(optionList);         //Check for and print readme
    checkArgList(argList);                    //Validate arg list is properly formatted

    String flightNumString = argList.get(1);

    try {

      int flightNumber = Integer.parseInt(flightNumString);       //Convert flightNum string to integer
      Flight flight = new Flight(flightNumber, argList.get(2), argList.get(4), argList.get(5), argList.get(3), argList.get(6), argList.get(8), argList.get(9), argList.get(7));     //Initialize flight

      if(optionList.contains("-textFile")) {                  //Textfile option selected

        String filePath = optionList.get(optionList.indexOf("-textFile") + 1);

        TextParser parser = new TextParser(filePath, argList.get(0));       //Call parser, add airline, then dump
        TextDumper dumper = new TextDumper(filePath);
        Airline airline = parser.parse();

        if(airline != null) {
          airline.addFlight(flight);
        } else {
          File file = new File(filePath);
          file.createNewFile();
          airline = new Airline(argList.get(0));
          airline.addFlight(flight);
        }

        dumper.dump(airline);

        if (optionList.contains("-print")) {
          System.out.println("NEW FLIGHT: ");
          System.out.println(flight.toString());
        }

        if(optionList.contains("-pretty")) {

          String filePath2 = optionList.get(optionList.indexOf("-pretty") + 1);

          PrettyPrinter p = new PrettyPrinter(filePath2);

          if(optionList.contains("-")) {
            p.print(airline);
          } else {
            p.dump(airline);
          }
        }

      } else if(optionList.contains("-xmlFile")) {                  //XmlFile option selected

        String filePath = optionList.get(optionList.indexOf("-xmlFile") + 1);

        XmlParser parser = new XmlParser(filePath, argList.get(0));       //Call parser, add airline, then dump
        Airline airline = parser.parse();

        if(airline != null) {
          airline.addFlight(flight);
        } else {
          File file = new File(filePath);
          file.createNewFile();
          airline = new Airline(argList.get(0));
          airline.addFlight(flight);
        }

        XmlDumper dumper = new XmlDumper(filePath);
        dumper.dump(airline);

        if (optionList.contains("-print")) {
          System.out.println("NEW FLIGHT: ");
          System.out.println(flight.toString());
        }

        if(optionList.contains("-pretty")) {

          String filePath2 = optionList.get(optionList.indexOf("-pretty") + 1);

          PrettyPrinter p = new PrettyPrinter(filePath2);

          if(optionList.contains("-")) {
            p.print(airline);
          } else {
            p.dump(airline);
          }
        }

      } else {
        Airline airline = new Airline(argList.get(0));      //Initialize airline
        airline.addFlight(flight);


        if (optionList.contains("-print")) {
          System.out.println(flight.toString());
        }

        if(optionList.contains("-pretty")) {

          String filePath = optionList.get(optionList.indexOf("-pretty") + 1);

          PrettyPrinter p = new PrettyPrinter(filePath);
          p.dump(airline);
        }
      }



    } catch (NumberFormatException ex) {
      printErrorMessageAndExit(flightNumString + " is incorrectly formatted. " + flightNumString + " should be an integer");
    } catch (IllegalArgumentException ex) {
      printErrorMessageAndExit(ex.getMessage());
    } catch (IOException e) {
      printErrorMessageAndExit(e.getMessage());
    } catch (ParserException e) {
      printErrorMessageAndExit(e.getMessage());
    } catch (NullPointerException e) {
      printErrorMessageAndExit(e.getMessage());
    }

    System.exit(0);
  }

  public static void checkForReadMeOption(LinkedList<String> optionList) {
    if (optionList.contains("-README")) {
      System.out.println("\nThis is a Project 4. The purpose of project 1 is to read in options and arguments from the command line,\n" +
              "parse them, construct two objects, and possibly print to standard output if the option is specified. \n" +
              "The program accepts two options: -print and -README. The -README option will print the README associated with \n" +
              "the program and then exit. The -print option will assemble the program objects, and display the data associated with \n" +
              "them both. The command line arguments are: int flightNum, String sourceDestination, String departTime, String departDate, \n" +
              "String arrivalDestination, String arrivalTime, String arrivalDate. The program will take these arguments, build a flight object,\n" +
              "build an airline object, and add the flight to the airline. Data pertaining to both is displayed with the -print option.\n");
      System.exit(0);
    }
  }

  public static void commandLineParser(LinkedList<String> argList, LinkedList<String> optionList) {

    for (int i = 0; i < argList.size(); i++) {                  //Command line parser

      if (argList.get(i).matches("-[a-zA-Z0-9]*")) {     //Separate program args from option
        if (argList.get(i).matches("-textFile") || argList.get(i).matches("-pretty") || argList.get(i).matches("-xmlFile")) {
          optionList.add(argList.remove(i));
          optionList.add(argList.remove(i));
          if(argList.get(i).equals("-")) {
            optionList.add(argList.remove(i));
          }
          i -= 1;
        } else {
          optionList.add(argList.remove(i));
          i--;
        }                   //Readjust index for removed element
      }
    }

    if(optionList.contains("-textFile") && optionList.contains("-xmlFile")) {
      throw new IllegalArgumentException("Error: -textFile and -xmlFile options cannot be used simultaneously.");
    }
  }

  public static void checkArgList(LinkedList<String> argList) {       //Function for checking correct number of arguments provided on command line

    if (argList.size() < 10) {          //Test cases for when too few command line arguments are supplied
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
        printErrorMessageAndExit("Missing departure time am/pm");
      } else if (argList.size() == 6) {
        printErrorMessageAndExit("Missing arrival airport");
      } else if (argList.size() == 7) {
        printErrorMessageAndExit("Missing arrival date");
      } else if (argList.size() == 8) {
        printErrorMessageAndExit("Missing arrival time");
      } else if (argList.size() == 9) {
        printErrorMessageAndExit("Missing arrival time am/pm");
      }
    } else if(argList.size() > 10) {
      printErrorMessageAndExit("Too many arguments supplied for flight");
    }
  }

  public static void printErrorMessageAndExit(String msg) {       //Prints provided message and exists
    System.err.println(msg);
    System.exit(1);
  }
}

