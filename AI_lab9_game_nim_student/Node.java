package Lab9.game_nim_student;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
	private List<Integer> data = new ArrayList<Integer>();

	public void add(Integer val) {
		this.data.add(val);
	}

	public void addAll(List<Integer> data) {
		this.data.addAll(data);
	}

	// Get children of the current nodes
	public List<Node> getSuccessors() {
		if (isTerminal()) return null;
		List<Node> res = new ArrayList<>();
		List<Integer> child_data = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			for (int j = 1; j <= data.get(i) / 2; j++) {
				Node child = new Node();
				if (j == data.get(i) - j) continue;
				child_data.add(j);
				child_data.add(data.get(i) - j);
				for (int k = 0; k < data.size(); k++) {
					if (k == i) continue;
					child_data.add(data.get(k));
				}
				child_data.sort(Node.DESCOMPARATOR);
				child.addAll(child_data);
				res.add(child);
				child_data.clear();
			}
		}
		return res;
	}

	// Check whether a node is terminal or not
	public boolean isTerminal() {
		data.sort(Node.DESCOMPARATOR);
		return data.get(0) <= 2;
	}

	public static final Comparator<Integer> DESCOMPARATOR = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	};

	@Override
	public String toString() {
		Collections.sort(this.data, DESCOMPARATOR);
		return this.data.toString();
	}

}
