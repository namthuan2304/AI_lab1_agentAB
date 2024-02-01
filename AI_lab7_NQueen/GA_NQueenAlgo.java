package Lab7;
import java.util.*;

public class GA_NQueenAlgo{
    public static final int POP_SIZE = 100;//Population size
    public static final double MUTATION_RATE = 0.03;
    public static final int MAX_ITERATIONS = 1000;
    List<Node> population = new ArrayList<Node>();
    Random rd = new Random();
    
    public Node execute() {
        initPopulation();
        int k = 0;
        while (k ++  < MAX_ITERATIONS) {
            List<Node> newPopulation = new ArrayList<>();
            for (int i = 0; i < POP_SIZE; i++) {
                Node x = getParentByRandomSelection();
                Node y = getParentByRandomSelection();
                Node child =  this.reproduce(x, y);
                if (rd.nextDouble() < MUTATION_RATE) mutate(child);
                if (child.getH() == 0) return child;
                newPopulation.add(child);
            }
            this.population = newPopulation;
        }
        Collections.sort(population);
        return population.get(0);
    }
    // initialize the individuals of the population
    public void initPopulation() {
        for (int i = 0; i < POP_SIZE; i++) {
            Node ni = new Node();
            ni.generateBoard();
            population.add(ni);
        }
    }

    // Mutate an individual by selecting a random Queen and
//move it to a random row.
    public void mutate(Node node) {
        Queen rQueen = node.getState()[rd.nextInt(Node.N)];
        rQueen.setRow(rd.nextInt(Node.N));
    }
    
    //Crossover x and y to reproduce a child
    public Node reproduce(Node x, Node y) {
        Node child = new Node();
        child.generateBoard();
        int c = rd.nextInt();
        for (int i = 0 ; i < c; i++) {
            child.setRow(i, x.getRow(i));
        }
        for (int i = c; i < Node.N; i++) {
            child.setRow(i, y.getRow(i));
        }
        return child;
    }

    //Select a random parent from the population
    public Node getParentByRandomSelection() {
        return population.get(rd.nextInt(Node.N));
    }

    // Select K individuals from the population at random and 
    // select the best out of these to become a parent.
    public Node getParentByTournamentSelection() {
// Enter your code here
        int k = 50;
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < k; i++) list.add(this.population.get(rd.nextInt(Node.N)));
        Collections.sort(list);
        return list.get(0);
    }
}
