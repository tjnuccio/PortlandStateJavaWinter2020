package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TJ Nuccio
 * TextParser class for Project1. The TextParser class implements the AirlineParser
 * interface. The TextParser will read and parse a file supplied to it by the command line.
 */

public class TextParser implements AirlineParser {

    private File file;
    private String airlineName;
    private BufferedReader buffReader;
    /**
     * Constructor for TextParser class.
     * @param    filePath    filepath to be read from
     * @param    name       name of the airline supplied on the command line to compare against airline name in file
     */
    public TextParser(String filePath, String name) {
        try {
            this.file = new File(filePath);                                      //File object
            this.airlineName = name;                                             //Airline name from command line
            this.buffReader = new BufferedReader(new FileReader(file));           //Bufferreader for filereader
        } catch (FileNotFoundException e) {
        }
    }

    /**
     * The parse method will attempt to read the flights from the file supplied on the command
     * line. If the airline name is found not to match the one supplied on the commmand line, or
     * if the file is malformatted, a parser exception will be thrown to main.
     * @return  airline that is created from the airline name and flights found in the file
     * @throws  ParserException
     */
    @Override
    public Airline parse() throws ParserException {


        if(!file.exists() || file.length() == 0) {
            return null;
        }

        try {

            List<String> list;
            list = buffReader.lines().collect(Collectors.toList());

            String fileAirlineName = list.get(0);

            Airline airline = new Airline(airlineName);                             //Test against command line airline name

            if (!airline.getName().equals(fileAirlineName)) {
                throw new ParserException("Airline name provided does not match that of file.");
            }

            for (int i = 1; i < list.size(); i += 7) {

                try {
                    Integer.parseInt(list.get(i));                                    //Flight number
                } catch (NumberFormatException e) {
                    throw new ParserException("Flight number incorrectly formatted in file. Flight number should be 3 digits.");
                }

                if (list.get(i + 1).length() != 3 || list.get(i + 4).length() != 3) {
                    throw new ParserException("Airport codes incorrectly formatted in file. Airport codes should be 3 letters.");       //Airport codes
                } else if (list.get(i + 1).matches(".*\\d.*") || list.get(i + 4).matches(".*\\d.*")) {
                    throw new ParserException("Airport code incorrectly formatted in file. Airport codes should not contain digits.");
                }

                if (!list.get(i + 3).matches("\\d\\d?/\\d\\d?/\\d\\d\\d\\d") || !list.get(i + 6).matches("\\d\\d?/\\d\\d?/\\d\\d\\d\\d")) {                //Dates
                    throw new ParserException("Date incorrectly formatted in file. Should be of format: mm/dd/yyyy");
                }

                if (!list.get(i + 2).matches("\\d\\d?:\\d\\d") || !list.get(i + 5).matches("\\d\\d?:\\d\\d")) {
                    throw new ParserException("Time is incorrectly formatted in file. Should be of format: mm:hh");
                }

                Flight flight = new Flight(Integer.parseInt(list.get(i)), list.get(i + 1), list.get(i + 2), list.get(i + 3), list.get(i + 4), list.get(i + 5), list.get(i + 6));
                airline.addFlight(flight);
            }

            return airline;

        } catch (Exception e) {
            throw new ParserException(e.getMessage());
        }
    }
}
