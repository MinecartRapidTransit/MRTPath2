package net.minecartrapidtransit.path.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import net.minecartrapidtransit.path.core.*;
import net.minecartrapidtransit.path.data.YamlDataStore;
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
