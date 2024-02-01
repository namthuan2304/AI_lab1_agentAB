package Lab9.game_nim_student;

import java.util.Arrays;
import java.util.List;

public class TestNode {
	public static void main(String[] args) {
//		Node node = new Node();
//		Integer[] data = { 7 };
//		node.addAll(Arrays.asList(data));
//
//		MinimaxAlgo algo = new MinimaxAlgo();
//		algo.execute(node);
		Node node  = new Node();
		Integer[] data = {3, 3, 1};
		node.addAll(Arrays.asList(data));
		System.out.println(node.getSuccessors());
	}
}