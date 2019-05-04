import java.util.ArrayList;
import java.util.Collection;
import java.util.ListIterator;
public class AntColony {

	private int antsnumber,maxAnts;
	private ArrayList<Ant> ants;
	private ArrayList<ArrayList<Node>> hamiltonians;
	private Node starting_node;
	private Graph graph;


   	AntColony(int _Antsnumber,Graph graph, int Starting_node){
		this.maxAnts = _Antsnumber;
		antsnumber=1;

		ants= new ArrayList<>();
		this.graph=graph;
		this.starting_node=graph.getNode(Starting_node);
		hamiltonians =new ArrayList<>();
	}
	public void antFirstMove(){
   		if(starting_node.isEmpty()&&antsnumber<maxAnts)
   			ants.add(new Ant(this,starting_node,Constants.sigma));
	}
	public boolean isHamiltonian(ListIterator<Node> path){
   		int nodes[]= new int[graph.getNodeNumber()];

   		for(int i=0;i<nodes.length;i++){
   			nodes[i]=0;
		}

   		while(path.hasNext()){
   			Node next =path.next();
   			nodes[next.getID()]=1;
		}

   		for(int i=0;i<nodes.length;i++){
			if(nodes[i]==0)
				return false;
		}
   		hamiltonians.add(new ArrayList<>((Collection<Node>) path)); //TODO check if this works
		return true;
	}


	@Override
	public String toString() {
   		if(hamiltonians.size()==0)
   			return "";
   		String to_send="";
		for(int i = 0; i< hamiltonians.size(); i++){
			ArrayList<Node> path= hamiltonians.get(i);
			to_send+='[';
			for(int j=0;j<path.size();j++){
				to_send+=","+path.get(j).getID()+",";
			}
			to_send+=']';
		}
		return to_send;
	}
}
