package net.minecartrapidtransit.path.directionGenerators;

import net.minecartrapidtransit.path.constants.S;
import net.minecartrapidtransit.path.core.*;

public class StandardDirectionGenerator implements DirectionGenerator {
	

	@Override
	public String generateDirection(Step current, Step next) {
		System.out.printf("%s: %s\n", current.getType(), current.getTo().getId());
		switch(current.getType()){
		case S.type_FOOT:
			if(current.getFrom().getId().equals(S.id_START)){
				return String.format("Walk to %s.", current.getData());
			}else if(current.getFrom().getId().equals(S.id_END)){
				return String.format("You have arrived at %s", current.getData());
			}else{
				return String.format("Travel by foot to %s", current.getData());
			}
		case S.type_RAIL:
		case S.type_HSRAIL:
			if(next.getType().equals(S.type_TRANSFER) || next.getType().equals(S.type_FOOT)){
				return String.format("Ride the %s until you reach %s", current.getData(), current.getTo().getPlace().getName());
			}
			break;
		case S.type_BUS:
			if(next.getType().equals(S.type_TRANSFER) || next.getType().equals(S.type_FOOT)){
				return String.format("Take the bus, %s, to %s", current.getData(), current.getTo().getPlace().getName());
			}
		case S.type_WARP:
			if(next.getType().equals(S.type_TRANSFER) || next.getType().equals(S.type_FOOT)){
				return String.format("Take the warp, %s, to %s", current.getData(), current.getTo().getPlace().getName());
			}
		}
		return null;
	}

}
