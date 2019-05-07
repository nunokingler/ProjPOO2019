import java.util.ArrayList;
import java.util.ListIterator;
public class AntColony {

	private int antsnumber,maxAnts;
	private ArrayList<Ant> ants;
	private ArrayList<ArrayList<Node>> hamiltonians;
	private Node starting_node;
	private Graph graph;
	private float alpha;
	private float beta;
	private float sigma;


	AntColony(int _Antsnumber, Graph graph, int Starting_node){
		this.maxAnts = _Antsnumber;
		//antsnumber=1;
		ants= new ArrayList<>();
		for(int i=0;i<_Antsnumber;i++){
			ants.add(new Ant(this,starting_node,sigma));
		}
		antsnumber=_Antsnumber;
		this.graph=graph;
		this.starting_node=graph.getNode(Starting_node);
		hamiltonians =new ArrayList<>();
		alpha=0;
		beta=0;
		sigma=0;
	}
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public void setBeta(float beta) {
		this.beta = beta;
	}

	public void setSigma(float sigma) {
		this.sigma = sigma;
	}

	public void antFirstMove(){
   		if(starting_node.isEmpty()&&antsnumber<maxAnts)
   			ants.add(new Ant(this,starting_node,Constants.sigma));
	}
	public boolean isHamiltonian(ListIterator<Node> path){
   		int nodes[]= new int[graph.getNodeNumber()];
		Node next=null;

   		for(int i=0;i<nodes.length;i++){
   			nodes[i]=0;
		}

   		while(path.hasNext()){
   			next =path.next();
   			nodes[next.getID()]=1;
		}

   		for(int i=0;i<nodes.length;i++){
			if(nodes[i]==0)
				return false;
		}
   		ArrayList<Node> completeCicle= new ArrayList<>();
   		completeCicle.add(next);
   		while(path.hasPrevious()){
   			next=path.previous();
   			completeCicle.add(0,next);
		}
   		//hamiltonians.add(new ArrayList<>((Collection<Node>) path)); //TODO check if this works
		return true;
	}


	@Override
	public String toString() {
   		if(hamiltonians.size()==0)
   			return "";
   		String to_send="";
		for(int i = 0; i< hamiltonians.size(); i++){
			ArrayList<Node> path= hamiltonians.get(i);
			to_send+='{';
			for(int j=0;j<path.size();j++){
				to_send+=","+path.get(j).getID()+",";
			}
			to_send+='}';
		}
		return to_send;
	}

	public float getAlpha() {
   		return alpha;
	}

	public float getBeta() {
		return beta;
	}

	public float getSigma() {
		return sigma;
	}
}
