package net.minecartrapidtransit.path.data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import net.minecartrapidtransit.path.core.Connection;
import net.minecartrapidtransit.path.core.Network;
import net.minecartrapidtransit.path.core.Place;
import net.minecartrapidtransit.path.core.Station;

public class NetworkData {
	
	public NetworkData() {}
	public NetworkData(Network network) {
		this.places = new HashMap<String, PlaceData>();
		for(Place place : network.getPlaces().values()) {
			this.places.put(place.getId(), new PlaceData(place));
		}
		
	}
	
	private Map<String, PlaceData> places;
	private Map<String, Station> stations;
	private Map<Connection, String> connections; // Only used when deconstructig
	
	/**
	 * @return the places
	 */
	public Map<String, PlaceData> getPlaces() {
		return places;
	}

	/**
	 * @param places the places to set
	 */
	public void setPlaces(Map<String, PlaceData> places) {
		this.places = places;
	}
	
	public Network toNetwork() {
		Network network = new Network();
		for(PlaceData placedata : places.values()) {
			network.addPlace(placedata.toPlace(this));
		}
		for(Connection connection : connections.keySet()){
			connection.setDestination(stations.get(connections.get(connection)));
		}
		return network;
	}
	
	public void addStation(String id, Station station){
		stations.put(id, station);
	}
	
	public void needsStation(Connection connection, String station){
		connections.put(connection, station);
	}
	
	
}
