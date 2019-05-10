package event;
import pec.PEC;
import pec.PecHolder;
import ant.Edge;
/**{@link EvaporationEvent} implements removal of pheromones from an {@link Edge} as an {@link Event} */
public class EvaporationEvent implements Event{
    private float n // related to time
            ,p;     //related to value
    private double timeForNextEvaporation;
    private Edge edge;
    private PEC pec;
    private static int nmbr_of_evaps=0;

    /** Default constructor for EvaporationEvent
        @param edge the edge related to this evaporation
        @param evaporationTime parameter for specifying the time between evaporations
        @param evaporationValue parameter for specifying the amout of pheromonoes lost each evaporation
    * */
    public EvaporationEvent(Edge edge, float evaporationTime,float evaporationValue) {
        this.edge= edge;
        n=evaporationTime;
        p=evaporationValue;
        timeForNextEvaporation=0;
        pec=PecHolder.pec;
    }
    /** Evaporates as an event, if no pheromones are left after this evaporation the event is not called again
    * */
    @Override
    public void doEvent() {
        if(edge.evaporate(p)){
            timeForNextEvaporation+=PEC.expRandom(n);
            pec.addEvent(this);
        }
        nmbr_of_evaps++;
    }
    /** returns time for the next evaporation
    @return double
    * */
    @Override
    public double getTime() {
        return timeForNextEvaporation;
    }

    /** Method used to start evaporations after a node with no pheromones gets pheromones
* */
    public void start_evaps(){
       if(!pec.contains(this)){
           timeForNextEvaporation=pec.getCurrentTime()+PEC.expRandom(n);
           pec.addEvent(this);
       }
    }
    /** returns the number of evaporations made since program start
        @return int number of evaporations
    * */
    public static int getNmbr_of_moves() {
        return nmbr_of_evaps;
    }
}
