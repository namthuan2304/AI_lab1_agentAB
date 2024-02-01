package task1234;

public class TestNode {
	public static void main(String[] args) {
		Puzzle p = new Puzzle();
		p.readInput("txt/PuzzleMap.txt", "txt/PuzzleGoalState.txt");

		Node initialState = p.getInitialState();
		Node tmp = new Node(initialState);
//		System.out.println(initialState.equals(tmp)); //true //đã cmd

		Node goalState = p.getGoalState();
//		System.out.println(p.getInitialState()); // "txt/PuzzleMap.txt"
//		System.out.println("H: "+initialState.getH()); // H = 0 //đã cmd
//		System.out.println(Arrays.toString(initialState.getWhiteTilePosition()));
//		System.out.println(p.getGoalState()); // "txt/PuzzleGoalState.txt"
		Node re = p.moveWhiteTile(initialState, 'r');
//
//		System.out.println(re); // ra ma trận init dịch "r" //đã cmd
//		System.out.println("H: " + re.getH()); // H sau khi dịch "r" //đã cmd
//		System.out.println(initialState); // init vẫn k thay đổi //đã cmd
//		System.out.println(Arrays.toString(re.getWhiteTilePosition()));
//		System.out.println(p.computeH(init, goal));

//		System.out.println(p.getInitialState()); // init vẫn k thay đổi
//		System.out.println(p.getGoalState()); // goal vẫn k thay đổi

//		List<Node> children = p.getSuccessors(initialState);
//		System.out.println("Size: "+children.size());
//		for (Node child : children) {
//			System.out.println(child.getH()+" "+p.computeH(child));
//		}

		IPuzzleAlgo greedy = new GreedyBestFirstSearch();
		System.out.println(greedy.execute(p)); // up to 12 million steps until H = 0

		IPuzzleAlgo aStar = new AStarSearchAlgo();
		System.out.println(aStar.execute(p)); // we have 630 steps with G = 16 when H = 0
	}
}