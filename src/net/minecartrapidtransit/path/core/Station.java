package net.minecartrapidtransit.path.core;

 import java.util.ArrayList;
import java.util.List;

public class Station {

	private String name;
	private String id;
	private List<Connection> connections;
	private Place place;
	
	public Station(String name, String id) {
		super();
		this.name = name;
		this.id = id;
		this.connections = new ArrayList<Connection>();
	}
	
	/**
	 * Constructs a new Station.
	 * @param name The name of the Station.
	 * @param id The ID of the Station (e.g. Y14).
	 * @param connections The connections of the station.
	 */
	public Station(String name, String id, List<Connection> connections) {
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
	public List<Connection> getConnections() {
		return connections;
	}
	
	/**
	 * @return the place
	 */
	public Place getPlace(){
		return place;
	}
	
	/**
	 * @param place The place that the station is in.
	 */
	public void setPlace(Place place){
		this.place = place;
	}

	/**
	 * @param connections The new List of connections.
	 */
	public void setConnections(List<Connection> connections) {
		this.connections = connections;
	}
	
	/**
	 * @param connection The connection to add.
	 */
	public void addConnection(Connection connection){
		connections.add(connection);
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
