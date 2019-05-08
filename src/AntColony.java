import java.util.*;

public class AntColony {

	private int antsnumber,maxAnts;
	private ArrayList<Ant> ants;
	private HashMap<String,ArrayList<Node>> hamiltonians;
	private Node starting_node;
	private Graph graph;
	private float alpha;
	private float beta;
	private float delta;
	private float gamma;


	AntColony(int _Antsnumber, Graph graph, int Starting_node){
		this.maxAnts = _Antsnumber;

		antsnumber=_Antsnumber;
		this.graph=graph;
		this.starting_node=graph.getNode(Starting_node);
		hamiltonians =new HashMap<>();
		alpha=0;
		beta=0;
		delta =0;
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

	public void antFirstMove(){
   		if(starting_node.isEmpty()&&antsnumber<maxAnts)
   			ants.add(new Ant(this,starting_node,Constants.sigma));
	}
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
		String key="";
		try {
			while (path.hasNext()) {
				next = path.next();
				key+=next.toString();
				totalWeight += next.getEdgeToWeight(previNode.getID());
				previNode = next;
			}
		}catch (NotThisEdge_exeption ex){
			ex.printStackTrace();
		}
   		graph.addPheromones( completeCicle.listIterator(), gamma/totalWeight);

		if(!hamiltonians.containsKey(key))
			hamiltonians.put(key,completeCicle);
		return true;
	}


	@Override
	public String toString() {//TODO tirar o primeiro no dos prints e mudar hamiltonians para hashmap de forma a nao haver ciclos repetidos
   		if(hamiltonians.size()==0)
   			return "";
   		String to_send="";
   		/*
		for(int i = 0; i< hamiltonians.size(); i++){
			ArrayList<Node> path= hamiltonians.get(i);
			to_send+='{';
			for(int j=0;j<path.size()-1;j++){
				to_send+=","+path.get(j).getID()+",";
			}
			to_send+='}';
		}*/
		for (ArrayList<Node> cicle : hamiltonians.values()) {
			to_send+='{';
			for(int i=0;i<cicle.size()-1;i++){
				to_send+=+cicle.get(i).getID();
				if(i!=(cicle.size()-2))
					to_send+=",";
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

	public float getDelta() {
		return delta;
	}

	public float getGamma() {
		return gamma;
	}


}
