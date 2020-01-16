package edu.pdx.cs410J.nucciotj;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Airline} class.
 * */

public class AirlineTest {

    @Test
    public void getAirlineNameJetBlue() {
        String name = "Jet Blue";
        Airline airline = new Airline(name);
    }

}

