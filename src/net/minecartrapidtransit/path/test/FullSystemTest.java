package net.minecartrapidtransit.path.test;

import java.io.IOException;
import java.nio.charset.Charset;

import net.minecartrapidtransit.path.core.Network;
import net.minecartrapidtransit.path.core.Pathfinder;
import net.minecartrapidtransit.path.core.Route;
import net.minecartrapidtransit.path.data.YamlDataStore;
import net.minecartrapidtransit.path.directionGenerators.StandardDirectionGenerator;

import org.junit.Test;

public class FullSystemTest {

	@Test
	public void testEverything() throws IOException {
		Network network = new YamlDataStore().decodeNetwork(
				YamlDataStore.readFile("res/mrtnetwork.yml", Charset.defaultCharset()));
		Route route = Pathfinder.getShortestRoute(network, network.getPlaceByID("gund_valley"), network.getPlaceByID("cactusville"));
		for(String step : route.getDirections(new StandardDirectionGenerator())){
			System.out.println(step);
		}
	}

}
