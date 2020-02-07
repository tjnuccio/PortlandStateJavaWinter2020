package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.AirlineDumper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TJ Nuccio
 * TextDumper class for Project1. The TextDumper class implements the AirlineDumper
 * interface. The TextDumper will write out the airline and flights read in
 * from the file supplied by the command line. If the file supplied doesn't exist,
 * it will create a file and write out to that.
 */

public class TextDumper<T extends Airline> implements AirlineDumper<Airline> {

    private File file;
    private Writer output;

    /**
     * TextDumper Constructor
     * @param filePath  file supplied on the command line to be written out to
     * @throws IOException
     */
    public TextDumper(String filePath) throws IOException {
        this.file = new File(filePath);
        this.output = new BufferedWriter(new FileWriter(this.file, true));
    }

    /**
     * The dump method will write out the new flight to the file supplied by the command line.
     * @param airline   airline containing flights contained in the file and from the command line
     * @throws IOException
     */
    @Override
    public void dump(Airline airline) throws IOException {

        file.createNewFile();

        List<Flight> list = new ArrayList<>(airline.getFlights());

        Flight f = list.get(list.size() - 1);

        if(file.length() == 0) {
            output.append(airline.getName() + "\n");
        }

        output.append(f.getNumber() + "\n");
        output.append(f.getSource() + "\n");
        output.append(f.getDDateString() + "\n");
        output.append(f.getDTimeString() + "\n");
        output.append(f.getDAMPMString() + "\n");
        output.append(f.getDestination() + "\n");
        output.append(f.getADateString() + "\n");
        output.append(f.getATimeString() + "\n");
        output.append(f.getAAMPMString() + "\n");

        output.close();

        return;
    }
}
