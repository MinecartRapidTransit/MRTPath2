package net.minecartrapidtransit.path.core;

public class Connection {

	private String type;
	private int distance;
	private String name;
	private Station destination;

	/**
	 * Constructs a new Connection. Use types defined in constants.
	 * @param type The type of the connection.
	 * @param distance The distance to the destination / length of the connection. For foot, use 100; for Bus/Warp, use 1.
	 * @param name The name of the connnection (e.g. [Linename] [Directionbound]). Use an empty String, if type is foot. For transfer use [station name]
	 * @param destination The destination of the connection.
	 */
	public Connection(String type, int distance, String name, Station destination) {
		this.type = type;
		this.distance = distance;
		this.name = name;
		this.destination = destination;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the destination
	 */
	public Station getDestination() {
		return destination;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Station destination) {
		this.destination = destination;
	}
}
