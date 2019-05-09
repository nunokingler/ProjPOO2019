package ant;
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

	/** Default constructor for AntColony
        @param _Antsnumber the max number of ants of this colony
        @param graph the graph where this colony is placed
        @param  Starting_node starting_node the number of node containing this ant colony
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
/**sets the value of alpha(used to calculate node jumps)
 * @param alpha value to set alpha to
 * */
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	/**sets the value of beta(used to calculate node jumps)
	 * @param beta value to set beta to
	 * */
	public void setBeta(float beta) {
		this.beta = beta;
	}
	/**sets the value of delta(used to calculate time between node jumps)
	 * @param delta value to set delta to
	 * */
	public void setDelta(float delta) {
		this.delta = delta;
	}
	/**sets the value of gamma(used to calculate the ammout of pheromones to add)
	 * @param gamma value to set gamma to
	 * */
	public void setGamma(float gamma) {
		this.gamma = gamma;
	}
	/** Evaluates if a certain cicle is hamiltonian or not
        @param pathTaken the path done by the cicle
        @return true if the cicle is hamiltonian, false otherwise
    * */
	protected boolean isHamiltonian(List<Node> pathTaken){
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

		for (int node : nodes) {
			if (node == 0)
				return false;
		}
   		ArrayList<Node> completeCicle= new ArrayList<>(pathTaken);
   		path=completeCicle.listIterator();
   		Node previNode=next;
		float totalWeight=0;

		while (path.hasNext()) {
			next = path.next();
			totalWeight += next.getEdgeToWeight(previNode.getID());
			previNode = next;
		}

   		graph.addPheromones( completeCicle.listIterator(), gamma/totalWeight);
		if(totalWeight<best_ham_weight){
			best_ham=completeCicle;
			best_ham_weight=totalWeight;
		}

		return true;
	}

	/** Returns the shortest Hamiltonian cicle found yet
	 * @return String Hamiltonian cycle in format {node0,node1,node2,...,noden-1} if we consider node n == node 1
	 * */
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
	/** Returns the current alpha for this antColony
	 * @return float alpha
	 * */
	public float getAlpha() {
   		return alpha;
	}
	/** Returns the current beta for this antColony
	 * @return float beta
	 * */
	public float getBeta() {
		return beta;
	}
	/** Returns the current delta for this antColony
	 * @return float delta
	 * */
	public float getDelta() {
		return delta;
	}
	/** Returns the current gamma for this antColony
	 * @return float gamma
	 * */
	public float getGamma() {
		return gamma;
	}


}
