package net.minecartrapidtransit.path.core;

 

import net.minecartrapidtransit.path.constants.S;

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
		if (this.type.equals(S.type_FOOT)) {
			this.name = "";
			this.distance = 100;
		}
		else if (this.type.equals(S.type_BUS) || this.type.equals(S.type_WARP)) {
			this.distance = 1;
		}
		else if (this.type.equals(S.type_TRANSFER)) {
			this.distance = 10;
			this.name = destination.getName();
		}
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
}
