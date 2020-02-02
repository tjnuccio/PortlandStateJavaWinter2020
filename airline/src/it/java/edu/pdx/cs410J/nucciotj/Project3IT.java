package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An integration test for the {@link Project3} main class.
 */
public class Project3IT extends InvokeMainTestCase {


    /**
     * Invokes the main method of {@link Project3} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project3.class, args );
    }
    /**
     * Tests that invoking the main method with no arguments issues an error
     */
    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain(Project3.class);
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
    }

    @Test
    public void runMainWithCorrectlyFormattedArguments() {
        MainMethodResult result = invokeMain(Project3.class,"Airline", "1", "AAA", "3/15/2017", "10:00",  "AAA",  "3/15/2017", "11:00");
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void runWithOneArgumentAssertMissingFlightNumber() {
        MainMethodResult result = invokeMain(Project3.class, "AirlineName");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing flight number"));
    }

    @Test
    public void runWithTwoArgumentsAssertMissingDepartureDestination() {
        MainMethodResult result = invokeMain(Project3.class, "AirlineName", "1");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing departure destination"));
    }

    @Test
    public void runWithThreeArgumentsAssertMissingDepartureDate() {
        MainMethodResult result = invokeMain(Project3.class, "AirlineName", "1", "aaa");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing departure date"));
    }

    @Test
    public void runWithFourArgumentsAssertMissingDepartureTime() {
        MainMethodResult result = invokeMain(Project3.class, "AirlineName", "1", "AAA", "aaa");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing departure time"));
    }

    @Test
    public void runWithFiveArgumentsAssertMissingArrivalDestination() {
        MainMethodResult result = invokeMain(Project3.class, "AirlineName", "1", "AAA", "aaa", "aaa");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing arrival destination"));
    }

    @Test
    public void runWithSixArgumentsAssertMissingArrivalDate() {
        MainMethodResult result = invokeMain(Project3.class, "AirlineName", "1", "AAA", "aaa", "aaa", "aaa");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing arrival date"));
    }

    @Test
    public void runWithSevenArgumentsAssertMissingArrivalTime() {
        MainMethodResult result = invokeMain(Project3.class, "AirlineName", "1", "AAA", "aaa", "aaa", "aaa", "aaa");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing arrival time"));
    }

    @Test
    public void whenFlightSourceContainsNumberIllegalArgumentExceptionShouldBeCaughtInMain() {
        MainMethodResult result = invokeMain(Project3.class, "AirlineName", "1", "AA1", "aaa", "aaa", "aaa", "aaa", "aaa");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("AA1 contains integer values."));
    }

    @Test
    public void runMainWithCorrectlyFormattedArgumentsAndCheckStandardOutIsCorrect() {
        MainMethodResult result = invokeMain(Project3.class,"-print", "Airline", "1", "AAA", "3/15/2017", "10:00",  "AAA",  "3/15/2017", "11:00");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Flight 1 departs AAA at Wed Mar 15 10:00:00 PDT 2017 arrives AAA at Wed Mar 15 11:00:00 PDT 2017"));
    }

    @Test
    public void whenFileIsNotSuppliedAndTextFileOptionProvidedExitStatusOne() {
        MainMethodResult result = invokeMain(Project3.class,"-print", "-textFile", "Airline", "1", "AAA", "3/15/2017", "10:00",  "AAA",  "3/15/2017", "11:00");
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void whenFileIsSuppliedAndTextFileOptionSuppliedExitZero() {
        MainMethodResult result = invokeMain(Project3.class,"-print", "-textFile", "src/test/resources/edu/pdx/cs410J/nucciotj/data.txt", "Jet Blue", "1", "AAA", "3/15/2017", "10:00",  "AAA",  "3/15/2017", "11:00");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Flight 1 departs AAA at Wed Mar 15 10:00:00 PDT 2017 arrives AAA at Wed Mar 15 11:00:00 PDT 2017"));
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void whenTextFileProvidedIsFormattedIncorrectlyExitOne() {
        MainMethodResult result = invokeMain(Project3.class,"-print", "-textFile", "~/ dat a.txt", "Airline", "1", "AAA", "3/15/2017", "10:00",  "AAA",  "3/15/2017", "11:00");
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void airlineNamesDontMatchBetweenFileAndCommandLine() {
        MainMethodResult result = invokeMain(Project3.class,"-print", "-textFile", "src/test/resources/edu/pdx/cs410J/nucciotj/data.txt", "Jet Ble", "1", "AAA", "3/15/2017", "10:00",  "AAA",  "3/15/2017", "11:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("Airline name provided does not match that of file."));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void whenTextFileProvidedDoesntExistCreateOne() {
        MainMethodResult result = invokeMain(Project3.class,"-print", "-textFile", "src/test/resources/edu/pdx/cs410J/nucciotj/dta.txt", "Airline", "1", "AAA", "3/15/2017", "10:00",  "AAA",  "3/15/2017", "11:00");
        assertThat(result.getExitCode(), equalTo(0));
    }

}