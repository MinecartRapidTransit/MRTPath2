package net.minecartrapidtransit.path.core;

import java.util.LinkedList;
import java.util.List;

public class Route {
	private List<Step> steps;
	public Route(){
		steps = new LinkedList<Step>();
	}
	
	public void addStep(Step step){
		steps.add(0, step);
	}
	public void addStepToBack(Step step){
		steps.add(step);
	}
	
	public List<Step> getSteps(){
		return steps;
	}
	
	public String[] getDirections(DirectionGenerator dg){
		LinkedList<String> strings = new LinkedList<String>();
		for(int i = 0; i < steps.size(); i++){
			String string;
			if(i >= steps.size() - 1){
				string = dg.generateDirection(steps.get(i), null);
			}else{
				string = dg.generateDirection(steps.get(i), steps.get(i+1));
			}
			if(string != null) strings.add(string);
		}
		return strings.toArray(new String[strings.size()]);
	}
}
