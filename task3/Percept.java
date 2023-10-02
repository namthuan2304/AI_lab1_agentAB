package AI_THB1_agentAB.task3;

public class Percept {
	private Index<Integer, Integer> agentLocation;
	private Environment.LocationState state;

	public Percept(Index<Integer, Integer> agentLocation, Environment.LocationState state) {
		this.agentLocation = agentLocation;
		this.state = state;
	}

	public Environment.LocationState getLocationState() {
		return this.state;
	}

	public Index<Integer, Integer> getAgentLocation() {
		return this.agentLocation;
	}
}