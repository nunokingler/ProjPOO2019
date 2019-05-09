package ant;
import exception.NotThisEdge_exeption;
import property.Node;
import simulation.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AntColony {

	private int antsnumber,maxAnts;
	private ArrayList<Ant> ants;
	private ArrayList<Node> best_ham;
	private float best_ham_weight;
	private Node starting_node;
	private Graph graph;
	private float alpha;
	private float beta;
	private float delta;
	private float gamma;

	/* Default constructor for AntColony
        @param _Antsnumber the max number of ants of this colony
        @param graph the graph where this colony is placed
        @return int starting_node the number of node containing this ant colony
    * */
	public AntColony(int _Antsnumber, Graph graph, int Starting_node){
		this.maxAnts = _Antsnumber;

		antsnumber=_Antsnumber;
		this.graph=graph;
		this.starting_node=graph.getNode(Starting_node);
		alpha=0;
		beta=0;
		delta =0;
		best_ham=new ArrayList<>();
		best_ham_weight=Integer.MAX_VALUE;
		ants= new ArrayList<>();
		for(int i=0;i<_Antsnumber;i++){
			ants.add(new Ant(this,starting_node, delta));
		}
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public void setBeta(float beta) {
		this.beta = beta;
	}

	public void setDelta(float delta) {
		this.delta = delta;
	}

	public void setGamma(float gamma) {
		this.gamma = gamma;
	}
	/* Evaluates if a certain cicle is hamiltonian or not
        @param pathTaken the path done by the cicle
        @return true if the cicle is hamiltonian, false otherwise
    * */
	public boolean isHamiltonian(List<Node> pathTaken){
		ListIterator<Node> path=pathTaken.listIterator();
   		int nodes[]= new int[graph.getNodeNumber()];
		Node next=null;

   		for(int i=0;i<nodes.length;i++){
   			nodes[i]=0;
		}

   		while(path.hasNext()){
   			next =path.next();
   			nodes[next.getID()-1]=1;
		}

   		for(int i=0;i<nodes.length;i++){
			if(nodes[i]==0)
				return false;
		}
   		ArrayList<Node> completeCicle= new ArrayList<>(pathTaken);
   		path=completeCicle.listIterator();
   		Node previNode=next;
		float totalWeight=0;
		try {
			while (path.hasNext()) {
				next = path.next();
				totalWeight += next.getEdgeToWeight(previNode.getID());
				previNode = next;
			}
		}catch (NotThisEdge_exeption ex){
			ex.printStackTrace();
		}
   		graph.addPheromones( completeCicle.listIterator(), gamma/totalWeight);
		if(totalWeight<best_ham_weight){
			best_ham=completeCicle;
			best_ham_weight=totalWeight;
		}

		return true;
	}


	@Override
	public String toString() {//TODO tirar o primeiro no dos prints e mudar hamiltonians para hashmap de forma a nao haver ciclos repetidos
   		if(best_ham.size()==0)
   			return "";
   		String to_send="";
		to_send+='{';
		for(int i=0;i<best_ham.size()-1;i++){
			to_send+=+best_ham.get(i).getID();
			if(i!=(best_ham.size()-2))
				to_send+=",";
		}
		to_send+='}';
		return to_send;
	}

	public float getAlpha() {
   		return alpha;
	}

	public float getBeta() {
		return beta;
	}

	public float getDelta() {
		return delta;
	}

	public float getGamma() {
		return gamma;
	}


}
