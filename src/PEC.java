import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Cavaco on 28-Apr-19.
 */
public class PEC {

    private ArrayList<Event> events;
    static Random random = new Random();
    private double currentTime;

    public PEC() {
        this.events = new ArrayList<>() ;
        currentTime=0;
    }
    public Event nextEvent()throws NoEventException {
        if(!events.isEmpty()){
            Collections.sort(events, new Comparator<Event>() {
                        public int compare(Event p1, Event p2) {
                            return Double.compare(p1.getTime(),p2.getTime());
                        }
                    });
            Event ev= events.get(0);
            events.remove(ev);
            currentTime=ev.getTime();
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

    public double getCurrentTime(){
        return currentTime;
    }
    public boolean contains(EvaporationEvent evaporationEvent) {
        return events.contains(evaporationEvent);
    }
}
