package edu.pdx.cs410J.nucciotj;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Flight} class.
 */
public class FlightTest {

  @Test
  public void getFlightNumEqualsTen() {
    int flightNum = 10;
    Flight flight = new Flight(flightNum, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "3/15/2017");
    assertThat(flightNum, equalTo(flight.getNumber()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightNumberLessThanZeroThrowIllegalArgumentException() {
    int flightNum = -1;
    Flight flight = new Flight(flightNum, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "3/15/2017");
  }

  @Test
  public void getFlightSourceWhenSetToAAA() {
    String src = "AAA";
    Flight flight = new Flight(1, src, "10:00", "3/15/2017", "AAA", "11:00", "3/15/2017");
    assertThat(flight.getSource(), equalTo(src));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightSrcContainsMoreThan3LettersThrowIllegalArgumentException() {
    String src = "AAAA";
    Flight flight = new Flight(1, src, "10:00", "3/15/2017", "AAA", "11:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightSrcContainsLessThan3LettersThrowIllegalArgumentException() {
    String src = "AA";
    Flight flight = new Flight(1, src, "10:00", "3/15/2017", "AAA", "11:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightSourceContainsADigitThrowsIllegalArgumentException() {
    String src = "AA1";
    Flight flight = new Flight(1, src, "10:00", "3/15/2017", "AAA", "11:00", "3/15/2017");
  }

  @Test
  public void getFlightDestWhenSetToAAA() {
    String dest = "AAA";
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", dest, "11:00", "3/15/2017");
    assertThat(flight.getDestination(), equalTo(dest));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightDestContainsMoreThan3LettersThrowIllegalArgumentException() {
    String dest = "AAAA";
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", dest, "11:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightDestContainsLessThan3LettersThrowIllegalArgumentException() {
    String dest = "AA";
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", dest, "11:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightDestContainsADigitThrowsIllegalArgumentException() {
    String dest = "AA1";
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", dest, "11:00", "3/15/2017");
  }

  @Test
  public void getFlightArrivalForDateOfMarchFifteenthTwentySeventeenAndTimeOfElevenAM() {
    Date date = new Date((2017 - 1900), (3 - 1), 15, 11, 00);
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "3/15/2017");
    assertThat(flight.getArrival(), equalTo(date));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateDoesNotContainTwoDashesThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "3152017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateDoesNotContainMonthDayYearThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "//");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateContainsTooManyDigitsForMonthThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "333/1/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateContainsMonthOver12ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "13/1/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateContainsDaysOver31ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "12/32/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateContainsTooManyDigitsForDayThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "3/100/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateContainsTooManyDigitsForYearThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "3/10/20177");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateContainsCharactersForMonthThrowException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "11:00", "March/10/20177");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeDoesNotContainColonThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "1100", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeContainsTooManyDigitsForHourThrowException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "111:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeDoesNotContainHoursThrowException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", ":00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeDoesNotContainMinsThrowException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "11:", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeHasHoursGreaterThan23ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "24:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeHasMinsGreaterThan59ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "23:61", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeHasNeagtiveHoursThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/2017", "AAA", "-10:00", "3/15/2017");
  }

  @Test
  public void getFlightDepartureForDateOfMarchFifteenthTwentySeventeenAndTimeOfElevenAM() {
    Date date = new Date((2017 - 1900), (3 - 1), 15, 11, 00);
    Flight flight = new Flight(1, "AAA", "11:00", "3/15/2017", "AAA", "11:00", "3/15/2017");
    assertThat(flight.getDeparture(), equalTo(date));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateDoesNotContainTwoDashesThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3152017", "AAA", "11:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateDoesNotContainMonthDayYearThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "//", "AAA", "11:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateContainsTooManyDigitsForMonthThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "333/15/2017", "AAA", "11:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateContainsMonthOver12ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "13/15/2017", "AAA", "11:00", "3/1/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateContainsDaysOver31ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/32/2017", "AAA", "11:00", "12/3/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateContainsTooManyDigitsForDayThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/155/2017", "AAA", "11:00", "3/10/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateContainsTooManyDigitsForYearThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "10:00", "3/15/20177", "AAA", "11:00", "3/10/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateContainsCharactersForMonthThrowException() {
    Flight flight = new Flight(1, "AAA", "10:00", "Feb/15/2017", "AAA", "11:00", "3/10/20177");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureTimeDoesNotContainColonThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "1000", "3/15/2017", "AAA", "11:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureTimeContainsTooManyDigitsForHourThrowException() {
    Flight flight = new Flight(1, "AAA", "100:00", "3/15/2017", "AAA", "11:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureTimeDoesNotContainHoursThrowException() {
    Flight flight = new Flight(1, "AAA", ":00", "3/15/2017", "AAA", "11:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureTimeDoesNotContainMinsThrowException() {
    Flight flight = new Flight(1, "AAA", "10:", "3/15/2017", "AAA", "11:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureTimeHasHoursGreaterThan23ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "24:00", "3/15/2017", "AAA", "11:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureTimeHasMinsGreaterThan59ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "23:61", "3/15/2017", "AAA", "23:00", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureTimeHasNeagtiveHoursThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "AAA", "-10:00", "3/15/2017", "AAA", "10:00", "3/15/2017");
  }
}
