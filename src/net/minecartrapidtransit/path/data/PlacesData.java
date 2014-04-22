package net.minecartrapidtransit.path.data;

import java.util.HashMap;
import java.util.Map;

public class PlacesData implements TLDTag {
	private Map<String, String> places;
	private String name;
	
	public PlacesData(){
		places = new HashMap<String, String>();
	}
	public Map<String, String> getPlaces() {
		return places;
	}
	public void setPlaces(Map<String, String> places) {
		this.places = places;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	
}
