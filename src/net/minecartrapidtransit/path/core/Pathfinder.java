package net.minecartrapidtransit.path.core;

import java.util.ArrayList;
import java.util.List;


public class Pathfinder {

	public static String getShortestRoute(List<Place> places, Place pStart, Place pEnd) {
		NavNode start = new NavNode(pStart);
		NavNode end = new NavNode(pEnd);
		NavNode current = start;
		
		if (start == null || end == null)
			return "ERR:WrongPlace(s)";
		start.setDistanceToStart(0);
		
		List<NavNode> nn = new ArrayList<NavNode>();
		for (Place curr : places) {
			nn.add(new NavNode(curr));
		}
		
		List<Connection> c = current.getConnectionsWithoutTransfer();
		int shortest = 0;
		NavNode next = null;
		
		
		for (Connection curr : c) {
			if(findNavNodeByPlace(curr.getDestination().getPlace(), nn).getDistanceToStart() == -1 || findNavNodeByPlace(curr.getDestination().getPlace(), nn).getDistanceToStart() > (current.getDistanceToStart() + curr.getDistance())) {
				findNavNodeByPlace(curr.getDestination().getPlace(), nn).setDistanceToStart(current.getDistanceToStart() + curr.getDistance());
			}
		}
		
		while (current != null && current != end) {
			
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
	
	private static NavNode findNavNodeByPlace(Place p, List<NavNode> pN) {
		for (NavNode n : pN) {
			if (n.getId().equals(p.getId())) {
				return n;
			}
		}
		return null;
	}
}
