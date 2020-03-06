package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.ParserException;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author TJ Nuccio
 * The converter class for the CS410J airline Project 4
 * The converter class will parse an airline and its flights from a textfile and then
 * dump the airline out to the xml file supplied.
 *
 * Command Line Args: (String textFile, String xmlFile)
 */
public class Converter {

    private static LinkedList<String> argList;

    public static void main(String[] args) throws ParserException, IOException {

        try {

            argList = new LinkedList<>(Arrays.asList(args));     //Holds program arguments

            if (argList.size() != 2) {
                throw new IllegalArgumentException("Converter needs to be supplied with two file arguments");
            }

            String textFile = argList.get(0);
            String xmlFile = argList.get(1);

            TextParser parser = new TextParser(textFile, null);
            Airline airline = parser.parse();

            XmlDumper dumper = new XmlDumper(xmlFile);
            dumper.dump(airline);

        } catch (IllegalArgumentException ex) {
            printErrorMessageAndExit(ex.getMessage());
        } catch (IOException e) {
            printErrorMessageAndExit(e.getMessage());
        } catch (ParserException e) {
            printErrorMessageAndExit(e.getMessage());
        } catch (NullPointerException e) {
            printErrorMessageAndExit(e.getMessage());
        }

        System.exit(0);

    }

    public static void printErrorMessageAndExit(String msg) {       //Prints provided message and exists
        System.err.println(msg);
        System.exit(1);
    }
}
