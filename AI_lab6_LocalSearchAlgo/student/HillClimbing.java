package student;

public class HillClimbing {

	public Node execute(Node start) throws CloneNotSupportedException {
		Node cr = start, neighbor = null;
		while (cr.getH() > 0) {
			neighbor = cr.getBestCandidate();
			if (cr.getH() > neighbor.getH())
				cr = neighbor;
			else {
				System.out.println("H: " + cr.getH());
				return cr;
			}
		}
		System.out.println("H: " + cr.getH());
		return cr;
	}

	public Node executeUntilOK(Node start) throws CloneNotSupportedException {
		Node cr = start, neighbor = null;
		while (cr.getH() != 0) {
			while (cr.getH() > 0) {
				neighbor = cr.getBestCandidate();
				if (cr.getH() > neighbor.getH())
					cr = neighbor;
				else {
					break;
				}
			}
			cr = new Node();
		}
		System.out.println("H: "+cr.getH());
		return cr;
	}

}
