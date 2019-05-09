package property;

import exception.AlreadyFilledExeption;
import exception.NotFullExeption;
import exception.NotThisEdge_exeption;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Objects;

public class Node {
	
		private int nodeidx;
		private ArrayList<Edge> edges;
		private boolean busy;
	/** Constructor for node that already references edges
	@param nodeidx node ID
	@param edges edges
    * */
	public Node(int nodeidx, ArrayList<Edge> edges) {
		this.nodeidx = nodeidx;
		this.edges = edges;
		busy = false;
	}
	/** Default constructor for Node
	@param nodeidx node ID
    * */
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
		return edges.listIterator();
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
	/** checks if this node contains an edge to the node with number @param other_nodeNmbr
	@returns boolean true if the edge exits, false otherwise
* */
	public boolean hasEdge(int other_nodeNmbr) throws NotThisEdge_exeption {
		for(int i =0;i<edges.size();i++){
			if(this.edges.get(i).otherNode(this).getID()==other_nodeNmbr)
				return true;//this.edges.get(i).getWeight();
		}
		return false;
	}
	/** returns the weight of the edge to node @param other
	@returns float weight
    * */
	public float getEdgeToWeight(int other) throws NotThisEdge_exeption {
		for(int i =0;i<edges.size();i++){
			if(this.edges.get(i).otherNode(this).getID()==other)
				return this.edges.get(i).getWeight();
		}
		return -1;
	}
	/** Returns the edge to node @param node
	*  @returns edge said edge
    * */
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
