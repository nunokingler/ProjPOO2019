package pec;

import event.Event;
import exception.NoEventException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/***
 * Created by Cavaco on 28-Apr-19.
 */
/**{@link PEC} implements an event scheduler sorted by {@link Event}.getTime() */
public class PEC {

    private ArrayList<Event> events;
    private static Random random = new Random();
    private double currentTime;
/** Default constructor for PEC
 * */
    public PEC() {
        this.events = new ArrayList<>() ;
        currentTime=0;
    }
    /**Returns the next event with the lowest getTime()
     * @return Event the event with the lowest getTime()
     * @throws NoEventException if there is no events left on the PEC
     *  */
    public Event nextEvent()throws NoEventException {
        if(!events.isEmpty()){
            Event ev= events.get(0);
            events.remove(ev);
            currentTime=ev.getTime();
            return ev;
        }
        else throw new NoEventException("There are no events on the PEC, did you forget to add/readd them?");
    }
    /**Adds an event to the pec
     * @param ev the event to add to pec
     *  */
    public void addEvent(Event ev){
        events.add(ev);
        events.sort(Comparator.comparingDouble(Event::getTime));
    }
    /**Returns a random value using input parameters and following an exponential distribution
     * @param m the event to add to pec
     * @return returns a value acording to exponentional distribution
     *  */
    public static double expRandom(double m) {
        double next = random.nextDouble();
        return -m*Math.log(1.0-next);
    }
    /**Returns the time of the last removed event
     * @return double getTime of last removed event
     *  */
    public double getCurrentTime(){
        return currentTime;
    }
    /**Used to check if an event is in the pec already
     * @param event the event to check
     * @return true if the event is in the pec, false otherwise
     *  */
    public boolean contains(Event event) {
        return events.contains(event);
    }
}
