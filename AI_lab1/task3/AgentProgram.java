package AI_THB1_agentAB.task3;

import java.util.*;

public class AgentProgram {
	public static int errorCode = -1;
	public static int point = 0;
	public static final int UP_ACTION = 0;
	public static final int DOWN_ACTION = 1;
	public static final int LEFT_ACTION = 2;
	public static final int RIGHT_ACTION = 3;
	final int SUCK_DIRT_POINT = 500;
	final int CANT_MOVE_POINT = -100;
	final int OTHER_ACTION_POINT = -10;

	private Environment environment;

	public AgentProgram(Environment environment){
		this.environment = environment;
	}

	// random hướng.
	private static int random(){
		Random random = new Random();
		int action = random.nextInt(4);
		return action;
	}

	public static String printAction(int action){
		switch (action){
			case UP_ACTION:
				return "UP";
			case DOWN_ACTION:
				return "DOWN";
			case RIGHT_ACTION:
				return "RIGHT";
			case LEFT_ACTION:
				return "LEFT";
		}
		return null;
	}

	public Index<Integer, Integer> move(int action, Percept p){
		Integer row = p.getAgentLocation().getRow();
		Integer col = p.getAgentLocation().getColumn();
		switch (action){
			case UP_ACTION: //0
				return new Index<>(row-1, col);
			case DOWN_ACTION: //1
				return new Index<>(row+1, col);
			case LEFT_ACTION: //2
				return new Index<>(row, col-1);
			case RIGHT_ACTION: //3
				return new Index<>(row, col+1);
		}
		return null;
	}

	private void cantMove(Index<Integer, Integer> agentLoc) {

	}

	private Action doWhenLocationStateClean(int rand, Percept p){
		Index<Integer, Integer> newIndex = move(rand, p);
		if(newIndex.getRow()<0 || newIndex.getColumn()<0 || newIndex.getRow()>= environment.getEnvState().getState().length /*m*/
				|| newIndex.getColumn()>=environment.getEnvState().getState()[0].length /*n*/){
			point += CANT_MOVE_POINT;
			errorCode = rand;
//			doWhenLocationStateClean(random(), p);
		} else {
			Index<Integer, Integer> currentIndex = p.getAgentLocation();
			if(currentIndex.getRow()==newIndex.getRow()){
				if(currentIndex.getColumn()<newIndex.getColumn()) { // khả năng là RIGHT_ACTION
					for (Index<Integer, Integer> pair: environment.getEnvState().getStatePair().keySet()) {
						if(environment.getEnvState().getStatePair().get(pair).equals(Environment.LocationState.WALL)){
							if(pair.getRow()==newIndex.getRow() && pair.getColumn()==newIndex.getColumn()){
								point += CANT_MOVE_POINT;
								errorCode = rand;
								return NoOpAction.NO_OP;
							}
						}
					}
					point += OTHER_ACTION_POINT;
					currentIndex.setColumn(newIndex.getColumn());
					return Environment.MOVE_RIGHT;
				} else if(currentIndex.getColumn()>newIndex.getColumn()) {
					for (Index<Integer, Integer> pair: environment.getEnvState().getStatePair().keySet()) {
						if(environment.getEnvState().getStatePair().get(pair).equals(Environment.LocationState.WALL)){
							if(pair.getRow()==newIndex.getRow() && pair.getColumn()==newIndex.getColumn()){
								point += CANT_MOVE_POINT;
								errorCode = rand;
								return NoOpAction.NO_OP;
							}
						}
					}
					point += OTHER_ACTION_POINT;
					currentIndex.setColumn(newIndex.getColumn());
					return Environment.MOVE_LEFT;
				}
			}
			if(currentIndex.getColumn()==newIndex.getColumn()){
				if(currentIndex.getRow()<newIndex.getRow()) {
					for (Index<Integer, Integer> pair: environment.getEnvState().getStatePair().keySet()) {
						if(environment.getEnvState().getStatePair().get(pair).equals(Environment.LocationState.WALL)){
							if(pair.getRow()==newIndex.getRow() && pair.getColumn()==newIndex.getColumn()){
								point += CANT_MOVE_POINT;
								errorCode = rand;
								return NoOpAction.NO_OP;
							}
						}
					}
					point += OTHER_ACTION_POINT;
					currentIndex.setRow(newIndex.getRow());
					return Environment.MOVE_DOWN;
				} else if(currentIndex.getRow()>newIndex.getRow()) {
					for (Index<Integer, Integer> pair: environment.getEnvState().getStatePair().keySet()) {
						if(environment.getEnvState().getStatePair().get(pair).equals(Environment.LocationState.WALL)){
							if(pair.getRow()==newIndex.getRow() && pair.getColumn()==newIndex.getColumn()){
								point += CANT_MOVE_POINT;
								errorCode = rand;
								return NoOpAction.NO_OP;
							}
						}
					}
					point += OTHER_ACTION_POINT;
					currentIndex.setRow(newIndex.getRow());
					return Environment.MOVE_UP;
				}
			}
		}
		return NoOpAction.NO_OP;
	}

	/**
	 * Execute của agent thực hiện, executeAction của Environment cập nhật
	 * Nếu Execute của agent thực hiện SUCK_DIRT, executeAction của Environment set vị trí đó là sạch
	 * Nếu Execute của agent thực hiện MOVE_UP, executeAction của Environment cập nhật lại location mới so với location cũ.
	 * Nếu Execute của agent thực hiện MOVE_DOWN, executeAction của Environment cập nhật lại location mới so với location cũ.
	 * Nếu Execute của agent thực hiện MOVE_RIGHT, executeAction của Environment cập nhật lại location mới so với location cũ.
	 * Nếu Execute của agent thực hiện MOVE_LEFT, executeAction của Environment cập nhật lại location mới so với location cũ.
	 */
	public Action execute(Percept p) {// location, status
		if(p.getLocationState()==Environment.LocationState.DIRTY) {
			point += SUCK_DIRT_POINT;
			return Environment.SUCK_DIRT;
		} else {
			int rand = random();
			return doWhenLocationStateClean(rand, p);
		}
	}
}