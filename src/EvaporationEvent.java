public class EvaporationEvent implements Event{
    private float n // related to time
            ,p;     //related to value
    private double timeForNextEvaporation;
    private Edge edge;
    private PEC pec;
    private static int nmbr_of_evaps=0;
    public EvaporationEvent(Edge edge, float evaporationTime,float evaporationValue) {
        this.edge= edge;
        n=evaporationTime;
        p=evaporationValue;
        timeForNextEvaporation=0;
        pec=PecHolder.pec;
    }

    @Override
    public void doEvent() {
        if(edge.evaporate(p)){
            timeForNextEvaporation+=PEC.expRandom(n);
            pec.addEvent(this);
        }
        nmbr_of_evaps++;
    }

    @Override
    public double getTime() {
        return timeForNextEvaporation;
    }

    public static int getNmbr_of_moves() {
        return nmbr_of_evaps;
    }
}
