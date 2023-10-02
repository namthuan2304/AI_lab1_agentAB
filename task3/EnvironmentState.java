package AI_THB1_agentAB.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import AI_THB1_agentAB.task3.Environment.LocationState;

public class EnvironmentState {
    private Map<Index<Integer, Integer>, Environment.LocationState> statePair = new HashMap<>(); // map lưu dirty and obstacles
    private Map<Index<Integer, Integer>, Environment.LocationState> statePairClone; // map lưu dirty and obstacles
    private Environment.LocationState[][] state;
    private Index<Integer, Integer> agentLocation = null;

    public EnvironmentState(int m, int n, double DIRT_RATE, double WALL_RATE) {
        state = new Environment.LocationState[m][n];
        putStateIntoEnvironment(m, n, DIRT_RATE, LocationState.DIRTY);
        putStateIntoEnvironment(m, n, WALL_RATE, LocationState.WALL);
        // lỗi 1 cặp toạ độ KEY có 2 giá trị DIRTY và WALL
        statePairClone = statePair;
    }

    private boolean checkExist(Index<Integer, Integer> location){
        for (Index<Integer, Integer> pair: this.statePair.keySet()) {
            if (pair.getRow() == location.getRow() && pair.getColumn() == location.getColumn()) {
                return true;
            }
        }
        return false;
    }

    private void putStateIntoEnvironment(int m, int n, double type, LocationState stateType) { // có vấn đề
        for (int i = 0; i < (int) (type * m * n); i++) {
            Random random = new Random();
            int row = random.nextInt(m);
            int col = random.nextInt(n);
            Index<Integer, Integer> location = new Index<>(row, col);
            if (statePair.isEmpty()) {
                statePair.put(new Index<>(row, col), stateType);
            } else {
                if(checkExist(location)) {
                    i -= 1;
                } else {
                    statePair.put(new Index<>(row, col), stateType);
                }
//            for (Index<Integer, Integer> pair: this.statePair.keySet()) {
//                if (pair.getRow() == location.getRow() && pair.getColumn() == location.getColumn()) {
//                    i -= 1;
//                } // kiểm tra cẩn thận có else chỗ này k do vòng lặp foreach mang lại???
            }
        }
    }

    public Index<Integer, Integer> getAgentLocation() {
        return agentLocation;
    }

    public void setAgentLocation(Index<Integer, Integer> location) {
        for (Index<Integer, Integer> pair: this.statePair.keySet()) {
            if (pair.getRow() == location.getRow() && pair.getColumn() == location.getColumn()) {
                if(statePair.get(pair)==LocationState.WALL){
                    throw new RuntimeException("Can't put the agent into the cell that has obstacles(wall)!!!");
                }
            }
        }
        agentLocation = location;
    }

    public LocationState getLocationState(Index<Integer, Integer> location) {
        for (Index<Integer, Integer> pair: this.statePair.keySet()) {
            if (pair.getRow()==location.getRow() && pair.getColumn()==location.getColumn()) {
                return this.statePair.get(pair);
            }
        }
        return LocationState.CLEAN;
    }

    //setLocationState
    public void setLocationState(Index<Integer, Integer> location, LocationState ls) {
        for (Index<Integer, Integer> pair: this.statePair.keySet()) {
            if (pair.getRow()==location.getRow() && pair.getColumn()==location.getColumn()) {
                this.statePair.put(pair, ls);
            }
        }
    }

    public Map<Index<Integer, Integer>, LocationState> getStatePair() {
        return statePair;
    }

    public LocationState[][] getState() {
        return state;
    }

    public void display() {
        int count = 0;
        System.out.println("Environment state:");
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                Index<Integer, Integer> index = new Index<>(i, j);
                for (Index<Integer, Integer> pair : statePairClone.keySet()) {
                    count++;
                    if (pair.getColumn() == index.getColumn() && pair.getRow() == index.getRow()) {
                        System.out.print(statePairClone.get(pair) + "  ");
                        count = 0;
                        break;
                    } else if(count==statePairClone.size()){
                        System.out.print(LocationState.CLEAN + "  ");
                        count = 0;
                    }
                }
            }
            System.out.println();
        }
        System.out.println(statePairClone);
    }
}