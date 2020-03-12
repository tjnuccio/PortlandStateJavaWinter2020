package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.ParserException;
import org.junit.Test;

import java.io.IOException;

public class XmlParserTest {

    @Test
    public void checkXmlDumperCreatesFileAndDumpsFlights() throws IOException, ParserException {
        String filePath = "src/test/resources/edu/pdx/cs410J/nucciotj/valid-airline.xml";
        XmlParser xmlparser = new XmlParser(filePath, "Valid Airlines");
        xmlparser.parse();
    }
}
