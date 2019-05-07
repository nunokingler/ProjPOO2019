import java.util.ArrayList;

public class Simulation {

	private float finalinst,plevel;
    private int antcolsize;
    private AntColony antColony;
    private Graph graph;



	public Simulation(float finalinst,float alpha, float beta, float delta, float p, float n, int antcolsize, int nestNode) {
		//super();
		this.finalinst = finalinst;
		this.plevel = p;
		this.antcolsize = antcolsize;
		graph= new Graph(n,p);
		antColony=new AntColony(antcolsize,graph,nestNode);
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

	public void addNode(int nodeNmbr,ArrayList<Float>weights,ArrayList<Integer> connections) throws DiferentWeightExeption, DuplicatesExeption, SizeMismatchException {
		graph.addNode(nodeNmbr,weights,connections);
	}

	public String getAntColonyString(){
		return antColony.toString();
	}


}
