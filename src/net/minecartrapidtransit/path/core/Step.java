package net.minecartrapidtransit.path.core;

public class Step {
	private String type;
	private String data;
	private NavNode from;
	public Step(String type, String data, NavNode from){
		this.type = type;
		this.data = data;
		this.from = from;
	}
	
	public String getType(){
		return type;
	}
	
	public String getData(){
		return data;
	}
	
	public NavNode getNode(){
		return from;
	}
}
