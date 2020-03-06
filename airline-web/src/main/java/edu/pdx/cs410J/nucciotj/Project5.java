package edu.pdx.cs410J.nucciotj;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

/**
 * The main class that parses the command line and communicates with the
 * Airline server using REST.
 */
public class Project5 {

    private static LinkedList<String> argList;
    private static LinkedList<String> optionList;

    public static void main(String[] args) {

        argList = new LinkedList<>(Arrays.asList(args));     //Holds program arguments
        optionList = new LinkedList<>();                     //Holds command line options

        checkForReadMeOption();         //Check for and print readme
        commandLineParser(argList, optionList);       //Separate args from options
        checkOptionList(optionList);
        checkArgList(argList);

        int port;
        try {
            port = Integer.parseInt(optionList.get(optionList.indexOf("-port") + 1));
            String hostName = optionList.get(optionList.indexOf("-host") + 1);

            AirlineRestClient client = new AirlineRestClient(hostName, port);

            if(argList.size() == 1) {


                String airlineName = argList.get(0);

                String msg = client.searchFlights(airlineName, null, null);
                System.out.println(msg);


            } else if(optionList.contains("-search")) {
                //Search for a flight between two airports
                //Pretty print all flights that originate in src and terminate in dst
                //Print message if there are no existing flights
                try {

                    String airlineName = argList.get(0);
                    String src = argList.get(1);
                    String dest  = argList.get(2);

                    String msg = client.searchFlights(airlineName, src, dest);
                    System.out.println(msg);

                } catch (Exception e) {
                    printErrorMessageAndExit("Error: " + e.getMessage());
                }

            } else {                                                                              //Add flight to server
                String flightNumString = argList.get(1);

                try {

                    Airline airline = new Airline(argList.get(0));      //Initialize airline

                    int flightNumber = Integer.parseInt(flightNumString);       //Convert flightNum string to integer
                    Flight flight = new Flight(flightNumber, argList.get(2), argList.get(4), argList.get(5), argList.get(3), argList.get(6), argList.get(8), argList.get(9), argList.get(7));     //Initialize flight


                    try {
                        String msg = client.addAirline(airline, flight);
                        System.out.println(msg);

                    if (optionList.contains("-print")) {
                        System.out.println(flight.toString());
                    }
                    } catch(IOException | RuntimeException e) {
                        printErrorMessageAndExit("Error at server: " + e.getMessage());
                    } catch (Exception e) {
                        printErrorMessageAndExit("Error: " + e.getMessage());
                    }


                } catch(NumberFormatException e) {
                    printErrorMessageAndExit("Flight number must be an integer");
                } catch (IllegalArgumentException ex) {
                    printErrorMessageAndExit(ex.getMessage());
                }

            }

        } catch (NumberFormatException ex) {
            printErrorMessageAndExit("Port \"" + optionList.get(optionList.indexOf("-port")) + "\" must be an integer");
            return;
        } catch (Exception e) {
            printErrorMessageAndExit("Error: " + e.getMessage());
        }

        System.exit(0);
    }

    public static void checkForReadMeOption() {
        if (argList.contains("-README")) {
            System.out.println("\nThis is a Project 5. The purpose of project 1 is to read in options and arguments from the command line,\n" +
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
                if (argList.get(i).matches("-host") || argList.get(i).matches("-port")) {
                    optionList.add(argList.remove(i));
                    optionList.add(argList.remove(i));
                } else {
                    optionList.add(argList.remove(i));
                }
                i--;
            }
        }

    }

    public static void checkArgList(LinkedList<String> argList) {       //Function for checking correct number of arguments provided on command line

        if (argList.size() == 0) {
            printErrorMessageAndExit("Missing command line arguments");
        } else if (optionList.contains("-search")) {
            if (argList.size() < 3) {          //Test cases for when too few command line arguments are supplied
                if (argList.size() == 0) {
                    printErrorMessageAndExit("Missing command line arguments");
                } else if (argList.size() == 1) {
                    printErrorMessageAndExit("Missing source airport");
                } else if (argList.size() == 2) {
                    printErrorMessageAndExit("Missing destination airport");
                }
            } else if (argList.size() > 3) {
                printErrorMessageAndExit("Too many arguments supplied for flight");
            }
        } else {
            if (argList.size() > 1 && argList.size() < 10) {          //Test cases for when too few command line arguments are supplied
                if (argList.size() == 2) {
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
            } else if (argList.size() > 10) {
                printErrorMessageAndExit("Too many arguments supplied for flight");
            }
        }
    }

    public static void checkOptionList(LinkedList<String> optionList) {

        checkForReadMeOption();

        if(!optionList.contains("-host") && !optionList.contains("-port")) {
            printErrorMessageAndExit("Host and port must be specified.");
        } else {
            if(optionList.size() < 4) {
                printErrorMessageAndExit("Host name and port name must be specified");
            }
        }

    }

    public static void printErrorMessageAndExit(String msg) {       //Prints provided message and exists
        System.err.println(msg);
        System.exit(1);
    }
}