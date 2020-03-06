package edu.pdx.cs410J.nucciotj;

import org.junit.Test;

import java.io.IOException;

public class XmlDumperTest {

    @Test
    public void checkXmlDumperCreatesFileAndDumpsFlights() throws IOException {
        String filePath = "something.xml";
        Airline airline = new Airline("Jet Blue");
        Flight f = new Flight(100, "PDX", "10:30", "AM", "3/15/2017", "JFK", "11:30", "PM", "3/16/2017");
        Flight f2 = new Flight(101, "PDX", "10:31", "AM", "3/16/2017", "JFK", "11:31", "PM", "3/17/2017");
        airline.addFlight(f);
        airline.addFlight(f2);
        XmlDumper xmldumper = new XmlDumper(filePath);
        xmldumper.dump(airline);
    }

}
