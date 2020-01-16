package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Airline<T extends AbstractFlight> extends AbstractAirline<T> {

    private final String name;
    private Map<Integer, T> flights;

    /**
     * Returns the name of this airline.
     */
    public Airline (String name) {

        if(!name.matches("\\w+\\s*\\w*")) {
            throw new IllegalArgumentException("Airline name must not be blank.");
        }
        else {
            this.name = name;
        }
        this.flights = new HashMap<>();

    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Adds a flight to this airline.
     */
    @Override
    public void addFlight(T flight) {

        if(flights.containsKey(flight.getNumber())) {
            throw new IllegalArgumentException("Flight " + flight.getNumber() + " already exists for the current airline.");
        }
        else {
            flights.put(flight.getNumber(), flight);
        }
    }

    /**
     * Returns all of this airline's flights.
     */
    @Override
    public Collection<T> getFlights() {
        return this.flights.values();
    }

}