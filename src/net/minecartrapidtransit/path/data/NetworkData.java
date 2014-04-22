package net.minecartrapidtransit.path.data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.minecartrapidtransit.path.core.Connection;
import net.minecartrapidtransit.path.core.Network;
import net.minecartrapidtransit.path.core.Place;
import net.minecartrapidtransit.path.core.Station;

public class NetworkData implements TLDTag {
	
	public NetworkData() {
		this.places = new HashMap<String, PlaceData>();
	}
	public NetworkData(Network network) {
		this.places = new HashMap<String, PlaceData>();
		for(Place place : network.getPlaces().values()) {
			this.places.put(place.getId(), new PlaceData(place));
		}
		
	}
	
	private Map<String, PlaceData> places;
	private Map<String, Station> stations;
	private Map<Connection, String> connections; // Only used when deconstructig
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
		connections = new HashMap<Connection, String>();
		stations = new HashMap<String, Station>();
		Network network = new Network();
		for(String key : places.keySet()) {
			network.addPlace(places.get(key).toPlace(this, key));
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
	
	// KEY CODE
	
	public void addPlacesData(PlacesData psd){
		Map<String, String> psdm = psd.getPlaces();
		for(String id : psdm.keySet()){
			if(!places.containsKey(id)){
				PlaceData pd = new PlaceData();
				pd.setName(psdm.get(id));
				places.put(id, pd);
			}
		}
	}
	
	public void addLineData(LineData line){
		for(LineStationData sd : line.getStations()){
			List<StationData> stations = places.get(sd.getPlace()).getStations();
			stations.add(sd);
			places.get(sd.getPlace()).setStations(stations);
		}
	}
	
	public PlacesData getPlacesData(){
		Map<String, String> psd = new HashMap<String, String>();
		for(String p : places.keySet()){
			psd.put(p, places.get(p).getName());
		}
		PlacesData pd = new PlacesData();
		pd.setPlaces(psd);
		pd.setName(String.format("Places in NetworkData (%s", name));
		return pd;
	}
	
	public LineData getLineData(){
		LineData ld = new LineData();
		List<LineStationData> stations = new LinkedList<LineStationData>();
		ld.setName(String.format("Stations in NetworkData (%s)", name));
		for(String id : places.keySet()){
			for(StationData sd : places.get(id).getStations()){
				stations.add(sd.toLineStationData(id));
			}
		}
		ld.setStations(stations);
		return ld;
	}
	
	public void merge(NetworkData other){
		LineData ld = other.getLineData();
		PlacesData psd = other.getPlacesData();
		addPlacesData(psd);
		addLineData(ld);
	}
	
	
	private static int count = 0;
	public static int count(){
		return count++;
	}
	
}
