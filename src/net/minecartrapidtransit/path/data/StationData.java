package net.minecartrapidtransit.path.data;

import java.util.LinkedList;
import java.util.List;

import net.minecartrapidtransit.path.constants.C;
import net.minecartrapidtransit.path.core.Connection;
import net.minecartrapidtransit.path.core.Station;

public class StationData {
	public StationData() {}
	public StationData(Station station) {
		this.name = station.getName();
		this.id = station.getId();
		this.connections = new LinkedList<ConnectionData>();
		for(Connection connection : station.getConnections()) {
			if(connection.getType() != C.type_TRANSFER){
				this.connections.add(new ConnectionData(connection));
			}
		}
	}
	
	public Station toStation(NetworkData network) {
		Station station = new Station(this.name, this.id, new LinkedList<Connection>());
		for(ConnectionData cd : connections){
			station.addConnection(cd.toConnection(network));
		}
		network.addStation(this.id, station);
		return station;
	}
	
	private String name;
	private String id;
	private List<ConnectionData> connections;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the connections
	 */
	public List<ConnectionData> getConnections() {
		return connections;
	}
	/**
	 * @param connections the connections to set
	 */
	public void setConnections(List<ConnectionData> connections) {
		this.connections = connections;
	}
	
	public LineStationData toLineStationData(String place){
		LineStationData lsd = new LineStationData();
		lsd.setConnections(connections);
		lsd.setId(id);
		lsd.setName(name);
		lsd.setPlace(place);
		return lsd;
	}
	
	
}
