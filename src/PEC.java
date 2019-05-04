import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;

/**
 * Created by Cavaco on 28-Apr-19.
 */
public class PEC {

    private ArrayList<Event> events;
    private float maxTime;
    private int steps;

    public PEC(float maxTime,int steps) {
        this.events = new ArrayList<>() ;
        this.maxTime=maxTime;
        this.steps=steps;
    }
    public void addEvent(Event ev){
        events.add(ev);
        events.sort((p1, p2) -> Float.compare(p1.getTime(),p2.getTime()));
    }
    public void doEvents(){
        Event ev=events.get(0);
        events.remove(ev);
        float time=ev.getTime(),timePerStep=maxTime/steps;
        int stepCounter=0;
        while (time<maxTime) {
            ev.doEvent();
            if(time>stepCounter*timePerStep){
                stepCounter++;
                doStatistics();
            }
            ev = events.get(0);
            time=ev.getTime();
        }
        doStatistics();
    }

    private void doStatistics() {

    }
}
