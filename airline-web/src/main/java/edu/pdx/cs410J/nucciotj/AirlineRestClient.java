package edu.pdx.cs410J.nucciotj;

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

    public String searchFlights(String airlineName, String src, String dest) throws Exception {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("airlineName", airlineName);
        parameters.put("src", src);
        parameters.put("dest", dest);
        Response response = get(this.url, parameters);
        throwExceptionIfNotOkayHttpStatus(response);

        return response.getContent();
    }

    public String addAirline(Airline airline, Flight flight) throws Exception {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("airlineName", airline.getName());
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

    public void deleteAllData() throws Exception {
        Response response = delete(this.url, null);
        throwExceptionIfNotOkayHttpStatus(response);
    }

    private Response throwExceptionIfNotOkayHttpStatus(Response response) throws Exception {
        int code = response.getCode();
        if (code != HTTP_OK) {
            throw new Exception("Bad code!");
        }
        return response;
    }


}
