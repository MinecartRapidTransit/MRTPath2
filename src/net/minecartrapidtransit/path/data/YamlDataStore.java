package net.minecartrapidtransit.path.data;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.minecartrapidtransit.path.core.Network;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

public class YamlDataStore implements DataFormat {

	@Override
	public String encodeNetwork(Network network) {
		NetworkData nd = new NetworkData(network);
		return encodeTag(nd);
	}

	@Override
	public Network decodeNetwork(String string) {
		List<TLDTag> tags = decodeTags(string);
		NetworkData nd = new NetworkData();
		for(TLDTag tag : tags){
			if(tag instanceof NetworkData){
				nd.merge((NetworkData) tag); 
			}else if(tag instanceof PlacesData){
				nd.addPlacesData((PlacesData) tag);
			}else if(tag instanceof LineData){
				nd.addLineData((LineData) tag);
			}
		}
		return nd.toNetwork();
	}
	
	public String encodeTag(TLDTag tag){
		Representer representer = new Representer();
		representer.setPropertyUtils(new CustomPropertyUtils());
		representer.addClassTag(NetworkData.class, new Tag("!network"));
		representer.addClassTag(PlacesData.class, new Tag("!places"));
		representer.addClassTag(LineData.class, new Tag("!line"));
		Yaml yaml = new Yaml(representer);
		return yaml.dump(tag);
	}
	
	public List<TLDTag> decodeTags(String string){
		List<TLDTag> tags = new LinkedList<TLDTag>();
		Constructor constructor = new Constructor();
		constructor.addTypeDescription(new TypeDescription(NetworkData.class, "!network"));
		constructor.addTypeDescription(new TypeDescription(PlacesData.class, "!places"));
		constructor.addTypeDescription(new TypeDescription(LineData.class, "!line"));
		Yaml yaml = new Yaml(constructor);
		for(Object object : yaml.loadAll(string)){
			tags.add((TLDTag) object);
		}
		return tags;
	}
	
	public String encodeTags(List<TLDTag> tags){
		StringBuilder sb = new StringBuilder();
		for(TLDTag tag : tags){
			sb.append("---\n");
			sb.append(encodeTag(tag));
		}
		return sb.toString();
	}
	
	private class CustomPropertyUtils extends PropertyUtils {
        @Override
        protected Set<Property> createPropertySet(Class<? extends Object> type, BeanAccess bAccess)
                throws IntrospectionException {
            Set<Property> result = new TreeSet<Property>(new CustomComparator());
            result.addAll(super.createPropertySet(type, bAccess));
            return result;
        }		
        private class CustomComparator implements Comparator<Property> {

			@Override
			public int compare(Property o1, Property o2) {
				String[] special = new String[] {"id", "name"};
				boolean a = Arrays.asList(special).contains(o1.getName());
				boolean b = Arrays.asList(special).contains(o2.getName());
				if(a == b){
					return o1.getName().compareTo(o2.getName());
				}else if(a){
					return -1;
				}else{
					return 1;
				}
			}
        	
        }
	}
	
	public static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
	
//	//TODO remove this
//	public static void main(String args[]){
//		Network network = new Network();
//		Place place1 = new Place("ARLI", "Arlington");
//		Station station1 = new Station("TFM District Arlington", "TFM-1", new LinkedList<Connection>());
//		Place place2 = new Place("RVTH", "Redstone Valley TransHUB");
//		Station station2 = new Station("TFM District Redstone Valley", "TFM-2", new LinkedList<Connection>());
//		Connection connection1 = new Connection(S.type_RAIL, 100, "TFM Northbound", station2);
//		Connection connection2 = new Connection(S.type_RAIL, 100, "TFM Southbound", station1);
//		
//		station1.addConnection(connection1);
//		station2.addConnection(connection2);
//		place1.addStation(station1);
//		place2.addStation(station2);
//		network.addPlace(place1);
//		network.addPlace(place2);
//		String yaml = new YamlDataStore().encodeNetwork(network);
//		Network net2 = new YamlDataStore().decodeNetwork(yaml);
//		String yaml2 = new YamlDataStore().encodeNetwork(net2);
//		System.out.println(yaml2);
//		
//	}
}
