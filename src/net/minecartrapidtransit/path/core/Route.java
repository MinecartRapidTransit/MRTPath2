package net.minecartrapidtransit.path.core;

import java.util.LinkedList;
import java.util.List;

public class Route {
	private List<Step> steps;
	public Route(){
		steps = new LinkedList<Step>();
	}
	
	public void addStep(Step step){
		steps.add(steps.size(), step);
	}
	public void addStepToBack(Step step){
		steps.add(step);
	}
	
	public List<Step> getSteps(){
		return steps;
	}
	
	public String[] getDirections(DirectionGenerator dg){
		LinkedList<String> strings = new LinkedList<String>();
		for(Step step : steps){
			strings.add(dg.generateDirection(step));
		}
		return (String[]) strings.toArray();
	}
}
