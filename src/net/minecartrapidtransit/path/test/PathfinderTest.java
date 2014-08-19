package net.minecartrapidtransit.path.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.Charset;

import net.minecartrapidtransit.path.core.Network;
import net.minecartrapidtransit.path.core.Pathfinder;
import net.minecartrapidtransit.path.core.Route;
import net.minecartrapidtransit.path.data.YamlDataStore;

import org.junit.Test;
public class PathfinderTest {

	@Test
	public void testPrefersShorterPaths() throws IOException {
		Network network = new YamlDataStore().decodeNetwork(
				YamlDataStore.readFile("res/test/pathfinderTestYaml.yml", Charset.defaultCharset()));
		Route route = Pathfinder.getShortestRoute(network, network.getPlaceByID("a"), network.getPlaceByID("b"));
		String connection = route.getSteps().get(1).getConnection().getName();
		assertEquals("Good", connection);
	}

}
