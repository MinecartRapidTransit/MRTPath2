package net.minecartrapidtransit.path.core;

public interface DirectionGenerator {
	public String generateDirection(Step current, Step next);
}
