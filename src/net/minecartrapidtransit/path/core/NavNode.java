package net.minecartrapidtransit.path.core;

 

import java.util.ArrayList;

public class NavNode extends Station {

	private boolean visited;
	private int distanceToStart;
	private NavNode prev;
	
	/**
	 * Constructor if no station Object is built. Cunstructs a new NavNode.
	 * @param name The name of the Station.
	 * @param id The ID of the Station (e.g. Y14).
	 * @param connections The connections of the station.
	 * @param the place that the station is in.
	 */
	public NavNode(String name, String id, ArrayList<Connection> connections) {
		super(name, id, connections);
		visited = false;
		distanceToStart = -1;
		prev = null;
	}

	/**
	 * Constructs a new NavNode.
	 * @param s The station represented by this NavNode.
	 */
	public NavNode(Station s) {
		super(s.getName(), s.getId(), s.getConnections());
		visited = false;
		distanceToStart = -1;
		prev = null;
	}

	/**
	 * @return true, if NavNode is already visited, else false.
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * @return the distance to the starting NavNode.
	 */
	public int getDistanceToStart() {
		return distanceToStart;
	}

	/**
	 * @return the prev
	 */
	public NavNode getPrev() {
		return prev;
	}

	/**
	 * @param visited true, if this node was visited, else false.
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * @param distanceToStart the distance to the starting NavNode
	 */
	public void setDistanceToStart(int distanceToStart) {
		this.distanceToStart = distanceToStart;
	}

	/**
	 * @param prev the previous NavNode.
	 */
	public void setPrev(NavNode prev) {
		this.prev = prev;
	}
}
