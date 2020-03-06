package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.AirlineDumper;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TJ Nuccio
 * XmlDumper class for Project4. The XmlDumper class implements the AirlineDumper
 * interface. The XmlDumper will write out the airline and flights in xml read in
 * from the file supplied by the command line. If the file supplied doesn't exist,
 * it will create a file and write out to that.
 */

public class XmlDumper<T extends Airline> implements AirlineDumper<Airline> {

    private File file;
    private Writer output;
    protected final String SYSTEM_ID = "http://www.cs.pdx.edu/~whitlock/dtds/airline.dtd";
    protected final String PUBLIC_ID = "-//Portland State University//DTD CS410J Airline//EN";

    /**
     * TextDumper Constructor
     * @param filePath  file supplied on the command line to be written out to
     * @throws IOException
     */
    public XmlDumper(String filePath) throws IOException {
        this.file = new File(filePath);
        this.output = new FileWriter(this.file);
    }
    /**
     * The dump method will write out the new flight to the xmlfile supplied by the command line.
     * @param airline   airline containing flights contained in the file and from the command line
     * @throws IOException
     */
    @Override
    public void dump(Airline airline) throws IOException {

        //Create an empty document
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            DocumentType docType = dom.createDocumentType("airline", PUBLIC_ID, SYSTEM_ID);
            doc = dom.createDocument(null, "airline", docType);
            doc.setXmlStandalone(true);
        } catch (ParserConfigurationException ex) {
        } catch(DOMException ex) {
        }


        try {

            Element root = doc.getDocumentElement();
            List<Flight> list = new ArrayList<>(airline.getFlights());

            Element name = doc.createElement("name");
            String s1 = airline.getName();
            name.appendChild(doc.createTextNode(s1));
            root.appendChild(name);

            for(int i = 0; i < list.size(); i++) {

                Flight f = list.get(i);
                Element flight = doc.createElement("flight");

                Element number = doc.createElement("number");
                String s2 = Integer.toString(f.getNumber());
                number.appendChild(doc.createTextNode(s2));

                Element src = doc.createElement("src");
                String s3 = f.getSource();
                src.appendChild(doc.createTextNode(s3));

                Element depart = doc.createElement("depart");
                Element date = doc.createElement("date");
                Element time = doc.createElement("time");

                String [] dDate = f.getDDateString().split("/");
                String [] dTime = f.getDTimeString().split(":");

                date.setAttribute("day", dDate[1]);
                date.setAttribute("month", Integer.toString(Integer.parseInt(dDate[0]) - 1));
                date.setAttribute("year", dDate[2]);

                time.setAttribute("hour", dTime[0]);
                time.setAttribute("minute", dTime[1]);

                depart.appendChild(date);
                depart.appendChild(time);

                Element dest = doc.createElement("dest");
                String s4 = f.getDestination();
                dest.appendChild(doc.createTextNode(s4));

                Element arrive = doc.createElement("arrive");
                Element arrivalDate = doc.createElement("date");
                Element arrivalTime = doc.createElement("time");

                String [] aDate = f.getADateString().split("/");
                String [] aTime = f.getATimeString().split(":");

                arrivalDate.setAttribute("day", aDate[1]);
                arrivalDate.setAttribute("month", Integer.toString(Integer.parseInt(aDate[0]) - 1));
                arrivalDate.setAttribute("year", aDate[2]);

                arrivalTime.setAttribute("hour", aTime[0]);
                arrivalTime.setAttribute("minute", aTime[1]);

                arrive.appendChild(arrivalDate);
                arrive.appendChild(arrivalTime);

                flight.appendChild(number);
                flight.appendChild(src);
                flight.appendChild(depart);
                flight.appendChild(dest);
                flight.appendChild(arrive);

                root.appendChild(flight);
            }


        } catch(DOMException ex) {

        }

        //Write XML to file
        try {
            Source src = new DOMSource(doc);
            Result res = new StreamResult(output);
            TransformerFactory xFactory = TransformerFactory.newInstance();
            Transformer xform = xFactory.newTransformer();
            xform.setOutputProperty(OutputKeys.INDENT, "yes");
            xform.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, SYSTEM_ID);
            xform.transform(src, res);
        } catch (TransformerException ex) {
        }
    }
}
