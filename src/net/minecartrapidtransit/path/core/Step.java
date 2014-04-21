package net.minecartrapidtransit.path.core;

public class Step {
	private Connection connection;
	private NavNode from;
	public Step(NavNode from, Connection connection){
		this.from = from;
		this.connection = connection;
	}
	
	public String getType(){
		return connection.getType();
	}
	
	public String getData(){
		return connection.getName();
	}
	
	public NavNode getFrom(){
		return from;
	}
	
	public Station getTo(){
		return connection.getDestination();
	}
	
	public Connection getConnection(){
		return connection;
	}

}
