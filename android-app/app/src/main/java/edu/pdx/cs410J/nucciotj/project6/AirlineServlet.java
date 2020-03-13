package edu.pdx.cs410J.nucciotj.project6;

import edu.pdx.cs410J.AirportNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author TJ Nuccio
 * This servlet ultimately provides a REST API for working with an
 * <code>Airline</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple dictionary of words
 * and their definitions.
 */
public class AirlineServlet extends HttpServlet {
  static final String AIRLINE_NAME_PARAMETER = "airline";
  static final String SRC_PARAMETER = "src";
  static final String DEST_PARAMETER = "dest";

  private final Map<String, Airline> airlines = new HashMap<>();

  /**
   * Returns the value of the HTTP request parameter with the given name.
   *
   * @return <code>null</code> if the value of the parameter is
   *         <code>null</code> or is the empty string
   */
  private String getParameter(String name, HttpServletRequest request) {
      String value = request.getParameter(name);
      if (value == null || "".equals(value)) {
          return null;
      } else {
          return value;
      }
  }
  /**
   * Handles an HTTP GET request from a client by writing the definition of the
   * word specified in the "word" HTTP parameter to the HTTP response.  If the
   * "word" parameter is not specified, all of the entries in the dictionary
   * are written to the HTTP response.
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      response.setContentType( "text/plain" );

      String airlineName = getParameter(AIRLINE_NAME_PARAMETER, request);
      String src = getParameter(SRC_PARAMETER, request);
      String dest = getParameter(DEST_PARAMETER, request);

      PrintWriter pw = response.getWriter();

      if(airlineName == null) {
          response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Airline name not supplied.");
          return;
      } else if(src == null && dest != null) {
          response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Source airport code was not supplied.");
          return;
      } else if(dest == null && src != null) {
          response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Destination airport code was not supplied.");
          return;
      }

      if(src == null && dest == null) {
          if (airlines.isEmpty()) {
              pw.println("No airlines currently on server.");
              pw.flush();
              return;
          } else {
              if(airlines.containsKey(airlineName)) {
                  Airline airline = airlines.get(airlineName);
                  XmlDumper dumper = new XmlDumper(response.getWriter());
                  dumper.dump(airline);
                  return;
              }

              if(!airlines.containsKey(airlineName)){
                  pw.println("Airline not found on server.");
                  pw.flush();
                  return;
              }
          }
          return;

      } else if(airlineName != null && src != null && dest != null) {

          if(airlines.containsKey(airlineName)) {


              if (src.length() != 3) {
                  response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Flight source should be a three-letter code.");
                  return;
              } else if (src.matches(".*\\d.*")) {
                  response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, src + " contains integer values. Should be three-letter code.");
                  return;
              } else if (AirportNames.getName(src) == null) {
                  response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Airport " + src + " has not been found to be a valid airport. Flight has not been added.");
                  return;
              }

              if (dest.length() != 3) {
                  response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Flight destination should be a three-letter code.");
                  return;
              } else if (dest.matches(".*\\d.*")) {
                  response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, dest + " contains integer values. Should be three-letter code.");
                  return;
              } else if (AirportNames.getName(dest) == null) {
                  response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Airport " + dest + " has not been found to be a valid airport.");
                  return;
              }

              Airline airline = airlines.get(airlineName);
              List<Flight> list = new ArrayList<>(airline.getFlights());

              Airline temp = new Airline(airlineName);

              for (int i = 0; i < list.size(); i++) {
                  if (list.get(i).getSource().equals(src) && list.get(i).getDestination().equals(dest)) {
                      temp.addFlight(list.get(i));
                  }
              }

              if(temp.getFlights().size() > 0) {
                  XmlDumper dumper = new XmlDumper(response.getWriter());
                  dumper.dump(temp);
                  return;
              } else {
                  pw.println("Flights starting at " + src + " and ending at " + dest + " were not found for airline " + airlineName + ".");
                  pw.flush();
                  return;
              }
          } else {
              pw.println("Airline is not on server.");
              pw.flush();
              return;
          }

      }
  }
  /**
   * Handles an HTTP POST request by storing the dictionary entry for the
   * "word" and "definition" request parameters.  It writes the dictionary
   * entry to the HTTP response.
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/plain");

      String airlineName = getParameter("airline", request);
      String flightNumber = getParameter("flightNumber", request);
      String src = getParameter("src", request);
      String departDate = getParameter("departDate", request);
      String departTime = getParameter("departTime", request);
      String departAMPM = getParameter("departAMPM", request);
      String dest = getParameter("dest", request);
      String arrivalDate = getParameter("arrivalDate", request);
      String arrivalTime = getParameter("arrivalTime", request);
      String arrivalAMPM = getParameter("arrivalAMPM", request);

      int flightNum;

      if (airlineName == null) {
          missingRequiredParameter(response, "airlineName");
          return;
      } else if(src == null) {
          missingRequiredParameter(response, "src");
          return;
      } else if(departDate == null) {
          missingRequiredParameter(response, "departDate");
          return;
      } else if(departTime == null) {
          missingRequiredParameter(response, "departTime");
          return;
      } else if(departAMPM == null) {
          missingRequiredParameter(response, "departAMPM");
          return;
      } else if(dest == null) {
          missingRequiredParameter(response, "dest");
          return;
      } else if(arrivalDate == null) {
          missingRequiredParameter(response, "arrivalDate");
          return;
      } else if(arrivalTime == null) {
          missingRequiredParameter(response, "arrivalTime");
          return;
      } else if(arrivalAMPM == null) {
          missingRequiredParameter(response, "arrivalAMPM");
          return;
      }

      try {
          flightNum = Integer.parseInt(flightNumber);

          PrintWriter pw = response.getWriter();
          Flight flight = new Flight(flightNum, src, departTime, departAMPM, departDate, dest, arrivalTime, arrivalAMPM, arrivalDate);

          if(airlines.containsKey(airlineName)) {
              airlines.get(airlineName).addFlight(flight);
              pw.println();
              pw.println("Airline " + airlineName + " had been updated with flight " + flightNumber);
              pw.flush();
          } else {
              Airline airline = new Airline(airlineName);
              airline.addFlight(flight);
              airlines.put(airlineName, airline);
              pw.println();
              pw.println("Airline " + airlineName + " has been added to server with flight " + flightNumber);
              pw.flush();
          }

          response.setStatus(HttpServletResponse.SC_OK);
          return;

      } catch (NumberFormatException e) {
          response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Flight num must be integer");
          return;
      } catch (IllegalArgumentException e) {
          response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
          return;
      } catch (Exception e) {
          response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
          return;
      }
  }

    private void missingRequiredParameter(HttpServletResponse response, String parameterName) throws IOException {

      String message = "Missing required parameter: " + parameterName;
      response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
      return;

    }

    private void writeAllFlightsToResponse(HttpServletResponse response) throws IOException {
      PrintWriter pw = response.getWriter();

      for(String key : airlines.keySet()) {

          List<Flight> list = new ArrayList<>(airlines.get(key).getFlights());

          Collections.sort(list);

          pw.println("Airline Name: " + key + "\n");
          for(int i = 0; i < list.size(); i++) {

              Flight flight = list.get(i);

              if(AirportNames.getName(flight.getSource()) == null) {
                  throw new IllegalArgumentException("Airport " + flight.getSource() + " has not been found to be a valid airport.");
              } else if (AirportNames.getName(flight.getDestination()) == null) {
                  throw new IllegalArgumentException("Airport " + flight.getDestination() + " has not been found to be a valid airport.");
              }

              long diffInMillies = Math.abs(flight.getArrival().getTime() - flight.getDeparture().getTime());
              long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);

              pw.println("Flight Number: " + flight.getNumber() + "\n");
              pw.println("    Source: " + AirportNames.getName(flight.getSource()) + "\n");
              pw.println("    Departure Time: " + flight.getDTimeString() + flight.getDAMPMString() + ", on " + flight.getDDateString() + "\n");
              pw.println("    Destination: " + AirportNames.getName(flight.getDestination()) + "\n");
              pw.println("    Arrival Time: " + flight.getATimeString() + flight.getAAMPMString() + ", on " + flight.getADateString() + "\n");
              pw.println("    Total Flight Time: " + diff + " mins" + "\n");

          }
      }
      pw.close();
      response.setStatus(HttpServletResponse.SC_OK);
  }
    /**
   * Handles an HTTP DELETE request by removing all dictionary entries.  This
   * behavior is exposed for testing purposes only.  It's probably not
   * something that you'd want a real application to expose.
   */
//  @Override
//  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      response.setContentType("text/plain");
//
//      this.airlines.clear();
//
//      PrintWriter pw = response.getWriter();
//      pw.println("Deleted all airlines + flights");
//      pw.flush();
//
//      response.setStatus(HttpServletResponse.SC_OK);
//  }

}
