package edu.pdx.cs410J.nucciotj.project6;

import edu.pdx.cs410J.AirlineDumper;
import edu.pdx.cs410J.AirportNames;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrettyPrinter<T extends Airline> implements AirlineDumper<Airline> {

    private File file;
    private Writer output;

    /**
     * PrettyPrinter will write out to a file the airline name and all relevant flight information
     * in a sleek and aethetically pleasing format
     * @param filePath  filepath specifying where to pretty print airline and flights
     * @throws IOException
     */
    PrettyPrinter(String filePath) throws IOException {
        this.file = new File(filePath);
        this.output = new BufferedWriter(new FileWriter(this.file, true));
    }

    /**
     * The dump method will prettyprint airline and flight information out to the filepath
     * supplied to the constructor
     * @param airline airline from which to read flight information
     * @throws IOException
     */
    @Override
    public void dump(Airline airline) throws IOException {

        file.createNewFile();

        List<Flight> list = new ArrayList<>(airline.getFlights());

        Collections.sort(list);

        output.append("Airline Name: " + airline.getName() + "\n");

        for(int i = 0; i < list.size(); i++) {

            Flight flight = list.get(i);

            if(AirportNames.getName(flight.getSource()) == null) {
                throw new IllegalArgumentException("Airport " + flight.getSource() + " has not been found to be a valid airport.");
            } else if (AirportNames.getName(flight.getDestination()) == null) {
                throw new IllegalArgumentException("Airport " + flight.getDestination() + " has not been found to be a valid airport.");
            }

            long diffInMillies = Math.abs(flight.getArrival().getTime() - flight.getDeparture().getTime());
            long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);

            output.append("Flight Number: " + flight.getNumber() + "\n");
            output.append("    Source: " + AirportNames.getName(flight.getSource()) + "\n");
            output.append("    Departure Time: " + flight.getDTimeString() + flight.getDAMPMString() + ", on " + flight.getDDateString() + "\n");
            output.append("    Destination: " + AirportNames.getName(flight.getDestination()) + "\n");
            output.append("    Arrival Time: " + flight.getATimeString() + flight.getAAMPMString() + ", on " + flight.getADateString() + "\n");
            output.append("    Total Flight Time: " + diff + " mins" + "\n");

        }

        output.close();
    }

    /**
     * print will prettyprint airline and flight information to standard output, rather
     * than to a file. The - option must be supplied.
     * @param airline   airline from which to read flights
     * @throws IOException
     */
    public void print(Airline airline) throws IOException {

        List<Flight> list = new ArrayList<>(airline.getFlights());

        Collections.sort(list);

        System.out.print("Airline Name: " + airline.getName() + "\n");

        for(int i = 0; i < list.size(); i++) {

            Flight flight = list.get(i);

            if(AirportNames.getName(flight.getSource()) == null) {
                throw new IllegalArgumentException("Airport " + flight.getSource() + " has not been found to be a valid airport.");
            } else if (AirportNames.getName(flight.getDestination()) == null) {
                throw new IllegalArgumentException("Airport " + flight.getDestination() + " has not been found to be a valid airport.");
            }

            long diffInMillies = Math.abs(flight.getArrival().getTime() - flight.getDeparture().getTime());
            long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);

            System.out.print("Flight Number: " + flight.getNumber() + "\n");
            System.out.print("    Source: " + AirportNames.getName(flight.getSource()) + "\n");
            System.out.print("    Departure Time: " + flight.getDTimeString() + flight.getDAMPMString() + ", on " + flight.getDDateString() + "\n");
            System.out.print("    Destination: " + AirportNames.getName(flight.getDestination()) + "\n");
            System.out.print("    Arrival Time: " + flight.getATimeString() + flight.getAAMPMString() + ", on " + flight.getADateString() + "\n");
            System.out.print("    Total Flight Time: " + diff + " mins" + "\n");

        }

    }
}
