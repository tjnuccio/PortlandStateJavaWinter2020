package edu.pdx.cs410J.nucciotj.project6;

import edu.pdx.cs410J.web.HttpRequestHelper;

import java.util.HashMap;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * A helper class for accessing the rest client.  Note that this class provides
 * an example of how to make gets and posts to a URL.  You'll need to change it
 * to do something other than just send dictionary entries.
 */
public class AirlineRestClient extends HttpRequestHelper
{
    private static final String WEB_APP = "airline";
    private static final String SERVLET = "flights";

    private final String url;

    /**
     * Creates a client to the airline REST service running on the given host and port
     * @param hostName The name of the host
     * @param port The port
     */
    public AirlineRestClient(String hostName, int port) {
        this.url = String.format( "http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET);
    }

    /**
     * The searchFlights method will retrieve an airline and its flights from the server.
     * If found, it will send back to main the xml string containing the airline and all
     * of its flights.
     * @param airlineName   airline name to search for
     * @param src           flight source
     * @param dest          flight destination
     * @return              string containing xml to be parsed by main class
     * @throws Exception
     */
    public String searchFlights(String airlineName, String src, String dest) throws Exception {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("airline", airlineName);
        if(src != null && dest != null) {
            parameters.put("src", src);
            parameters.put("dest", dest);
        }
        Response response = get(this.url, parameters);

        throwExceptionIfNotOkayHttpStatus(response);

        return response.getContent();
    }

    /**
     * The add airline method will add an airline to the server. If an airline
     * is already on the server, it will add the flight to the airline.
     * @param airline   name of airline
     * @param flight    flight to be added to airline
     * @return          string containing reponse message
     * @throws Exception
     */
    public String addAirline(Airline airline, Flight flight) throws Exception {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("airline", airline.getName());
        parameters.put("flightNumber", Integer.toString(flight.getNumber()));
        parameters.put("src", flight.getSource());
        parameters.put("departDate", flight.getDDateString());
        parameters.put("departTime", flight.getDTimeString());
        parameters.put("departAMPM", flight.getDAMPMString());
        parameters.put("dest", flight.getDestination());
        parameters.put("arrivalDate", flight.getADateString());
        parameters.put("arrivalTime", flight.getATimeString());
        parameters.put("arrivalAMPM", flight.getAAMPMString());

        Response response = post(this.url, parameters);
        throwExceptionIfNotOkayHttpStatus(response);

        return response.getContent();
    }

    /**
     * Method to check http response code
     * @param response
     * @return
     */
    private Response throwExceptionIfNotOkayHttpStatus(Response response) {
        int code = response.getCode();
        if (code != HTTP_OK) {
            if (response.getContent() != null) {
                throw new AirlineRestException(code, response.getContent());
            }
            throw new AirlineRestException(code);
        }
        return response;
    }

    class AirlineRestException extends RuntimeException {
        AirlineRestException(int httpStatusCode) {
            super("Got an HTTP Status Code of " + httpStatusCode);
        }
        AirlineRestException(int httpStatusCode, String message) {
            super("Got an HTTP Status Code of " + httpStatusCode + ". Reason: " + message);
        }
    }


}
