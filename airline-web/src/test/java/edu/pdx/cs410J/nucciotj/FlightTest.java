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
  public void createFlight() {
    Flight flight = new Flight(10, "PDX", "10:30", "pm", "3/15/2017", "PDX", "11:00", "pm", "3/16/2018");
  }

  @Test
  public void getFlightNumEqualsTen() {
    int flightNum = 10;
    Flight flight = new Flight(flightNum, "CWA", "10:00", "am", "3/15/2017", "CWA", "11:00", "am", "3/15/2017");
    assertThat(flightNum, equalTo(flight.getNumber()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightNumberLessThanZeroThrowIllegalArgumentException() {
    int flightNum = -1;
    Flight flight = new Flight(flightNum, "CWA", "10:00", "am", "3/15/2017", "CWA", "11:00", "am", "3/15/2017");
  }

  @Test
  public void getFlightSourceWhenSetToPDX() {
    String src = "PDX";
    Flight flight = new Flight(1, src, "10:00", "am", "3/15/2017", "PDX", "11:00", "AM","3/15/2017");
    assertThat(flight.getSource(), equalTo(src));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightSrcContainsMoreThan3LettersThrowIllegalArgumentException() {
    String src = "AAAA";
    Flight flight = new Flight(1, src, "10:00", "am", "3/15/2017", "PDX", "11:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightSrcContainsLessThan3LettersThrowIllegalArgumentException() {
    String src = "AA";
    Flight flight = new Flight(1, src, "10:00", "am", "3/15/2017", "PDX", "11:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightSourceContainsADigitThrowsIllegalArgumentException() {
    String src = "AA1";
    Flight flight = new Flight(1, src, "10:00", "am", "3/15/2017", "PDX", "11:00", "am", "3/15/2017");
  }

  @Test
  public void getFlightDestWhenSetToPDX() {
    String dest = "PDX";
    Flight flight = new Flight(1, "PDX", "10:00", "am", "3/15/2017", dest, "11:00", "am", "3/15/2017");
    assertThat(flight.getDestination(), equalTo(dest));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightDestContainsMoreThan3LettersThrowIllegalArgumentException() {
    String dest = "AAAA";
    Flight flight = new Flight(1, "AAA", "10:00", "am", "3/15/2017", dest, "11:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightDestContainsLessThan3LettersThrowIllegalArgumentException() {
    String dest = "AA";
    Flight flight = new Flight(1, "AAA", "10:00", "am", "3/15/2017", dest, "11:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenFlightDestContainsADigitThrowsIllegalArgumentException() {
    String dest = "PD1";
    Flight flight = new Flight(1, "PDX", "10:00", "am", "3/15/2017", dest, "11:00", "am", "3/15/2017");
  }

  @Test
  public void getFlightArrivalForDateOfMarchFifteenthTwentySeventeenAndTimeOfElevenAM() {
    Date date = new Date((2017 - 1900), (3 - 1), 15, 11, 00);
    Flight flight = new Flight(1, "PDX", "10:00", "am", "3/15/2017", "PDX", "11:00", "am", "3/15/2017");
    assertThat(flight.getArrival(), equalTo(date));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateDoesNotContainTwoDashesThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "PDX", "10:00", "am", "3/15/2017", "PDX", "11:00", "am", "3152017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateDoesNotContainMonthDayYearThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "PDX", "10:00", "am", "3/15/2017", "PDX", "11:00", "am", "//");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateContainsTooManyDigitsForMonthThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "PDX", "10:00", "am", "3/15/2017", "PDX", "11:00", "am", "333/1/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateContainsMonthOver12ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "PDX", "10:00", "am", "3/15/2017", "PDX", "11:00", "am", "13/1/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateContainsDaysOver31ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "PDX", "10:00", "am", "3/15/2017", "PDX", "11:00", "am", "12/32/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateContainsTooManyDigitsForDayThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "CWA", "10:00", "am", "3/15/2017", "CWA", "11:00", "am", "3/100/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateContainsTooManyDigitsForYearThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "CWA", "10:00", "am", "3/15/2017", "CWA", "11:00", "am", "3/10/20177");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalDateContainsCharactersForMonthThrowException() {
    Flight flight = new Flight(1, "CWA", "10:00", "am", "3/15/2017", "CWA", "11:00", "am", "March/10/20177");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeDoesNotContainColonThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "CWA", "10:00", "am", "3/15/2017", "CWA", "1100", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeContainsTooManyDigitsForHourThrowException() {
    Flight flight = new Flight(1, "CWA", "10:00", "am", "3/15/2017", "CWA", "111:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeDoesNotContainHoursThrowException() {
    Flight flight = new Flight(1, "CWA", "10:00", "am", "3/15/2017", "CWA", ":00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeDoesNotContainMinsThrowException() {
    Flight flight = new Flight(1, "CWA", "10:00", "am", "3/15/2017", "CWA", "11:", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeHasHoursGreaterThan23ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "PDX", "10:00", "am", "3/15/2017", "PDX", "24:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeHasMinsGreaterThan59ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "PDX", "10:00", "am", "3/15/2017", "PDX", "23:61", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeHasNeagtiveHoursThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "PDX", "10:00", "am", "3/15/2017", "PDX", "-10:00", "am", "3/15/2017");
  }

  @Test
  public void getFlightDepartureForDateOfMarchFifteenthTwentySeventeenAndTimeOfElevenAM() {
    Date date = new Date((2017 - 1900), (3 - 1), 15, 11, 00);
    Flight flight = new Flight(1, "PDX", "11:00", "am", "3/15/2017", "PDX", "11:00", "am", "3/15/2017");
    assertThat(flight.getDeparture(), equalTo(date));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateDoesNotContainTwoDashesThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "PDX", "10:00", "am", "3152017", "PDX", "11:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateDoesNotContainMonthDayYearThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "PDX", "10:00", "am", "//", "PDX", "11:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateContainsTooManyDigitsForMonthThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "PDX", "10:00", "am", "333/15/2017", "PDX", "11:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateContainsMonthOver12ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "CWA", "10:00", "am", "13/15/2017", "CWA", "11:00", "am", "3/1/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateContainsDaysOver31ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "CWA", "10:00", "am", "3/32/2017", "CWA", "11:00", "am", "12/3/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateContainsTooManyDigitsForDayThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "CWA", "10:00", "am", "3/155/2017", "CWA", "11:00", "am", "3/10/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateContainsTooManyDigitsForYearThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "CWA", "10:00", "am", "3/15/20177", "CWA", "11:00", "am", "3/10/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureDateContainsCharactersForMonthThrowException() {
    Flight flight = new Flight(1, "CWA", "10:00", "am", "Feb/15/2017", "CWA", "11:00", "am", "3/10/20177");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureTimeDoesNotContainColonThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "CWA", "1000", "am", "3/15/2017", "CWA", "11:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureTimeContainsTooManyDigitsForHourThrowException() {
    Flight flight = new Flight(1, "CWA", "100:00", "am", "3/15/2017", "CWA", "11:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureTimeDoesNotContainHoursThrowException() {
    Flight flight = new Flight(1, "DRO", ":00", "am", "3/15/2017", "DRO", "11:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureTimeDoesNotContainMinsThrowException() {
    Flight flight = new Flight(1, "DRO", "10:", "am", "3/15/2017", "DRO", "11:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureTimeHasHoursGreaterThan23ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "DRO", "24:00", "am", "3/15/2017", "DRO", "11:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartureTimeHasMinsGreaterThan59ThrowIllegalArgumentException() {
    Flight flight = new Flight(1, "DRO", "10:61", "am", "3/15/2017", "DRO", "11:00", "am", "3/15/2017");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArrivalTimeIsBeforeDepartureTimeThrowException() {
    Flight flight = new Flight(1, "DRO", "10:01", "am", "4/15/2017", "DRO", "11:00", "am", "3/15/2017");
  }

}

