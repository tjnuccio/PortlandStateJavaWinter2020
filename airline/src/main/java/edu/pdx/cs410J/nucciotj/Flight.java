package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.AbstractFlight;
import java.util.Date;

public class Flight extends AbstractFlight {

  private int flightNum;
  private String flightSrc;
  private String flightDest;
  private Date arrivalDate;
  private Date departDate;

  public Flight(int flightNum, String flightSrc, String departTime, String departDate, String flightDest, String arrivalTime, String arrivalDate) {

    String [] aDate;
    String [] aTime;
    String [] dDate;
    String [] dTime;

    if(flightNum < 0) {                                                         //Flight number
      throw new IllegalArgumentException("Flight number must be positive");
    }
    else {
      this.flightNum = flightNum;
    }

    //Flight Source
    if(flightSrc.length() != 3) {
      throw new IllegalArgumentException(flightSrc + " contains too many letters.");
    }
    else if(flightSrc.matches(".*\\d.*")) {
      throw new IllegalArgumentException(flightSrc + " contains integer values.");
    }
    else {
      this.flightSrc = flightSrc;
    }

    //Flight destination
    if(flightDest.length() != 3) {
      throw new IllegalArgumentException(flightDest + " contains too many letters.");
    }
    else if(flightDest.matches(".*\\d.*")) {
      throw new IllegalArgumentException(flightDest + " contains integer values.");
    }
    else {
      this.flightDest = flightDest;
    }

    //Arrival date                                                                         //Flight arrival date + time
    if(!arrivalDate.matches("\\d\\d?/\\d\\d?/\\d\\d\\d\\d")) {                      //Match input date to regex
      throw new IllegalArgumentException(arrivalDate + " is incorrectly formatted. Should be of format: m/d/yyyy");
    }
    else if(!arrivalTime.matches("\\d\\d?:\\d\\d")) {                               //Match input time to regex
      throw new IllegalArgumentException(arrivalTime + " is incorrectly formatted. Should be of format: hour:mins");
    }
    else {

      aDate = arrivalDate.split("/");         //Arrival date string parsed into array of strings containing year - 0, month - 1, and day - 2
      aTime = arrivalTime.split(":");         //Arrival time string parsed into array of strings containing hour - 0 and mins - 1

      if(Integer.parseInt(aTime[0]) > 23 || Integer.parseInt(aTime[1]) > 59) {            //Check for hours and mins out of bounds
        throw new IllegalArgumentException("Arrival time should formatted with hours between 0 - 23 and mins between 0 - 59.");
      }
      else if(Integer.parseInt(aDate[0]) > 12 || Integer.parseInt(aDate[1]) > 31) {       //Check for months and days our of bounds
        throw new IllegalArgumentException("Arrival date should formatted with month between 1 - 12 and day between 1 - 31.");
      }
      else {                                                                            //Create date object
        this.arrivalDate = new Date(Integer.parseInt(aDate[2]) - 1900, Integer.parseInt(aDate[0]) - 1, Integer.parseInt(aDate[1]), Integer.parseInt(aTime[0]), Integer.parseInt(aTime[1]));
      }
    }

    //Departure date
    if(!departDate.matches("\\d\\d?/\\d\\d?/\\d\\d\\d\\d")) {                      //Match input date to regex
      throw new IllegalArgumentException(departDate + " is incorrectly formatted. Should be of format: m/d/yyyy");
    }
    else if(!departTime.matches("\\d\\d?:\\d\\d")) {                               //Match input time to regex
      throw new IllegalArgumentException(departTime + " is incorrectly formatted. Should be of format: hour:mins");
    }
    else {

      dDate = departDate.split("/");         //Departure date string parsed into array of strings containing year - 0, month - 1, and day - 2
      dTime = departTime.split(":");         //Departure time string parsed into array of strings containing hour - 0 and mins - 1

      if(Integer.parseInt(dTime[0]) > 23 || Integer.parseInt(dTime[1]) > 59) {            //Check for hours and mins out of bounds
        throw new IllegalArgumentException("Departure time should formatted with hours between 0 - 23 and mins between 0 - 59.");
      }
      else if(Integer.parseInt(dDate[0]) > 12 || Integer.parseInt(dDate[1]) > 31) {       //Check for months and days our of bounds
        throw new IllegalArgumentException("Departure date should formatted with month between 1 - 12 and day between 1 - 31.");
      }
      else {                                                                            //Create date object
        this.departDate = new Date(Integer.parseInt(dDate[2]) - 1900, Integer.parseInt(dDate[0]) - 1, Integer.parseInt(dDate[1]), Integer.parseInt(dTime[0]), Integer.parseInt(dTime[1]));
      }
    }

  }

  @Override
  public int getNumber() {
    return this.flightNum;
  }

  @Override
  public String getSource() {
    return this.flightSrc;
  }

  @Override
  public String getArrivalString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDepartureString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDestination() {
    return this.flightDest;
  }

  @Override
  public Date getArrival() {
    return this.arrivalDate;
  }

  @Override
  public Date getDeparture() {
    return this.departDate;
  }
}
