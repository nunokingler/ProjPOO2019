import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Cavaco on 28-Apr-19.
 */
public class PEC {

    private ArrayList<Event> events;
    static Random random = new Random();

    public PEC() {
        this.events = new ArrayList<>() ;
    }
    public Event nextEvent()throws NoEventException {
        if(!events.isEmpty()){
            Event ev= events.get(0);
            events.remove(ev);
            return ev;
        }
        else throw new NoEventException("There are no events on the PEC, did you forget to add/readd them?");
    }
    public void addEvent(Event ev){
        events.add(ev);
        events.sort(Comparator.comparingDouble(Event::getTime));
    }

    public static double expRandom(double m) {
        double next = random.nextDouble();
        return -m*Math.log(1.0-next);
    }
}
