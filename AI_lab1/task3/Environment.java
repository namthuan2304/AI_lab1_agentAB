package AI_THB1_agentAB.task3;

import java.util.Random;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");

	public enum LocationState {
		CLEAN, DIRTY, WALL
	}

	private EnvironmentState envState;
	private boolean isDone = false;
	private Agent agent = null;
	private double  DIRT_RATE;
	private double  WALL_RATE;

//	private LocationState random(int m, int n){
//		Random random = new Random();
//		int rowRandom = random.nextInt(m);
//		int colRandom = random.nextInt(n);
//		return matrixEnvironment[rowRandom][colRandom];
//	}

	public Environment(int m, int n, double DIRT_RATE, double WALL_RATE) {
		this.DIRT_RATE = DIRT_RATE;
		this.WALL_RATE = WALL_RATE;
		// random khởi tạo môi trường với dirt và wall, đồng thời điền vào map statePair bên EnvironmentState
		envState = new EnvironmentState(m, n, DIRT_RATE, WALL_RATE);
	}

	public EnvironmentState getEnvState() {
		return envState;
	}

	// add an agent into the environment
	public void addAgent(Agent agent, int row, int col) {
		this.agent = agent;
		envState.setAgentLocation(new Index<>(row, col));
		// location thực sự lấy từ đây, nên envState.getAgentLocation là gốc.
	}

	// Update environment state when agent do an action
	// Dựa vào success Action để lấy action ra
	public EnvironmentState executeAction(Action action) {
		Index<Integer, Integer> index = envState.getAgentLocation();
		if (action.equals(Environment.SUCK_DIRT)) {
			envState.setLocationState(index, LocationState.CLEAN);  // lỗi
		} else if (action.equals(Environment.MOVE_RIGHT)) {
			envState.setAgentLocation(new Index<>(index.getRow(), index.getColumn()));
		} else if (action.equals(Environment.MOVE_LEFT)) {
			envState.setAgentLocation(new Index<>(index.getRow(), index.getColumn()));
		} else if (action.equals(Environment.MOVE_UP)) {
			envState.setAgentLocation(new Index<>(index.getRow(), index.getColumn()));
		} else if (action.equals(Environment.MOVE_DOWN)) {
			envState.setAgentLocation(new Index<>(index.getRow(), index.getColumn()));
		}
		envState.setAgentLocation(index);
		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.

	// percept dc ăn ở đây
	public Percept getPerceptSeenBy() {
		Index<Integer, Integer> location = envState.getAgentLocation(); //A
//		System.out.println(envState.getLocationState(location) + ": zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		return new Percept(location, envState.getLocationState(location));
		//vậy envState truyền cho percept(chắc chắn)
		// envState.getLocationState(location): lấy từ constructor của envState trong map put số 1
	}

	public void step() {
//		System.out.println("agentLoc 0" + this.envState.getAgentLocation());
		envState.display(); // chỉ in mỗi môi trường với các state.
		System.out.println("agentLoc.: " + this.envState.getAgentLocation()); // 0.0
		Index<Integer, Integer> agentLocation = this.envState.getAgentLocation(); // 0.0
		Action anAction = agent.execute(getPerceptSeenBy()); // DOWN
//		System.out.println("agentLoc.check: " + this.envState.getAgentLocation());
		EnvironmentState es = executeAction(anAction); // 2.0
//		envState.s
		/**
		 * anAction và es lỗi !!!!!!
		 */
//		System.out.println("Agent Loc. 2: " + agentLocation); // 1.0
		if(AgentProgram.errorCode!=-1) {
			System.out.print("\tAction: " + AgentProgram.printAction(AgentProgram.errorCode) + anAction + " "+ AgentProgram.printAction(AgentProgram.errorCode) + ")");
			AgentProgram.errorCode = -1;
			System.out.println("\tPoint: " + AgentProgram.point);
		} else {
			System.out.print("\tAction: " + anAction);
			System.out.println("\t\t\t\t\t\tPoint: " + AgentProgram.point);
		}
		int count = 0;
		int wall = 0;
		for (Index<Integer, Integer> pair : envState.getStatePair().keySet()) {
			if(envState.getStatePair().get(pair)==LocationState.DIRTY) {
				isDone = false;
				break;
			}
			if(envState.getStatePair().get(pair)==LocationState.CLEAN) {
				count++;
			}
			if(envState.getStatePair().get(pair)==LocationState.WALL) {
				wall++;
			}
		}
		if(count==envState.getStatePair().size()-wall) {
			isDone = true;
		}
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			if(isDone==false) {
				step();
				System.out.println("-------------------------");
			} else {
				System.out.println("Every dirty cell has already been cleaned. The agent is going to turn off automatically in a few seconds!");
				return;
			}
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
