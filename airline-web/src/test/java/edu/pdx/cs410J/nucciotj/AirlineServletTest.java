package edu.pdx.cs410J.nucciotj;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link AirlineServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
public class AirlineServletTest {

//  @Test
//  public void addFlightStoresAirlineWithFlight() throws ServletException, IOException {
//    AirlineServlet servlet = new AirlineServlet();
//
//    String airlineName = "TEST AIRLINE";
//    int flightNumber = 123;
//
//    HttpServletRequest request = mock(HttpServletRequest.class);
//    when(request.getParameter("airline")).thenReturn(airlineName);
//    when(request.getParameter("flightNumber")).thenReturn(Integer.toString(flightNumber));
//
//    HttpServletResponse response = mock(HttpServletResponse.class);
//    PrintWriter pw = mock(PrintWriter.class);
//
//    when(response.getWriter()).thenReturn(pw);
//
//    servlet.doPost(request, response);
//
//    Airline<Flight> airline = servlet.getAirline(airlineName);
//    assertThat(airline, isNotNull());
//
//    Flight flight = airline.getFlights().iterator().next();
//    assertThat(flight.getNumber(), equalTo(flightNumber));
//
//  }
}
