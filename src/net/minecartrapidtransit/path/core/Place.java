package net.minecartrapidtransit.path.core;

import java.util.LinkedList;
import java.util.List;

import net.minecartrapidtransit.path.constants.C;

public class Place {
	private List<Station> stations;
	private String id;
	private String name;
	
	/**
	 * Constructor for the Place object.
	 * @param id The id of the place ("Like sctc").
	 * @param name The long name of the place ("Like Spawn City Transit Centre").
	 */
	public Place(String id, String name, List<Station> stations){
		this.id = id;
		this.name = name;
		this.stations = new LinkedList<Station>();
		for(Station station : stations){
			addStation(station);
		}
	}
	
	/**
	 * Constructor if no list of stations is given
	 * @param id
	 * @param name
	 */
	public Place(String id, String name){
		this(id, name, new LinkedList<Station>());
	}
	
	/**
	 * @return the id
	 * @return
	 */
	public String getId(){
		return id;
	}
	
	/**
	 * @return the name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * @param station The station to add to the place
	 */
	public void addStation(Station station){
		station.setPlace(this);
		for(Station otherStation : stations){
			otherStation.addConnection(new Connection(C.type_TRANSFER, C.transfer_cost, null, station));
			station.addConnection(new Connection(C.type_TRANSFER, C.transfer_cost, null, otherStation));
		}
		stations.add(station);
	}

	/**
	 * @return the stations
	 */
	public List<Station> getStations() {
		return stations;
	}

	/**
	 * @param stations the stations to set
	 */
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public NavNode createStart(){
		LinkedList<Connection> connections = new LinkedList<Connection>();
		for(Station station : stations){
			connections.add(new Connection(C.type_FOOT, 1, station.getName(), station));
		}
		NavNode nav = new NavNode(new Station(name, C.id_START, connections));
		nav.setDistanceToStart(0);
		return nav;
	}
	
	public NavNode createEnd(){
		Station endStation = new Station(name, C.id_END, new LinkedList<Connection>());
		for(Station station : stations){
			station.addConnection(new Connection(C.type_FOOT, 1, name, endStation));
		}
		return new NavNode(endStation);
	}
}

