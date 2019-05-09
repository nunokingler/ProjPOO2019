package simulation;
import java.util.ArrayList;

import ant.AntColony;
import exception.DiferentWeightExeption;
import exception.DuplicatesExeption;
import exception.NotThisEdge_exeption;
import exception.SizeMismatchException;
import exception.ThisEdgeAlreadyExists;

public class Simulation {

	private float finalinst,plevel;
    private int antcolsize;
    private AntColony antColony;
    private Graph graph;
    private Observation obs;
    

	public Simulation(float finalinst, float n, float p) {
		//super();
		this.finalinst = finalinst;
		this.plevel = p;
		graph= new Graph(n,p);
	}

	public void setAlpha(float alpha){
		antColony.setAlpha(alpha);
	}

	public void setBeta(float beta){
		antColony.setBeta(beta);
	}

	public void setDelta(float delta){
		antColony.setDelta(delta);
	}

	public void setGamma(float gamma){ antColony.setGamma(gamma);}

	public void addNode(int nodeNmbr,ArrayList<Float>weights,ArrayList<Integer> connections) throws DiferentWeightExeption, DuplicatesExeption, SizeMismatchException {
		graph.addNode(nodeNmbr,weights,connections);
	}
	public void addEdge(int node1, int node2, float weight) throws NotThisEdge_exeption, ThisEdgeAlreadyExists {
		graph.addEdge(node1,node2,weight);
	}
	public void LoadAnts(int antcolsize, int nestNode){
		this.antcolsize = antcolsize;
		antColony=new AntColony(antcolsize,graph,nestNode);
		obs= new Observation(finalinst,20,antColony);
	}

	public String getAntColonyString(){
		return antColony.toString();
	}


}
