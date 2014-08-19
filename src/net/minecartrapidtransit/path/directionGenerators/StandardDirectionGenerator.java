package net.minecartrapidtransit.path.directionGenerators;

import net.minecartrapidtransit.path.constants.C;
import net.minecartrapidtransit.path.core.*;

public class StandardDirectionGenerator implements DirectionGenerator {
		
	@Override
	public String generateDirection(Step current, Step next) {
		// System.out.printf("%s: %s\n", current.getType(), current.getTo().getId()); // Used for debug
		if (current.getType().equals(C.type_FOOT)) {
			if(current.getFrom().getId().equals(C.id_START)){
				return String.format("Walk to the %s.", current.getData());
			}else if(current.getTo().getId().equals(C.id_END)){
				return String.format("You have arrived at %s.", current.getData());
			}else{
				return String.format("Travel by foot to %s.", current.getData());
			}
		}
		else if (current.getType().equals(C.type_RAIL)) {
			
		}
		else if (current.getType().equals(C.type_HSRAIL)) {
			if(next.getType().equals(C.type_TRANSFER) || next.getType().equals(C.type_FOOT)){
				return String.format("Ride the %s until you reach %s.", current.getData(), current.getTo().getPlace().getName());
			}
		}
		else if (current.getType().equals(C.type_BUS)) {
			if(next.getType().equals(C.type_TRANSFER) || next.getType().equals(C.type_FOOT)){
				return String.format("Take the bus, %s, to %s.", current.getData(), current.getTo().getPlace().getName());
			}
		}
		else if (current.getType().equals(C.type_WARP)) {
			if(next.getType().equals(C.type_TRANSFER) || next.getType().equals(C.type_FOOT)){
				return String.format("Take the warp, %s, to %s.", current.getData(), current.getTo().getPlace().getName());
			}
		}
		else if (current.getType().equals(C.type_TRANSFER)) {
			return String.format("Transfer to the %s.", current.getTo().getName());
		}
		return null;
	}

}
