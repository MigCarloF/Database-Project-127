package letsfly.entities;

import java.util.ArrayList;

public class Country {
	private ArrayList<Airport> airports;

	public Country() {
		airports = new ArrayList<Airport>();
	}

	public ArrayList<Airport> getAirports() {
		return airports;
	}

	public void setAirports(ArrayList<Airport> airports) {
		this.airports = airports;
	}

	public void addAirport(Airport airport) {
		airports.add(airport);
	}

}
