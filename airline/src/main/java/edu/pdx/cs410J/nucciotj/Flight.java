package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.AbstractFlight;

public class Flight extends AbstractFlight {

  private int flightNum;
  private String flightSrc;
  private String departTime;
  private String departDate;
  private String flightDest;
  private String arrivalTime;
  private String arrivalDate;

  public Flight(int flightNum, String flightSrc, String departTime, String departDate, String flightDest, String arrivalTime, String arrivalDate) {

    if(flightNum < 0) {
      throw new IllegalArgumentException("Flight number must be positive");
    }

    this.flightNum = flightNum;

    if(flightSrc.length() != 3) {
      throw new IllegalArgumentException(flightSrc + " contains too many letters.");
    }
    else if(flightSrc.matches(".*\\d.*")) {
      throw new IllegalArgumentException(flightSrc + " contains integer values.");
    }

    this.flightSrc = flightSrc;

    if(flightDest.length() != 3) {
      throw new IllegalArgumentException(flightDest + " contains too many letters.");
    }
    else if(flightDest.matches(".*\\d.*")) {
      throw new IllegalArgumentException(flightDest + " contains integer values.");
    }

    this.flightDest = flightDest;

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
  public String getDepartureString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDestination() {
    return this.flightDest;
  }

  @Override
  public String getArrivalString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
