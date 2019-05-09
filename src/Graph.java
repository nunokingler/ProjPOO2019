//contains nodes



import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

public class Graph {

	private HashMap<Integer,Node> nodes;
	private HashMap<String,Edge> edges;
	private float n,p,allEdgesWeights;

	Graph(float n, float p){
		this.nodes= new HashMap<>();
		this.edges= new HashMap<>();
		this.n=n;
		this.p=p;
		allEdgesWeights=0;
	}

	public void addNode(int nodeNmbr, ArrayList<Float> connections_weight,ArrayList<Integer> connections_other__node ) throws DuplicatesExeption, SizeMismatchException, DiferentWeightExeption {

		if(nodes.containsKey(nodeNmbr))
			throw new DuplicatesExeption("This node was already created!");
		if(connections_other__node.size()!= connections_weight.size())
			throw new SizeMismatchException("Connections weight and other node are diferent sizes");

		String sum;
		Node to_add= new Node(nodeNmbr),other_node;
		for(int i=0;i<connections_weight.size();i++){
			int other_node_nmbr=connections_other__node.get(i);
			float weight = connections_weight.get(i);

			if(!nodes.containsKey(other_node_nmbr)){//if the other node is not on the data-base it is added
				other_node= new Node(other_node_nmbr);
				nodes.put(other_node_nmbr,other_node);
			}
			else {                                    //if the other node is on the data base it does not need to be added, simply fetched. the weight of the connection is checked again
				other_node = nodes.get(other_node_nmbr);
			}
			sum= other_node_nmbr>nodeNmbr? Integer.toString(nodeNmbr )+'|'+Integer.toString(other_node_nmbr): Integer.toString(other_node_nmbr)+'|'+Integer.toString(nodeNmbr);
			Node node1 = other_node.getID()>to_add.getID()?to_add:other_node,node2 = node1==to_add?other_node:to_add;
			Edge edge_to_add = new Edge(weight,n,p,node1,node2);

			if(!this.edges.containsKey(sum)){
				//nodes.get(nodeNmbr).addEdge(edge_to_add);
				try {
					if(other_node.getEdgeToWeight(nodeNmbr)!=weight){
						throw new DiferentWeightExeption("This node already has 1 edge with diferent weight");
					}
				}
				catch (NotThisEdge_exeption notThisEdge_exeption) {
					notThisEdge_exeption.printStackTrace();
				}
			}
			else{
				this.edges.put(sum,edge_to_add);
			}
			allEdgesWeights+=edge_to_add.getWeight();
			to_add.addEdge(edge_to_add);
		}
		nodes.put(nodeNmbr,to_add);
	}
	public void addEdge(int node1,int node2, float weight) throws NotThisEdge_exeption, ThisEdgeAlreadyExists {
		if(!nodes.containsKey(node1))
			nodes.put(node1, new Node(node1));
		if(!nodes.containsKey(node2))
			nodes.put(node2, new Node(node2));

		if(nodes.get(node1).hasEdge(node2)){
			throw new ThisEdgeAlreadyExists();
		}

		Edge to_add = new Edge(weight,n,p,nodes.get(node1),nodes.get(node2));
		nodes.get(node1).addEdge(to_add);
		nodes.get(node2).addEdge(to_add);
		String sum= node2>node1? Integer.toString(node1 )+'|'+Integer.toString(node2): Integer.toString(node2)+'|'+Integer.toString(node1);
		allEdgesWeights+=to_add.getWeight();
		edges.put(sum,to_add);
	}

	public Node getNode(int starting_node) {
		return nodes.get(starting_node);
	}

	public int getNodeNumber(){
		return nodes.size();
	}

	public void addPheromones(ListIterator<Node> listIterator,float valueToAdd) {
		Node node = null,previNode;
		Edge edge = null;
		if(listIterator.hasNext())
			node=listIterator.next();
		while(listIterator.hasNext()){
			previNode=node;
			node=listIterator.next();
			try {
				edge=previNode.getEdgeTo(node);
				edge=previNode.getEdgeTo(node);
				edge.addPheromones(allEdgesWeights*valueToAdd);
			} catch (NotThisEdge_exeption notThisEdge_exeption) {
				notThisEdge_exeption.printStackTrace();
			}
		}
	}
}
