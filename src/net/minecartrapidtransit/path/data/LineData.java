package net.minecartrapidtransit.path.data;

import java.util.List;

public class LineData implements TLDTag {
	private String name;
	private List<LineStationData> stations;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<LineStationData> getStations() {
		return stations;
	}
	public void setStations(List<LineStationData> stations) {
		this.stations = stations;
	}
}
