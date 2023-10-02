package AI_THB1_agentAB.task3;

public class TestSimpleReflexAgent {

	// check xem liệu ta có bỏ lộn vào ô có wall k
	public static void main(String[] args) {
		Environment env = new Environment(3, 3, 0.5, 0.2);
		Agent agent = new Agent(new AgentProgram(env));
		env.addAgent(agent, 0, 0);

		env.step(25);
	}
}
