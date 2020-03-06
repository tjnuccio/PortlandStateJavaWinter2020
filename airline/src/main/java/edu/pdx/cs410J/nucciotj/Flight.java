package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirportNames;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author TJ Nuccio
 * Flight class for Project1. Flight extends AbstractFlight and overrides all methods except
 * AbstractFlight.toString(). Flight contains all of the relevant information pertaining to
 * an airline flight: Flight number, flight source airport, flight departure time, flight departure
 * date, flight destination airport, destination arrival time, destination arrival date.
 */

public class Flight extends AbstractFlight implements Comparable<Flight> {

  private int flightNum;
  private String flightSrc;
  private String flightDest;
  private Date arrivalDate;
  private Date departDate;
  private String arrivalTimeString;       //For writing to file
  private String arrivalAMPMString;       //For writing to file
  private String arrivalDateString;       //For writing to file
  private String departureTimeString;     //For writing to file
  private String departureAMPMString;     //For writing to file
  private String departureDateString;     //For writing to file

  /**
   *
   * Constructor for Flight class.
   * @param    flightNum    number associated with a given fight
   * @param    flightSrc    source airport associated with a given flight
   * @param    departTime   departure time associated with a given flight (Format ex: 10:00)
   * @param    departAMPM   AM/PM selection
   * @param    departDate   departure date associated with a given flight (Format ex: 3/15/2017)
   * @param    flightDest   destination airport associated with a given flight
   * @param    flightSrc    destination airport for given flight
   * @param    arrivalTime  arrival time associated with a given flight (Format ex: 10:00)
   * @param    arrivalAMPM   AM/PM selection
   * @param    arrivalDate  arrival date associated with a given flight (Format ex: 3/15/2017)
   * @throws  IllegalArgumentException for incorrectly formatted parameters
   *
   */
  public Flight(int flightNum, String flightSrc, String departTime, String departAMPM, String departDate, String flightDest, String arrivalTime, String arrivalAMPM, String arrivalDate) throws IllegalArgumentException {

    this.arrivalTimeString = arrivalTime;
    this.arrivalDateString = arrivalDate;
    this.departureTimeString = departTime;
    this.departureDateString = departDate;
    this.arrivalAMPMString = arrivalAMPM;
    this.departureAMPMString = departAMPM;

    if(flightNum < 0) {                                                         //Flight number
      throw new IllegalArgumentException("Flight number must be positive");
    } else {
      this.flightNum = flightNum;
    }

    //Flight Source
    if(flightSrc.length() != 3) {
      throw new IllegalArgumentException("Flight source should be a three-letter code.");
    } else if (flightSrc.matches(".*\\d.*")) {
      throw new IllegalArgumentException(flightSrc + " contains integer values. Should be three-letter code.");
    } else {
      this.flightSrc = flightSrc;
    }

    if(AirportNames.getName(flightSrc) == null) {
      throw new IllegalArgumentException("Airport " + flightSrc + " has not been found to be a valid airport. Flight has not been added.");
    }

    //Flight destination
    if(flightDest.length() != 3) {
      throw new IllegalArgumentException("Flight destination should be three-letter code.");
    } else if(flightDest.matches(".*\\d.*")) {
      throw new IllegalArgumentException(flightDest + " contains integer values. Should be three-letter code.");
    } else {
      this.flightDest = flightDest;
    }

    if(AirportNames.getName(flightDest) == null) {
      throw new IllegalArgumentException("Airport " + flightDest + " has not been found to be a valid airport. Flight has not been added.");
    }

    //Arrival date                                                                         //Flight arrival date + time
    if(!arrivalDate.matches("\\d\\d?/\\d\\d?/\\d\\d\\d\\d")) {                      //Match input date to regex
      throw new IllegalArgumentException(arrivalDate + " is incorrectly formatted. Should be of format: m/d/yyyy");
    } else if(!arrivalTime.matches("\\d\\d?:\\d\\d")) {                               //Match input time to regex
      throw new IllegalArgumentException(arrivalTime + " is incorrectly formatted. Should be of format: hour:mins");
    } else if (!arrivalAMPM.equals("am") && !arrivalAMPM.equals("AM") && !arrivalAMPM.equals("pm") && !arrivalAMPM.equals("PM")) {
      throw new IllegalArgumentException("Arrival am/pm is incorrectly formatted. Should be one of: am / pm or AM / PM");
    } else {

      String [] aDate = arrivalDate.split("/");         //Arrival date string parsed into array of strings containing year - 0, month - 1, and day - 2
      String [] aTime = arrivalTime.split(":");         //Arrival time string parsed into array of strings containing hour - 0 and mins - 1

      if(Integer.parseInt(aTime[0]) > 12 || Integer.parseInt(aTime[1]) > 59) {            //Check for hours and mins out of bounds
        throw new IllegalArgumentException("Arrival time should formatted with hours between 1 - 12 and mins between 0 - 59.");
      } else if(Integer.parseInt(aDate[0]) > 12 || Integer.parseInt(aDate[1]) > 31) {       //Check for months and days our of bounds
        throw new IllegalArgumentException("Arrival date should formatted with month between 1 - 12 and day between 1 - 31.");
      } else {                                                                            //Create date object
        if(arrivalAMPM.equals("pm") || arrivalAMPM.equals("PM")) {
          aTime[0] += 12;
        }
        this.arrivalDate = new Date(Integer.parseInt(aDate[2]) - 1900, Integer.parseInt(aDate[0]) - 1, Integer.parseInt(aDate[1]), Integer.parseInt(aTime[0]), Integer.parseInt(aTime[1]));
      }
    }

    //Departure date
    if(!departDate.matches("\\d\\d?/\\d\\d?/\\d\\d\\d\\d")) {                      //Match input date to regex
      throw new IllegalArgumentException(departDate + " is incorrectly formatted. Should be of format: m/d/yyyy");
    } else if(!departTime.matches("\\d\\d?:\\d\\d")) {                               //Match input time to regex
      throw new IllegalArgumentException(departTime + " is incorrectly formatted. Should be of format: hour:mins");
    } else if (!departAMPM.equals("am") && !departAMPM.equals("AM") && !departAMPM.equals("pm") && !departAMPM.equals("PM")) {
      throw new IllegalArgumentException("Departure am/pm is incorrectly formatted. Should be one of: am / pm or AM / PM");
    } else {
      String [] dDate = departDate.split("/");         //Departure date string parsed into array of strings containing year - 0, month - 1, and day - 2
      String [] dTime = departTime.split(":");         //Departure time string parsed into array of strings containing hour - 0 and mins - 1

      if(Integer.parseInt(dTime[0]) > 12 || Integer.parseInt(dTime[1]) > 59) {            //Check for hours and mins out of bounds
        throw new IllegalArgumentException("Departure time should formatted with hours between 1 - 12 and mins between 0 - 59.");
      } else if(Integer.parseInt(dDate[0]) > 12 || Integer.parseInt(dDate[1]) > 31) {       //Check for months and days our of bounds
        throw new IllegalArgumentException("Departure date should formatted with month between 1 - 12 and day between 1 - 31.");
      } else {                                                                            //Create date object
        if(departAMPM.matches("pm") || departAMPM.matches("PM")) {
          dTime[0] += 12;
        }
        this.departDate = new Date(Integer.parseInt(dDate[2]) - 1900, Integer.parseInt(dDate[0]) - 1, Integer.parseInt(dDate[1]), Integer.parseInt(dTime[0]), Integer.parseInt(dTime[1]));
      }
    }

    if(arrivalDate.compareTo(departDate) < 0) {
      throw new IllegalArgumentException("Departure time must be before arrival time.");
    }

  }

  /**
   * Returns the flight number associated with a given flight.
   * @return  the flight number associated with a given flight
   */
  @Override
  public int getNumber() {
    return this.flightNum;
  }
  /**
   * Returns the source airport associated with a given flight.
   * @return  the source airport associated with a given flight
   */
  @Override
  public String getSource() {
    return this.flightSrc;
  }
  /**
   * Returns the arrival date and time associated with a given flight.
   * @return  a string comprised of the flight's arrival date and time
   */
  @Override
  public String getArrivalString() {
    String myString = DateFormat.getInstance().format(getArrival());
    return myString;
  }
  /**
   * Returns the departure date and time associated with a given flight.
   * @return  a string comprised of the flight's departure date and time
   */
  @Override
  public String getDepartureString() {
    String myString = DateFormat.getInstance().format(getDeparture());
    return myString;
  }
  /**
   * Returns destination airport code associated with a given flight.
   * @return  a string comprised of the flight's destination airport
   */
  @Override
  public String getDestination() {
    return this.flightDest;
  }
  /**
   * Returns the arrival date and time associated with a given flight.
   * @return  a string comprised of the flight's arrival date and time
   */
  @Override
  public Date getArrival() {
    return this.arrivalDate;
  }
  /**
   * Returns the departure date and time associated with a given flight.
   * @return  a date comprised of the flight's departure date and time
   */
  @Override
  public Date getDeparture() {
    return this.departDate;
  }

  public String getATimeString() {
    return this.arrivalTimeString;
  }

  public String getAAMPMString() {
    return this.arrivalAMPMString;
  }

  public String getADateString() {
    return this.arrivalDateString;
  }

  public String getDTimeString() {
    return this.departureTimeString;
  }

  public String getDAMPMString() {
    return this.departureAMPMString;
  }

  public String getDDateString() {
    return this.departureDateString;
  }

  @Override
  public int compareTo(Flight flight) {
    if(getSource().compareTo(flight.getSource()) < 0) {
      return -1;
    } else if (getSource().compareTo(flight.getSource()) > 0) {
      return 1;
    } else {

      if(getDeparture().after(flight.getDeparture())) {
        return 1;
      } else if (getDeparture().equals(flight.getDeparture())) {
        return 0;
      } else {
        return -1;
      }

    }
  }
}
