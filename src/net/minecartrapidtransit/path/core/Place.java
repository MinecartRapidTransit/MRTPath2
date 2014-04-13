package net.minecartrapidtransit.path.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.minecartrapidtransit.path.constants.S;

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
		for(Station station : this.stations){
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
			otherStation.addConnection(new Connection(S.type_TRANSFER, 10, null, station));
			station.addConnection(new Connection(S.type_TRANSFER, 10, null, otherStation));
		}
		stations.add(station);
	}
	
	public List<Connection> getConnections() {
		List<Connection> l = new ArrayList<Connection>();
		for(Station s : stations){
			for (Connection c : s.getConnections()) {
				l.add(c);
			}
		}
		return l;
	}
}

