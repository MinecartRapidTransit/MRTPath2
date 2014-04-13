package net.minecartrapidtransit.path.core;

 

import java.util.ArrayList;

public class Station {

	private String name;
	private String id;
	private ArrayList<Connection> connections;
	
	/**
	 * Constructs a new Station.
	 * @param name The name of the Station.
	 * @param id The ID of the Station (e.g. Y14).
	 * @param connections The connections of the station.
	 */
	public Station(String name, String id, ArrayList<Connection> connections) {
		super();
		this.name = name;
		this.id = id;
		this.connections = connections;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the connections
	 */
	public ArrayList<Connection> getConnections() {
		return connections;
	}

	/**
	 * @param connections The new ArrayList of connections.
	 */
	public void setConnections(ArrayList<Connection> connections) {
		this.connections = connections;
	}
}
