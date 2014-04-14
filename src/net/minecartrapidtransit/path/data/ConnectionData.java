package net.minecartrapidtransit.path.data;

import net.minecartrapidtransit.path.core.Connection;

public class ConnectionData {
	public ConnectionData() {}
	public ConnectionData(Connection connection){
		this.type = connection.getType();
		this.distance = connection.getDistance();
		this.name = connection.getName();
		this.to = connection.getDestination().getName();
	}
	
	public Connection toConnection(NetworkData network){
		Connection connection = new Connection(type, distance, name, null);
		network.needsStation(connection, to);
		return connection;
	}
	
	private String type;
	private int distance;
	private String name;
	private String to;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
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
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	
}
