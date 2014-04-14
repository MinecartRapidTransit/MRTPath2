package net.minecartrapidtransit.path.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.minecartrapidtransit.path.constants.S;



public class Pathfinder {

	public static Route getShortestRoute(Network network, Place pStart, Place pEnd) {

		if(pStart == null) return null;
		if(pEnd == null) return null;
		
		List<NavNode> nodes = new LinkedList<NavNode>();
		// First get all Nodes
		for(Station station : network.getStations()){
			nodes.add(new NavNode(station));
		}
		// Add special start and end place nodes
		nodes.add(pStart.createStart()); // Has distance to start of 0
		nodes.add(pEnd.createEnd());
		
		// Finally we need a map (for lookup)
		Map<String, NavNode> node_lookup = new HashMap<String, NavNode>();
		for(NavNode node : nodes){
			node_lookup.put(node.getId(), node);
		}
		
		// Ok It is Algorithm time
		while(nodes.size() > 0){
			// Pick node with minimum distanceToStart (look at NavNode.compareTo() used by the min)
			NavNode min =  Collections.min(nodes); // Because NavNode implements Comparable
			nodes.remove(nodes.indexOf(min)); // Sets it as visited
			if(min.getId().equals(S.id_END)){
				// Woot we have found a smallest path.
				break;
			}
			
			// Now we look for connections
			for(Connection connection : min.getConnections()){
				NavNode destination = node_lookup.get(connection.getDestination().getId());
				if(nodes.contains(destination)){ // If it is not visited
					if(destination.getDistanceToStart() == -1){
						
						destination.setDistanceToStart(connection.getDistance() + min.getDistanceToStart());
						destination.setPrev(new Step(connection.getName(), connection.getType(), min));
						
					}else if(destination.getDistanceToStart() < connection.getDistance()){
						
						destination.setDistanceToStart(connection.getDistance() + min.getDistanceToStart());
						destination.setPrev(new Step(connection.getName(), connection.getType(), min));
						
					} // Else do nothing
				}
			}
			// And loop :D
		}
		
		// By now either we have failed or found the smallest path.
		if(nodes.size() == 0) return null; // Failure
		
		// Otherwise we can retrace our path from the finish into a Path object
		Route route = new Route();
		NavNode current = node_lookup.get(S.id_END);
		while(!current.getId().equals(S.id_START)){
			route.addStep(current.getPrev());
			current = current.getPrev().getNode();
		}
		
		
		return route;
		
	}

}
