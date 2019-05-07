
public class AntMoveEvent implements Event {
    private float sigma;
    private double time_for_next_move;
    private Ant ant;
    private PEC pec;
    private static int  nmbr_of_moves=0;
    
    public AntMoveEvent(Ant ant,float sig) {
        sigma=sig;
        this.ant=ant;
        pec=PecHolder.pec;
        pec.addEvent(this);
        time_for_next_move=0;
        pec.addEvent(this);
    }

    @Override
    public void doEvent() {
        time_for_next_move= PEC.expRandom(time_for_next_move+ant.nextHop());
        pec.addEvent(this);
        nmbr_of_moves++;
    }

    @Override
    public double getTime() {
        return time_for_next_move;
    }

    public static int getNmbr_of_moves() {
        return nmbr_of_moves;
    }

}
