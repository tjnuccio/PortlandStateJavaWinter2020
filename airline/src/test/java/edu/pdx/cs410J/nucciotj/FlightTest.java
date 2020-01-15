package edu.pdx.cs410J.nucciotj;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit testgit s for the {@link Flight} class.
 */
public class FlightTest {

  @Test
  public void getFlightNumEqualsTen() {
    int flightNum = 10;
    Flight flight = new Flight(flightNum, "AAA", "doesn't matter", "doesn't matter", "AAA", "doesn't matter", "doesn't matter");
    assertThat(flightNum, equalTo(flight.getNumber()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightNumberLessThanZeroThrowIllegalArgumentException() {
    int flightNum = -1;
    Flight flight = new Flight(flightNum, "AAA", "doesn't matter", "doesn't matter", "AAA", "doesn't matter", "doesn't matter");
  }

  @Test
  public void getFlightSourceWhenSetToAAA() {
    String src = "AAA";
    Flight flight = new Flight(1, src, "AAA", "doesn't matter", "AAA", "doesn't matter", "doesn't matter");
    assertThat(flight.getSource(), equalTo(src));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightSrcContainsMoreThan3DigitsThrowIllegalArgumentException() {
    String src = "AAAA";
    Flight flight = new Flight(1, src, "doesn't matter", "doesn't matter", "AAA", "doesn't matter", "doesn't matter");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightSrcContainsLessThan3DigitsThrowIllegalArgumentException() {
    String src = "AA";
    Flight flight = new Flight(1, src, "doesn't matter", "doesn't matter", "AAA", "doesn't matter", "doesn't matter");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightSourceContainsADigitThrowsIllegalArgumentException() {
    String src = "AA1";
    Flight flight = new Flight(1, src, "doesn't matter", "doesn't matter", "AAA", "doesn't matter", "doesn't matter");
  }

  @Test
  public void getFlightDestWhenSetToAAA() {
    String dest = "AAA";
    Flight flight = new Flight(1, "AAA", "doesn't matter", "doesn't matter", dest, "doesn't matter", "doesn't matter");
    assertThat(flight.getSource(), equalTo(dest));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightDestContainsMoreThan3DigitsThrowIllegalArgumentException() {
    String dest = "AAAA";
    Flight flight = new Flight(1, "AAA", "doesn't matter", "doesn't matter", dest, "doesn't matter", "doesn't matter");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightDestContainsLessThan3DigitsThrowIllegalArgumentException() {
    String dest = "AA";
    Flight flight = new Flight(1, "AAA", "doesn't matter", "doesn't matter", dest, "doesn't matter", "doesn't matter");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightDestContainsADigitThrowsIllegalArgumentException() {
    String dest = "AA1";
    Flight flight = new Flight(1, "AAA", "doesn't matter", "doesn't matter", dest, "doesn't matter", "doesn't matter");
  }

}
