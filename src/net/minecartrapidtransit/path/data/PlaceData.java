package net.minecartrapidtransit.path.data;

import java.util.LinkedList;
import java.util.List;

import net.minecartrapidtransit.path.core.Place;
import net.minecartrapidtransit.path.core.Station;

public class PlaceData {
	
	public PlaceData() {} // For javabean spec
	public PlaceData(Place place){
		this.id = place.getId();
		this.name = place.getName();
		this.stations = new LinkedList<StationData>();
		for(Station station : place.getStations()) {
			this.stations.add(new StationData(station));
		}
	}
	
	public Place toPlace(NetworkData network){
		Place place = new Place(this.id, this.name);
		for(StationData sd : stations){
			place.addStation(sd.toStation(network));
		}
		return place;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the stations
	 */
	public List<StationData> getStations() {
		return stations;
	}
	/**
	 * @param stations the stations to set
	 */
	public void setStations(List<StationData> stations) {
		this.stations = stations;
	}
	private String id;
	private String name;
	private List<StationData> stations;
	
	
}
