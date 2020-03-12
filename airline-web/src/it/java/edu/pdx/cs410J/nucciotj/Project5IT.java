package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.hamcrest.CoreMatchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * An integration test for {@link Project5} that invokes its main method with
 * various arguments
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Project5IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");

//    @Test
//    public void producesError() {
//        MainMethodResult result = invokeMain(Project5.class, "-host", "localhost", "-port", "8080", "Air", "1", "PDX", "07/19/2020", "1:02", "pm", "ORD", "07/19/2020", "6:22", "pm");
//        assertThat(result.getExitCode(), CoreMatchers.equalTo(1));
//    }
//
//    @Test
//    public void doesNotProduceError() {
//        MainMethodResult result = invokeMain(Project5.class, "-host", "localhost", "-port", "8080", "Air", "1", "PDX", "07/19/2020", "1:02", "pm", "ORD", "07/19/2020", "6:22", "pm");
//        result = invokeMain(Project5.class, "-host", "localhost", "-port", "8080", "A");
//        assertThat(result.getExitCode(), CoreMatchers.equalTo(0));
//    }


}