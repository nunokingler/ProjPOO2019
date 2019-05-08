public class Edge implements EventHolder{

	private float weight;
	private float pheromoneLevel;
	private float evaporation;
	private Node node1,node2;
	private EvaporationEvent event;

	public Edge(Edge e) {
		this.weight=e.weight;
		this.evaporation=e.evaporation;
		this.node1=e.node1;
		this.node2=e.node2;
		this.event = e.event;
	}

	public Edge(float weight, float evaporationTime,float evaporationValue, Node node1, Node node2) {
		this.weight = weight;
		this.evaporation = 0;
		this.node1 = node1;
		this.node2 = node2;
		this.event=new EvaporationEvent(this,evaporationTime,evaporationValue);
	}

    public boolean evaporate(float evaporationValue) {
	    float next_pheromone = pheromoneLevel-evaporationValue;
	    if(next_pheromone>0){
	    	pheromoneLevel=next_pheromone;
	    	return true;
		}
	    else{
	    	pheromoneLevel=0;
	    	return false;
		}
    }

    public float getWeight() {
		return weight;
	}

	public float getPheromoneLevel() {
		return pheromoneLevel;
	}

	public Node otherNode(Node N)throws NotThisEdge_exeption{
		if(N.getID()==node1.getID()){
			return node2;
		}
		else if(N.getID()==node2.getID()){
			return node1;
		}
		throw new NotThisEdge_exeption("This edge does not connect to that node",this,N.getID());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Edge edge = (Edge) o;
		return node1 == edge.node1 &&
				node2 == edge.node2;
	}

	@Override
	public int hashCode() {
		return node1.getID() + node2.getID();//Objects.hash(node1, node2);
	}

    @Override
    public Event getEvent() {
        return event;
    }

	public void addPheromones(float value_to_add) {
		if(pheromoneLevel==0){
			event.start_evaps();
		}
		pheromoneLevel+=value_to_add;
	}
}
