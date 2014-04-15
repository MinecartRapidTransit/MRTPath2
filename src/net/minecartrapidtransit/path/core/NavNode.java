package net.minecartrapidtransit.path.core;

public class NavNode extends Station implements Comparable<NavNode> {

	private int distanceToStart;
	private Step prev;

	/**
	 * Constructs a new NavNode.
	 * @param p The place represented by this NavNode.
	 */
	public NavNode(Station s) {
		super(s.getName(), s.getId(), s.getConnections());
		distanceToStart = -1;
		prev = null;
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
	public Step getPrev() {
		return prev;
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
	public void setPrev(Step prev) {
		this.prev = prev;
	}

	@Override
	public int compareTo(NavNode o) {
		boolean a = distanceToStart == -1;
		boolean b = o.getDistanceToStart() == -1;
		if(a & b){
			return 0;
		}else if(a){
			return 1;
		}else if(b)
			return -1;
		else{
			return Integer.compare(distanceToStart, o.getDistanceToStart());
		}
	}
}
