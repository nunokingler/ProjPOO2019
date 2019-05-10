package ant;

import exception.NotThisEdge_exeption;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Objects;
/**{@link Node} class, has a ID and a list containing all the {@link Edge} that this node connects to
 * */
public class Node {
	
		private int nodeidx;
		private ArrayList<Edge> edges;
	/** Constructor for node that already references edges
	@param nodeidx node ID
	@param edges edges
    * */
	public Node(int nodeidx, ArrayList<Edge> edges) {
		this.nodeidx = nodeidx;
		this.edges = edges;
	}
	/** Default constructor for Node
	@param nodeidx node ID
    * */
    public Node(int nodeidx) {
        this.nodeidx = nodeidx;
        this.edges = new ArrayList<>();
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
	/** checks if this node contains an edge to the node with number
	 * @param other_nodeNmbr ID of the node to check connection
	   @return boolean true if the edge exits, false otherwise
* */
	public boolean hasEdge(int other_nodeNmbr){
		for(int i =0;i<edges.size();i++){
			try {
				if(this.edges.get(i).otherNode(this).getID()==other_nodeNmbr)
					return true;//this.edges.get(i).getWeight();
			} catch (NotThisEdge_exeption notThisEdge_exeption) {
				edges.remove(edges.get(i));
				System.out.println("Removed Edge on node "+this.getID()+" because it wasnt mine");
				notThisEdge_exeption.printStackTrace();
			}
		}
		return false;
	}
	/** returns the weight of the edge to node @param other
	 * @param other ID of the other node to check
	@return float weight
    * */
	public float getEdgeToWeight(int other) {
		for(int i =0;i<edges.size();i++){
			try {
				if(this.edges.get(i).otherNode(this).getID()==other)
					return this.edges.get(i).getWeight();
			} catch (NotThisEdge_exeption notThisEdge_exeption) {
				edges.remove(edges.get(i));
				System.out.println("Removed Edge on node "+this.getID()+" because it wasnt mine");
				notThisEdge_exeption.printStackTrace();
			}
		}
		return -1;
	}
	/** Returns the edge to node @param node
	 * @param node ID of the other node to check
	*  @return edge said edge
    * */
	public Edge getEdgeTo(Node node)  {
		for(int i =0;i<edges.size();i++){
			try {
				if(this.edges.get(i).otherNode(this).getID()==node.getID())
					return this.edges.get(i);
			} catch (NotThisEdge_exeption notThisEdge_exeption) {
				edges.remove(edges.get(i));
				System.out.println("Removed Edge on node "+this.getID()+" because it wasnt mine");
				notThisEdge_exeption.printStackTrace();
			}
		}
		return null;
	}

}
