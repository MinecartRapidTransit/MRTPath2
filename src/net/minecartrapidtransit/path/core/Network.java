package net.minecartrapidtransit.path.core;

import java.util.HashMap;
import java.util.Map;

public class Network {

	Map<String, Place> places;
	
	/**
	 * Constructs a blank network.
	 */
	public Network() {
		places = new HashMap<String, Place>();
	}
	
	/**
	 * @param id The id of the place to get.
	 */
	public Place getPlaceByID(String id) {
		return places.get(id);
	}
	
	/*
	 * @param id The id of the place to add.
	 * @param place The place to add.
	 */
	public void addPlace(Place place) {
		places.put(place.getId(), place);
	}
	
	/**
	 * @returns an array of places.
	 */
	public Place[] getPlacesArray() {
		return (Place[]) places.values().toArray();
	}

	/**
	 * @return the places
	 */
	public Map<String, Place> getPlaces() {
		return places;
	}

	/**
	 * @param places the places to set
	 */
	public void setPlaces(Map<String, Place> places) {
		this.places = places;
	}
}
