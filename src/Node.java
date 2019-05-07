
import java.util.*;

public class Node {
	
		private int nodeidx;
		private ArrayList<Edge> edges;
		private boolean busy;

	public Node(int nodeidx, ArrayList<Edge> edges) {
		this.nodeidx = nodeidx;
		this.edges = edges;
		busy = false;
	}

    public Node(int nodeidx) {
        this.nodeidx = nodeidx;
        this.edges = new ArrayList<>();
    }

    public boolean getBusy(){
	    return busy;
	}

	public int getEdgeNmbr(){
		return edges.size();
	}

	public ListIterator<Edge> getEdges(){
		ArrayList<Edge> edg = new ArrayList<Edge>();
		for(int i= 0;i<edges.size();i++){
			edg.add( new Edge(edges.get(i)));
		}
		return edg.listIterator(edg.size());
	}

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

	public int getID() {
		return nodeidx;
	}

	public void addEdge(Edge edge_to_add) {
		edges.add(edge_to_add);
	}

	@Override
	public String toString() {
		return "Node{" +
				"nodeidx=" + nodeidx +
				'}';
	}
	public boolean hasEdge(int other_nodeNmbr) throws NotThisEdge_exeption {
		for(int i =0;i<edges.size();i++){
			if(this.edges.get(i).otherNode(this).getID()==other_nodeNmbr)
				return true;//this.edges.get(i).getWeight();
		}
		return false;
	}

	public float getEdgeToWeight(int other) throws NotThisEdge_exeption {
		for(int i =0;i<edges.size();i++){
			if(this.edges.get(i).otherNode(this).getID()==other)
				return this.edges.get(i).getWeight();
		}
		return -1;
	}

	public Edge getEdgeTo(Node node) throws NotThisEdge_exeption {
		for(int i =0;i<edges.size();i++){
			if(this.edges.get(i).otherNode(this).getID()==node.getID())
				return this.edges.get(i);
		}
		return null;
	}

    public boolean isEmpty() {
        return !busy;
	}
	public void fill()throws  AlreadyFilledExeption{
	    if(busy)
	        throw new AlreadyFilledExeption();
	    else
	        busy = true;
    }
    public void leave()throws NotFullExeption{
	    if(!busy)
	        throw new NotFullExeption();
        else
            busy = false;
	}
}
