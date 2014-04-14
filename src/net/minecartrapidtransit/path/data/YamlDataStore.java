package net.minecartrapidtransit.path.data;

import java.util.LinkedList;

import net.minecartrapidtransit.path.constants.S;
import net.minecartrapidtransit.path.core.Connection;
import net.minecartrapidtransit.path.core.Network;
import net.minecartrapidtransit.path.core.Place;
import net.minecartrapidtransit.path.core.Station;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YamlDataStore implements DataFormat {

	@Override
	public String encodeNetwork(Network network) {
		NetworkData nd = new NetworkData(network);
		Yaml yaml = getYaml();
		return yaml.dump(nd);
	}

	@Override
	public Network decodeNetwork(String string) {
		return null;
	}
	
	private Yaml getYaml(){
		return new Yaml(new Constructor(Network.class));
	}
	
	//TODO remove this
	public static void main(String args[]){
		Network network = new Network();
		Place place1 = new Place("ARLI", "Arlington");
		Station station1 = new Station("TFM District Arlington", "TFM-1", new LinkedList<Connection>());
		Place place2 = new Place("RVTH", "Redstone Valley TransHUB");
		Station station2 = new Station("TFM District Redstone Valley", "TFM-2", new LinkedList<Connection>());
		Connection connection1 = new Connection(S.type_RAIL, 100, "TFM Northbound", station2);
		Connection connection2 = new Connection(S.type_RAIL, 100, "TFM Southbound", station1);
		
		station1.addConnection(connection1);
		station2.addConnection(connection2);
		place1.addStation(station1);
		place2.addStation(station2);
		network.addPlace(place1);
		network.addPlace(place2);
		System.out.println(new YamlDataStore().encodeNetwork(network));
	}
}
