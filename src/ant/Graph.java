package ant;


import exception.ThisEdgeAlreadyExists;

import java.util.HashMap;
import java.util.ListIterator;
/**
 * {@link Graph} class, contains {@link Node}s  and {@link Edge}s in a way that no 2 nodes can have the same ID and
 * no 2 edges can connect to the same nodes*/
public class Graph {

	private HashMap<Integer,Node> nodes;
	private HashMap<String,Edge> edges;
	private float n,p,allEdgesWeights;
	/**
	 Default constructor for {@link Graph}
	 @param n parameter related to evaporation time
	 @param p parameter related to evaporation value
	  * */
	public Graph(float n, float p){
		this.nodes= new HashMap<>();
		this.edges= new HashMap<>();
		this.n=n;
		this.p=p;
		allEdgesWeights=0;
	}
	/**
	 Adds an edge to graph
	 @param node1 of of the nodes that the edge connects
	 @param node2  the other node
	 @param weight weight of the node to be added
	 @throws ThisEdgeAlreadyExists if there is already one edge connecting these two nodes
	  * */
	public void addEdge(int node1,int node2, float weight) throws  ThisEdgeAlreadyExists {
		if(!nodes.containsKey(node1))
			nodes.put(node1, new Node(node1));
		if(!nodes.containsKey(node2))
			nodes.put(node2, new Node(node2));

		if(nodes.get(node1).hasEdge(node2)){
			Edge edg=nodes.get(node1).getEdgeTo(nodes.get(node2));
			if(edg.getWeight()>weight)
				edg.setWeight(weight);
			throw new ThisEdgeAlreadyExists();
		}

		Edge to_add = new Edge(weight,n,p,nodes.get(node1),nodes.get(node2));
		nodes.get(node1).addEdge(to_add);
		nodes.get(node2).addEdge(to_add);
		String sum= node2>node1? Integer.toString(node1 )+'|'+node2: Integer.toString(node2)+'|'+node1;
		allEdgesWeights+=to_add.getWeight();
		edges.put(sum,to_add);
	}
	/**
		Returns the node given by  starting node
	 	@param starting_node  id of the returned node
		@return Node the node with given ID
	  * */
	protected Node getNode(int starting_node) {
		return nodes.get(starting_node);
	}

	/**
	 Returns the ammount of nodes
	 @return the ammount of nodes on this graph
	  * */
	public int getNodeNumber(){
		return nodes.size();
	}
	/**
	 Adds an edge to graph, can only be called before LoadAnts()
	 @param listIterator iterator of nodes to add pheromones between (first and last node should be the same)
	 @param valueToAdd value to add (already divided by the weight of the path)
	  * */
	protected void addPheromones(ListIterator<Node> listIterator,float valueToAdd) {
		Node node = null,previNode;
		Edge edge = null;
		if(listIterator.hasNext())
			node=listIterator.next();
		while(listIterator.hasNext()){
			previNode=node;
			node=listIterator.next();
			edge=previNode.getEdgeTo(node);
			edge.addPheromones(allEdgesWeights*valueToAdd);
		}
	}
}
