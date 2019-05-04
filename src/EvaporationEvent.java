public class EvaporationEvent implements Event{
    private float n // related to time
            ,p;     //related to value
    private double timeForNextEvaporation;
    private Edge edge;
    private PEC pec;
    private int nmbr_of_moves;
    public EvaporationEvent(Edge edge, float evaporationTime,float evaporationValue) {
        this.edge= edge;
        n=evaporationTime;
        p=evaporationValue;
        timeForNextEvaporation=0;
        pec=PecHolder.pec;
        nmbr_of_moves=0;
    }

    @Override
    public void doEvent() {
        edge.evaporate(p);
        timeForNextEvaporation+=PEC.expRandom(n);
        if(!(edge.getPheromoneLevel()==0))
            pec.addEvent(this);
        nmbr_of_moves++;
    }

    @Override
    public double getTime() {
        return timeForNextEvaporation;
    }

    public int getNmbr_of_moves() {
        return nmbr_of_moves;
    }
}
