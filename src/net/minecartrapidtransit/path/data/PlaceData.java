package net.minecartrapidtransit.path.data;

import java.util.LinkedList;
import java.util.List;

import net.minecartrapidtransit.path.core.Place;
import net.minecartrapidtransit.path.core.Station;

public class PlaceData {
	
	public PlaceData() {} // For javabean spec
	public PlaceData(Place place){
		this.name = place.getName();
		this.stations = new LinkedList<StationData>();
		for(Station station : place.getStations()) {
			this.stations.add(new StationData(station));
		}
	}
	
	public Place toPlace(NetworkData network, String id){
		Place place = new Place(id, this.name);
		for(StationData sd : stations){
			place.addStation(sd.toStation(network));
		}
		return place;
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
	private String name;
	private List<StationData> stations;
	
	
}
