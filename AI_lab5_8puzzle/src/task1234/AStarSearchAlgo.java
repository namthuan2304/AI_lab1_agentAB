package task1234;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSearchAlgo implements IPuzzleAlgo {
    @Override
    public Node execute(Puzzle model) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(PuzzleUtils.HeuristicComparatorByF);
        Set<Node> explored = new HashSet<>();
        if (model.getInitialState().equals(model.getGoalState())) return model.getInitialState();
        model.getInitialState().setH(model.computeH2(model.getInitialState()));
        frontier.add(model.getInitialState());
        int step = 0;
        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();
            System.out.println("G: " + currentNode.getG() + ", H: " + currentNode.getH() + ", F: " + currentNode.getF());
            explored.add(currentNode);
            System.out.println("Step "+ step++ +": \n" + currentNode);
            if (currentNode.equals(model.getGoalState())) return currentNode;
            for (Node e : model.getSuccessors(currentNode)) {
                if (!frontier.contains(e) && !explored.contains(e)) {
                    e.setG(currentNode.getG()+1);
                    frontier.add(e);
                } else if(frontier.contains(e) && e.getG()>(currentNode.getG()+1)) {
                    frontier.remove(e);
                    e.setG(currentNode.getG()+1);
                    frontier.add(e);
                }
            }
        }
        return null;
    }
}