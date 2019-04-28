import java.util.Objects;

public class Edge {

	private float weight;
	private float pheromoneLevel;
	private float evaporation;
	private int node1,node2;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Edge edge = (Edge) o;
		return node1 == edge.node1 &&
				node2 == edge.node2;
	}

	@Override
	public int hashCode() {
		return node1 + node2;//Objects.hash(node1, node2);
	}
}
