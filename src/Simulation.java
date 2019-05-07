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




}
