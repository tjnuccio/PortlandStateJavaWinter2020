package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConverterIT extends InvokeMainTestCase {

    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Converter.class, args);
    }

    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain(Converter.class);
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Converter needs to be supplied with two file arguments"));
    }

    @Test
    public void testMoreThanTwoCommandLineArguments() {
        MainMethodResult result = invokeMain(Converter.class, "2", "3", "4");
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void anotherTest() {
        MainMethodResult result = invokeMain(Converter.class, "nucciotj.txt", "new.xml");
        assertThat(result.getExitCode(), equalTo(0));
    }
}
