package edu.pdx.cs410J.nucciotj;

import edu.pdx.cs410J.AbstractAirline;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TJ Nuccio
 * Airline class for Project 1. Airline class extends AbstractAirline. Contains
 * the name of an airline. Each airline can have associated with it multiple flights.
 */

public class Airline<T extends Flight> extends AbstractAirline<T> {

    private final String name;
    private Map<Integer, T> flights;

    /**
     * Airline contructor.
     * @param name name of the airline
     * @throws IllegalArgumentException if airline name is left blank
     */
    public Airline (String name) {

        if(!name.matches("\\w+\\s*\\w*")) {
            throw new IllegalArgumentException("Airline name must not be blank.");
        } else {
            this.name = name;
        }

        this.flights = new HashMap<>();

    }
    /**
     * Returns the name of this airline. Airline constructor will ensure
     * that airline name is supplied when airline object is created.
     * @return  the name of the airline
     */

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Adds a flight to this airline. A flight with the same number cannot be added
     * to the same airline twice. Throws illegal argument exception if
     * flight with same number is being added twice.
     * @param   flight  flight to be added to the collection of airline's flights
     * @throws IllegalArgumentException if flight of same number is added twice
     */
    @Override
    public void addFlight(T flight) {

        if(flights.containsKey(flight.getNumber())) {
            throw new IllegalArgumentException("Flight " + flight.getNumber() + " already exists for the current airline.");
        } else {
            flights.put(flight.getNumber(), flight);
        }
    }

    /**
     * Returns all of this airline's flights.
     * @return Collection
     */
    @Override
    public Collection<T> getFlights() {
        return this.flights.values();
    }

}