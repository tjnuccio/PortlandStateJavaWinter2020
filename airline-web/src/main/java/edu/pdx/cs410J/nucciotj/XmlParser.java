package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * @author TJ Nuccio
 * XmlParser class for Project4. The XmlParser class implements the AirlineParser
 * interface. The XmlParser will read and parse a file supplied to it by the command line.
 */

public class XmlParser implements AirlineParser {

    /**
     * Constructor for TextParser class.
     * @param    filePath    filepath to be read from
     * @param    name       name of the airline supplied on the command line to compare against airline name in file
     */
    private File file;
    private String airlineName;
    private String xmlString;

    public XmlParser(String filePath, String name) {
        this.file = new File(filePath);                                      //File object
        this.airlineName = name;                                             //Airline name from command line
    }

    public XmlParser(String xmlString) {
        this.xmlString = xmlString;
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

        if(xmlString == null) {
            if (!file.exists() || file.length() == 0) {
                return null;
            }
        }

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;
            if(xmlString == null) {
                doc = dBuilder.parse(file);
            } else {
                doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));
            }
            doc.getDocumentElement().normalize();
            if(doc.getDocumentElement().getNodeName() != "airline") {
                throw new SAXException("XML's root tag should be 'airline");
            }

            NodeList l = doc.getElementsByTagName("name");
            Node n = l.item(0);
            Element e1 = (Element) n;
            String airName = e1.getTextContent();

            Airline airline = new Airline(airName);

            NodeList nList = doc.getElementsByTagName("flight");

            for(int i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nNode;

                    try {
                        String num = e.getElementsByTagName("number").item(0).getTextContent();
                        String src = e.getElementsByTagName("src").item(0).getTextContent();

                        Node dD = e.getElementsByTagName("date").item(0);
                        Element dDate = (Element) dD;
                        String day = dDate.getAttribute("day");
                        String month = dDate.getAttribute("month");
                        String year = dDate.getAttribute("year");

                        Node dT = e.getElementsByTagName("time").item(0);
                        Element dTime = (Element) dT;
                        String hour = dTime.getAttribute("hour");
                        String minute = dTime.getAttribute("minute");

                        if(minute.matches("\\w")) {
                            String newMinute = "0" + minute;
                            minute = newMinute;
                        }

                        String dest = e.getElementsByTagName("dest").item(0).getTextContent();

                        Node aD = e.getElementsByTagName("date").item(1);
                        Element aDate = (Element) aD;
                        String dayArrival = aDate.getAttribute("day");
                        String monthArrival = aDate.getAttribute("month");
                        String yearArrival = aDate.getAttribute("year");

                        Node aT = e.getElementsByTagName("time").item(1);
                        Element aTime = (Element) aT;
                        String hourArrival = aTime.getAttribute("hour");
                        String minuteArrival = aTime.getAttribute("minute");

                        if(minuteArrival.matches("\\w")) {
                            String newMinuteArrival = "0" + minuteArrival;
                            minuteArrival = newMinuteArrival;
                        }

                        String dAMPM;
                        if(Integer.parseInt(hour) < 12) {
                            dAMPM = "am";
                        } else {
                            String hourNew = Integer.toString(Integer.parseInt(hour) - 12);
                            hour = hourNew;
                            dAMPM = "pm";
                        }

                        String aAMPM;
                        if(Integer.parseInt(hourArrival) < 12) {
                            aAMPM = "am";
                        } else {
                            String hourArrivalNew = Integer.toString(Integer.parseInt(hourArrival) - 12);
                            hourArrival = hourArrivalNew;
                            aAMPM = "pm";
                        }

                        String dMonth = Integer.toString(Integer.parseInt(month) + 1);
                        String aMonth = Integer.toString(Integer.parseInt(monthArrival) + 1);

                        Flight f = new Flight(Integer.parseInt(num), src, hour + ":" + minute, dAMPM, dMonth + "/" + day + "/" + year, dest, hourArrival + ":" + minuteArrival, aAMPM, aMonth + "/" + dayArrival + "/" + yearArrival);

                        airline.addFlight(f);

                    } catch(NullPointerException ex) {
                        throw new NullPointerException("XML file is malformed. Missing appropriate elements specified by DTD.");
                    }

                } else {
                    throw new IllegalArgumentException("XML file malformed");
                }
            }

            return airline;

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
        }

        return null;
    }
}
