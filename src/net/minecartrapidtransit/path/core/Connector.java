package net.minecartrapidtransit.path.core;

/**
 * This class looks similiar to the Connection class, but does not specify a distance or a destination. It is used in the Path class.
 */
public class Connector {

	private String type;
	private String name;
	
	/**
	 * @param type The type of the connection (see net.minecartrapidtransit.path.constants.S).
	 * @param name The name of the connection (e.g. Yellow Line to Morningside).
	 */
	public Connector(String type, String name) {
		this.type = type;
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	

}
