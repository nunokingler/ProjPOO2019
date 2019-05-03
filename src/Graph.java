//contains nodes


import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

	private int nbnodes;
	private int nestnode;
	private HashMap<Integer,Node> nodes;
	private HashMap<String,Edge> edges;
	
	Graph(int nodes, int nest){
		this.nbnodes=nodes;
		this.nestnode=nest;
		this.nodes= new HashMap<>();
		this.edges= new HashMap<>();
	}
	public void addNode(int nodeNmbr, ArrayList<Float> connections_weight,ArrayList<Integer> connections_other__node ) throws DuplicatesExeption, SizeMissmatchExeption, DiferentWeightExeption {
		if(nodes.containsKey(nodeNmbr))
			throw new DuplicatesExeption("This node was already created!");
		if(connections_other__node.size()!= connections_weight.size())
			throw new SizeMissmatchExeption("Connections weight and other node are diferent sizes");

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
			int node1 = other_node_nmbr>nodeNmbr?nodeNmbr:other_node_nmbr,node2 = node1==nodeNmbr?other_node_nmbr:nodeNmbr;
			Edge edge_to_add = new Edge(weight,0,node1,node2);

			if(!this.edges.containsKey(sum)){
				//nodes.get(nodeNmbr).addEdge(edge_to_add);
				try {
					if(other_node.hasEdge(nodeNmbr)!=weight){
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
			to_add.addEdge(edge_to_add);
		}
		nodes.put(nodeNmbr,to_add);
	}
}
