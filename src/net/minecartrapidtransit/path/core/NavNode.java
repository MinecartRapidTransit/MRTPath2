package net.minecartrapidtransit.path.core;


public class NavNode extends Place {

	private boolean visited;
	private int distanceToStart;
	private NavNode prev;

	/**
	 * Constructs a new NavNode.
	 * @param p The place represented by this NavNode.
	 */
	public NavNode(Place p) {
		super(p.getName(), p.getId());
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
