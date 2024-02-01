package task1234;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {
    private IInformedSearchAlgo isa;

    public GreedyBestFirstSearchAlgo(IInformedSearchAlgo isa) {
        this.isa = isa;
    }

    public GreedyBestFirstSearchAlgo() {}

    @Override
    public Node execute(Node root, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>((o1, o2) -> {
            if(o1.getH()==o2.getH()) return o1.getLabel().compareTo(o2.getLabel());
            return Double.compare(o1.getH(), o2.getH());
        });
        List<Node> exploredSet = new ArrayList<>();
        frontier.add(root);
        while(!frontier.isEmpty()){
            Node current = frontier.poll();
            if(current.getLabel().equals(goal)) return current;
            exploredSet.add(current);
            for (Edge child : current.getChildren()) {
                Node n = child.getEnd();
                if (!exploredSet.contains(n) && !frontier.contains(n)) {
                    frontier.add(n);
                    n.setParent(current);
                } else if(frontier.contains(n)) {
                    n.setParent(current);
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>((o1, o2) -> {
            if(o1.getH()==o2.getH()) return o1.getLabel().compareTo(o2.getLabel());
            return Double.compare(o1.getH(), o2.getH());
        });
        List<Node> exploredSet = new ArrayList<>();
        frontier.add(root);
        boolean isStarted = false;
        while(!frontier.isEmpty()) {
            Node current = frontier.poll();
            if(current.getLabel().equals(goal) && isStarted) return current;
            if(current.getLabel().equals(start)) {
                current.setParent(null);
                frontier.clear();
                exploredSet.clear();
                isStarted = true;
            }
            exploredSet.add(current);
            for (Edge child : current.getChildren()) {
                Node n = child.getEnd();
                if (!exploredSet.contains(n) && !frontier.contains(n)) {
                    n.setParent(current);
                    frontier.add(n);
                } else if(frontier.contains(n)) {
                    n.setParent(current);
                }
            }
        }
        return null;
    }

    @Override
    public boolean isAdmissibleH(Node root, String goal) {
        return isa.isAdmissibleH(root, goal);
    }
}