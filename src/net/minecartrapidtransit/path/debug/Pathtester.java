package net.minecartrapidtransit.path.debug;

import java.util.ArrayList;
import java.util.List;

import net.minecartrapidtransit.path.constants.S;
import net.minecartrapidtransit.path.core.Connection;
import net.minecartrapidtransit.path.core.Pathfinder;
import net.minecartrapidtransit.path.core.Place;
import net.minecartrapidtransit.path.core.Station;

public class Pathtester {

	public static String testRVM() {
		List<Station> t = new ArrayList<Station>();
		List<Place> p = new ArrayList<Place>();
		
		//Station adding
		t.add(new Station("Ocelot Bay", "RVM1-1"));
		p.add(new Place("OCB", "Ocelot Bay"));
		p.get(0).addStation(t.get(0));
		
		t.clear();
		
		t.add(new Station("Redstone Valley Transit HUB", "RVM1-2"));
		t.add(new Station("Redstone Valley Transit HUB", "RVM2-1"));
		p.add(new Place("RVTH", "Redstone Valley Transit HUB"));
		p.get(1).addStation(t.get(0));
		p.get(1).addStation(t.get(1));
		
		t.clear();
		
		t.add(new Station("Redstone Valley Main street", "RVM1-3"));
		p.add(new Place("RVMS", "Redstone Valley Main street"));
		p.get(2).addStation(t.get(0));
		
		t.clear();
		
		t.add(new Station("Redstone Valley Fountain District", "RVM1-4"));
		p.add(new Place("RVFD", "Redstone Valley Fountain District"));
		p.get(3).addStation(t.get(0));
		
		t.clear();
		
		t.add(new Station("Redstone Valley City Hall", "RVM2-2"));
		p.add(new Place("RVCH", "Redstone Valley City Hall"));
		p.get(4).addStation(t.get(0));
		
		t.clear();
		
		t.add(new Station("Redstone Valley Central District", "RVM2-3"));
		p.add(new Place("RVFD", "Redstone Valley Central District"));
		p.get(5).addStation(t.get(0));
		
		t.clear();
		
		//Station connecting
		t = p.get(0).getStations();
		t.addAll(p.get(1).getStations());
		t.get(0).addConnection(new Connection(S.type_RAIL, 200, "RVM (1) to Redstone Valley Fountain District", t.get(1)));
		t.clear();
		
		t.addAll(p.get(1).getStations());
		t.addAll(p.get(2).getStations());
		t.addAll(p.get(4).getStations());
		t.addAll(p.get(0).getStations());
		t.get(0).addConnection(new Connection(S.type_RAIL, 200, "RVM (1) to Ocelot Bay", t.get(4)));
		t.get(0).addConnection(new Connection(S.type_RAIL, 50, "RVM (1) to Redstone Valley Fountain District", t.get(2)));
		t.get(1).addConnection(new Connection(S.type_RAIL, 50, "RVM (2) to Redstone Valley Central District", t.get(3)));
		t.clear();
		
		t.addAll(p.get(2).getStations());
		t.addAll(p.get(1).getStations());
		t.addAll(p.get(3).getStations());
		t.get(0).addConnection(new Connection(S.type_RAIL, 50, "RVM (1) to Ocelot Bay", t.get(1)));
		t.get(0).addConnection(new Connection(S.type_RAIL, 50, "RVM (1) to Redstone Valley Fountain District", t.get(3)));
		t.clear();
		
		t.addAll(p.get(3).getStations());
		t.addAll(p.get(2).getStations());
		t.get(0).addConnection(new Connection(S.type_RAIL, 50, "RVM (1) to Ocelot Bay", t.get(1)));
		t.clear();
		
		t.addAll(p.get(4).getStations());
		t.addAll(p.get(1).getStations());
		t.addAll(p.get(5).getStations());
		t.get(0).addConnection(new Connection(S.type_RAIL, 50, "RVM (2) to Redstone Valley Transit HUB", t.get(2)));
		t.get(0).addConnection(new Connection(S.type_RAIL, 50, "RVM (2) to Redstone Valley Central District", t.get(3)));
		t.clear();
		
		t.addAll(p.get(5).getStations());
		t.addAll(p.get(4).getStations());
		t.get(0).addConnection(new Connection(S.type_RAIL, 200, "RVM (2) to Redstone Valley Transit HUB", t.get(1)));
		t.clear();
		
		//Test
		return Pathfinder.getShortestRoute(p, p.get(0), p.get(5)).toString();
	}

	public static void main(String[] args) {
		System.out.println(testRVM());
	}
}
