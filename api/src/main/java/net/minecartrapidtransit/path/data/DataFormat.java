package net.minecartrapidtransit.path.data;

import net.minecartrapidtransit.path.core.Network;

public interface DataFormat {
	public String encodeNetwork(Network network);
	public Network decodeNetwork(String string);
}
