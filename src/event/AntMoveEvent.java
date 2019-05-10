package event;
import ant.Ant;
import pec.PEC;
import pec.PecHolder;
/**{@link AntMoveEvent} implements moving an {@link Ant} as an {@link Event} */
public class AntMoveEvent implements Event {
    private double time_for_next_move;
    private Ant ant;
    private PEC pec;
    private static int  nmbr_of_moves=0;

    /** Default constructor for AntMoveEvent
    @param ant the ant corresponting to this move
* */
    public AntMoveEvent(Ant ant) {
        this.ant=ant;
        pec=PecHolder.pec;
        time_for_next_move=0;
        pec.addEvent(this);
    }
    /** Moves the ant as an event
    * */
    @Override
    public void doEvent() {
        time_for_next_move= time_for_next_move+ PEC.expRandom(ant.nextHop());
        pec.addEvent(this);
        nmbr_of_moves++;
    }
    /** returns the time for the next move
    @return double time
    * */
    @Override
    public double getTime() {
        return time_for_next_move;
    }
    /** returns the total number of moves since program start
    @return int number of moves
    * */
    public static int getNmbr_of_moves() {
        return nmbr_of_moves;
    }

}
