package net.minecartrapidtransit.path.core;

import java.util.List;

public class Path {

	private List<Place> nodes;
	private List<Connector> connectors;
	private String path;
	
	public Path(String path, List<Place> network) {
		String[] s = path.split("-");
		for (int i = 0; i < s.length; i++) {
			nodes.add(findPlaceByID(network, s[i]));
			i++;
			if (i == s.length)
				break;
			String[] c = s[i].split(",");
			connectors.add(new Connector(c[0], c[1]));
		}
		this.path = path;
	}
	
	private static Place findPlaceByID(List<Place> network, String id) {
		for (Place p : network) {
			if (p.getId().equals(id))
				return p;
		}
		return null;
	}
	
	public List<Place> getNodes() {
		return nodes;
	}
	
	public List<Connector> getConnectors() {
		return connectors;
	}
	
	@Override
	public String toString() {
		return this.path;
	}
}
