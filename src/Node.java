
//contains a list of weights

import java.util.Objects;

public class Node {
	
		private int nodeidx;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node node = (Node) o;
		return nodeidx == node.nodeidx;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nodeidx);
	}
}
