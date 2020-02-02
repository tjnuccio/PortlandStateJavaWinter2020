package edu.pdx.cs410J.nucciotj;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Airline} class.
 * */

public class AirlineTest {

    @Test
    public void getAirlineNameJetBlue() {
        String name = "Jet Blue";
        Airline<Flight> airline = new Airline<>(name);
        assertThat(airline.getName(), equalTo(name));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAirlineNameIsBlankThrowIllegalArgumentException() {
        String name = " ";
        Airline<Flight> airline = new Airline<>(name);
    }

    @Test
    public void addFlightToAirline() {
        Flight flight = new Flight(111, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "3/15/2017");
        Airline<Flight> airline = new Airline<>("Jet Blue");
        airline.addFlight(flight);
    }

    @Test
    public void whenAddingOneFlightCheckThatGetFlightsSizeEqualsOne() {
        Flight flight = new Flight(111, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "3/15/2017");
        Airline<Flight> airline = new Airline<>("Jet Blue");
        airline.addFlight(flight);

        ArrayList<Flight> list = new ArrayList<>(airline.getFlights());
        assertThat(list.size(), equalTo(1));
    }

    @Test
    public void afterAddingFlightToAirlineUseGetFlightsMethodAndCheckFlightMatchesWhatWasAdded() {
        Flight flight = new Flight(111, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "3/15/2017");
        Airline<Flight> airline = new Airline<>("Jet Blue");
        airline.addFlight(flight);

        ArrayList<Flight> list = new ArrayList<>(airline.getFlights());

        assertThat(list.get(0), equalTo(flight));
    }

//    @Test (expected = IllegalArgumentException.class)
//    public void whenAddingFlightWithSameFlightNumberToAirlineTwiceThrowException() {
//        Flight flight = new Flight(111, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "3/15/2017");
//        Airline<Flight> airline = new Airline<>("Jet Blue");
//        airline.addFlight(flight);
//        airline.addFlight(flight);
//    }

    @Test
    public void callAirlineConstructorWithNoArgsAndConfirmNotNull() {
        Airline airline = new Airline();
        assertThat(airline, is(notNullValue()));
    }
}

