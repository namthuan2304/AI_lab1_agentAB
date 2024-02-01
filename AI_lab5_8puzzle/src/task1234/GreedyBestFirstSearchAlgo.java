package task1234;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * [Code at lab 03/10/2023]
 */
public class GreedyBestFirstSearchAlgo implements IPuzzleAlgo {
    @Override
    public Node execute(Puzzle model) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(PuzzleUtils.HeuristicComparatorByH);
        List<Node> exploredSet = new ArrayList<>();
        frontier.add(model.getInitialState());
        while(!frontier.isEmpty()){
            Node current = frontier.poll();
            if(current.getH()==0) return current;
            else {
                exploredSet.add(current);
                for (Node child : model.getSuccessors(current)) {
                    if(!frontier.contains(child) && !exploredSet.contains(child)) {
//                      child.setG(current.getG()+1);
                        frontier.add(child);
                    }
//                  else if(frontier.contains(child) && child.getG()>current.getG()+1) {
//                      child.setG(current.getG()+1);
//                  }
                }
            }
        }
        return null;
    }
}