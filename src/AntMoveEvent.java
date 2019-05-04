
public class AntMoveEvent implements Event {
    private float sigma;
    private double time_for_next_move;
    private Ant ant;
    private PEC pec;
    public AntMoveEvent(Ant ant,float sig, PEC eventManager) {
        sigma=sig;
        this.ant=ant;
        pec=eventManager;
        time_for_next_move=0;
        pec.addEvent(this);
    }

    @Override
    public void doEvent() {
        time_for_next_move= PEC.expRandom(time_for_next_move+ant.next(null));
        pec.addEvent(this);
    }

    @Override
    public double getTime() {
        return time_for_next_move;
    }
}
