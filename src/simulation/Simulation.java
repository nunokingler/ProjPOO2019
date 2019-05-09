package simulation;
import ant.AntColony;
import ant.Graph;
import event.Observation;
import exception.AntsAlreadyLoadedExeption;
import exception.ThisEdgeAlreadyExists;

public class Simulation {

	private float finalinst;
    private AntColony antColony;
    private Graph graph;
    private Observation obs;
    private boolean init;

	/** Default constructor for Edge
        @param finalinst final instant of the simulation
        @param n parameter related to evaporation
        @param p second parameter related to evaporation
    * */
	public Simulation(float finalinst, float n, float p) {
		//super();
		this.finalinst = finalinst;
		graph= new Graph(n,p);
		init=true;
	}
	/**
 		sets alpha for the simulation
	 @param alpha parameter used for calculating next hop
	 * */
	public void setAlpha(float alpha){
		antColony.setAlpha(alpha);
	}
	/**
	 sets beta for the simulation
	 @param beta parameter used for calculating next hop
	  * */
	public void setBeta(float beta){
		antColony.setBeta(beta);
	}
	/**
	 sets delta for the simulation
	 @param delta parameter used for calculating time before next hop
	  * */
	public void setDelta(float delta){
		antColony.setDelta(delta);
	}
	/**
	 sets gamma for the simulation
	 @param gamma parameter used for calculating pheromones added
	  * */
	public void setGamma(float gamma){ antColony.setGamma(gamma);}
	/**
	 Adds an edge to graph, can only be called before LoadAnts()
	 @param node1 of of the nodes that the edge connects
	 @param node2  the other node
	 @param weight weight of the node to be added
	 @throws ThisEdgeAlreadyExists if there is already one edge connecting these two nodes
	  * */
	public void addEdge(int node1, int node2, float weight) throws ThisEdgeAlreadyExists {
		if(init)
			graph.addEdge(node1,node2,weight);
	}
	/**
	 Starts the ant conoly on the respective node after all nodes were initialized
	 @param antcolsize parameter used for calculating pheromones added
	 @param nestNode the number of the nest node
	 @throws AntsAlreadyLoadedExeption if its the second time this method is called
	  * */
	public void LoadAnts(int antcolsize, int nestNode) throws AntsAlreadyLoadedExeption {
		if(init){
			antColony=new AntColony(antcolsize,graph,nestNode);
			obs= new Observation(finalinst,20,antColony);
			init=false;
		}
		else{
			throw new AntsAlreadyLoadedExeption("This simulation was already started");
		}
	}



}
