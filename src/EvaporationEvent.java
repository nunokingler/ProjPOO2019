public class EvaporationEvent implements Event{
    private float n // related to time
            ,p;     //related to value
    private double timeForNextEvaporation;
    private Edge edge;
    private PEC pec;
    public EvaporationEvent(Edge edge, float evaporationTime,float evaporationValue) {
        this.edge= edge;
        n=evaporationTime;
        p=evaporationValue;
        timeForNextEvaporation=0;
    }

    @Override
    public void doEvent() {
        edge.evaporate(p);
        timeForNextEvaporation+=PEC.expRandom(n);
        pec.addEvent(this);
    }

    @Override
    public double getTime() {
        return timeForNextEvaporation;
    }
}
