package ant;
import event.EvaporationEvent;
import event.Event;
import event.EventHolder;
import exception.NotThisEdge_exeption;
/**{@link Edge} class, links 2 {@link Node}s, also has an assosiated weight, pheromoneLevel and {@link Event} */
public class Edge implements EventHolder{

	private float weight;
	/**
	 * Current pheromone level for this edge */
	protected float pheromoneLevel;
	/**
	 * The nodes that this edge connects */
	protected Node node1,node2;
	private EvaporationEvent event;

	public Edge(Edge e) {
		this.weight=e.weight;
		this.node1=e.node1;
		this.node2=e.node2;
		this.event = e.event;
		this.pheromoneLevel=e.pheromoneLevel;
	}
	/** Default constructor for Edge
	 * @param weight the desired weight of this edge
        @param evaporationTime parameter regargint the time for each evaporation
        @param evaporationValue amount taken each evaporation
        @param node1 nodes1 that this edge connects
		@param node2 nodes2 that this edge connects
    * */
	public Edge(float weight, float evaporationTime,float evaporationValue, Node node1, Node node2) {
		this.weight = weight;
		this.node1 = node1;
		this.node2 = node2;
		this.event=new EvaporationEvent(this,evaporationTime,evaporationValue);
	}
	/** evaporates according to the value given by
	@param evaporationValue value to evaporate by
	@return true if there is more pheromones to evaporate(another event needs to occur) false otherwise
    * */
    public boolean evaporate(float evaporationValue) {

	    float next_pheromone = pheromoneLevel-evaporationValue;//TODO EVAPORATION FORMULA

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
	/** returns the node that is not referenced in @param N , @throws NotThisEdge_exeption if the edge does not contain N
	 * @param N one of the nodes this edge conains
	   @return Node other node
	   @throws NotThisEdge_exeption if this edge does not contain the node N
    * */
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

    public void setWeight(float weight) {
		this.weight=weight;
    }
}
