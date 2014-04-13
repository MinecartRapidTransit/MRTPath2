package net.minecartrapidtransit.path.core;

import java.util.List;


public class Pathfinder {

	public static String getShortestRoute(Place pStart, Place pEnd) {
		NavNode start = new NavNode(pStart);
		NavNode end = new NavNode(pEnd);
		NavNode current = start;
		
		if (start == null || end == null)
			return "ERR:WrongPlace(s)";
		start.setDistanceToStart(0);
		
		while (current != null && current != end) {
			List<Connection> c = current.getConnections();
			//TODO Add NavNode Class for stations and make seperate Pathfinder for Stations!
			/*
			for (Connection curr : c) {
				if(.getDestination().getDistanceToStart() == -1 || c.get(i).getNode().getDistanceToStart() > (current.getDistanceToStart() + c.get(i).getLength())) {
					
				}
			}
			current.setVisited(true);
			current = n.getNext();
			*/
		}
		
		//TODO Remove this
		return "";
	}
}
