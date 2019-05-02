import java.util.Objects;

public class Edge {

	private float weight;
	private float pheromoneLevel;
	private float evaporation;
	private int node1,node2;

	public Edge(Edge e) {
		this.weight=e.weight;
		this.evaporation=e.evaporation;
		this.node1=e.node1;
		this.node2=e.node2;
	}

	public Edge(float weight, float evaporation, int node1, int node2) {
		this.weight = weight;
		this.evaporation = evaporation;
		this.node1 = node1;
		this.node2 = node2;
	}
	public int otherNode(Node N)throws NotThisEdge_exeption{
		if(N.getID()==node1){
			return node2;
		}
		else if(N.getID()==node2){
			return node1;
		}
		throw new NotThisEdge_exeption("This edge does not connect to that node",this,N.getID());
	}

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
