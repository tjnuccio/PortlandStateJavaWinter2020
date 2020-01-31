package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.IOException;

public class TextDumper<T extends Airline> implements AirlineDumper<Airline> {

    /**
     * Dumps an airline to some destination.
     *
     * @param airline
     *        The airline being written to a destination
     *
     * @throws IOException
     *         Something went wrong while writing the airline
     */

    @Override
    public void dump(Airline airline) throws IOException {

        return;
    }
}
