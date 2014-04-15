package net.minecartrapidtransit.path.core;

import java.util.ArrayList;
import java.util.List;


public class Pathfinder {

	public static Path getShortestRoute(List<Place> places, Place pStart, Place pEnd) {
		NavNode start = new NavNode(pStart);
		NavNode end = new NavNode(pEnd);
		NavNode current = start;
		
		if (start == null || end == null)
			return null;
		start.setDistanceToStart(0);
		
		List<NavNode> nn = new ArrayList<NavNode>();
		for (Place curr : places) {
			nn.add(new NavNode(curr));
		}
		
		List<Connection> c = current.getConnectionsWithoutTransfer();
		
		while (current != null && current != end) {
			for (Connection curr : c) {
				if(findNavNodeByPlace(curr.getDestination().getPlace(), nn).getDistanceToStart() == -1 || findNavNodeByPlace(curr.getDestination().getPlace(), nn).getDistanceToStart() > (current.getDistanceToStart() + curr.getDistance())) {
					findNavNodeByPlace(curr.getDestination().getPlace(), nn).setDistanceToStart(current.getDistanceToStart() + curr.getDistance());
					findNavNodeByPlace(curr.getDestination().getPlace(), nn).setPrev(current);
				}
			}
			current.setVisited(true);
			current = getNext(nn);
			c = current.getConnections();
		}
		
		current = end;
		String temp = "";
		while (current != start) {
			temp = "-" + findConnectionByNavNode(current.getPrev().getConnections(), current).getType() + "," + findConnectionByNavNode(current.getPrev().getConnections(), current).getName() + "-" + current.getId() + temp;
			current = current.getPrev();
		}
		
		return new Path(temp, places);
	}
	
	private static NavNode findNavNodeByPlace(Place p, List<NavNode> pN) {
		for (NavNode n : pN) {
			if (n.getId().equals(p.getId())) {
				return n;
			}
		}
		return null;
	}
	
	private static NavNode getNext(List<NavNode> pN) {
		int shortest = -1;
		NavNode next = null;
		for (NavNode n : pN) {
			if (n.getDistanceToStart() != -1 && !n.isVisited()) {
				if (shortest == -1 || shortest == 0) {
					shortest = n.getDistanceToStart();
					next = n;
				}
				else if (n.getDistanceToStart() < shortest) {
					shortest = n.getDistanceToStart();
					next = n;
				}
			}
		}
		return next;
	}
	
	private static Connection findConnectionByNavNode(List<Connection> pC, NavNode dest) {
		for (Connection c : pC) {
			if (c.getDestination().equals(dest)) {
				return c;
			}
		}
		return null;
	}
}
