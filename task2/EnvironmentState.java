package AI_THB1_agentAB.task2;

import java.util.HashMap;
import java.util.Map;

import AI_THB1_agentAB.task2.Environment.LocationState;

public class EnvironmentState {
	private Map<String, Environment.LocationState> state = new HashMap<String, Environment.LocationState>();
	private String agentLocation = null;//

	public EnvironmentState(Environment.LocationState locAState, Environment.LocationState locBState,
							Environment.LocationState locDState, Environment.LocationState locCState) {
		this.state.put(Environment.LOCATION_A, locAState); //vị trí A, trạng thái MT của A
		this.state.put(Environment.LOCATION_B, locBState);
		this.state.put(Environment.LOCATION_D, locDState);
		this.state.put(Environment.LOCATION_C, locCState);
	}

	public void setAgentLocation(String location) {
		this.agentLocation = location;
	}

	public String getAgentLocation() {
		return this.agentLocation;
	}

	public LocationState getLocationState(String location) {
		return this.state.get(location);
	}

	public void setLocationState(String location, LocationState locationState) {
		this.state.put(location, locationState);
	}

	public void display() {
		System.out.println("Environment state: \n\t" + this.state);
	}
}