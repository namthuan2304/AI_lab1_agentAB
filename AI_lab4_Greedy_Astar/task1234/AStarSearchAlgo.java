package task1234;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStarSearchAlgo implements IInformedSearchAlgo {
    @Override
    public Node execute(Node root, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>((o1, o2) -> {
            if(o1.getFnOfAstar()==o2.getFnOfAstar()) return o1.getLabel().compareTo(o2.getLabel());
            return Double.compare(o1.getFnOfAstar(), o2.getFnOfAstar());
        });
        List<Node> exploredSet = new ArrayList<>();
        root.setG(0);
        frontier.add(root);
        while(!frontier.isEmpty()) {
            Node current = frontier.poll();
            if(current.getLabel().equals(goal)) return current;
            exploredSet.add(current);
            for (Edge child : current.getChildren()) {
                Node n = child.getEnd();
                if(!frontier.contains(n) && !exploredSet.contains(n)){
                    n.setG(current.getG() + child.getWeight());
                    n.setParent(current);
                    frontier.add(n);
                } else if(frontier.contains(n) && n.getG()>(current.getG() + child.getWeight())) {
                    frontier.remove(n);
                    n.setG(current.getG() + child.getWeight());
                    n.setParent(current);
                    frontier.add(n);
                }
            }
        }
        return null;
    }

    @Override
    public boolean isAdmissibleH(Node root, String goal) {
        return isAdmissibleH(root, root, goal);
    }

    public boolean isAdmissibleH(Node root, Node start, String goal) {
        if(execute(root, start.getLabel(), goal)!=null) {
            if(execute(root, start.getLabel(), goal).getG()<start.getH()) return true;
            for (Node child : start.getChildrenNodes()) {
                if(isAdmissibleH(root, child, goal)) return true;
            }
        }
        return false;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>((o1, o2) -> {
            if(o1.getFnOfAstar()==o2.getFnOfAstar()) return o1.getLabel().compareTo(o2.getLabel());
            return Double.compare(o1.getFnOfAstar(), o2.getFnOfAstar());
        });
        List<Node> exploredSet = new ArrayList<>();
        root.setG(0);
        frontier.add(root);
        boolean isStarted = false;
        while(!frontier.isEmpty()) {
            Node current = frontier.poll();
            if(current.getLabel().equals(goal) && isStarted) return current;
            if(current.getLabel().equals(start)) {
                current.setG(0);
                current.setParent(null);
                frontier.clear();
                exploredSet.clear();
                isStarted = true;
            }
            exploredSet.add(current);
            for (Edge child : current.getChildren()) {
                Node n = child.getEnd();
                if(!frontier.contains(n) && !exploredSet.contains(n)){
                    n.setG(current.getG() + child.getWeight());
                    n.setParent(current);
                    frontier.add(n);
                } else if(frontier.contains(n) && n.getG()>(current.getG() + child.getWeight())) {
                    frontier.remove(n);
                    n.setG(current.getG() + child.getWeight());
                    n.setParent(current);
                    frontier.add(n);
                }
            }
        }
        return null;
    }
}