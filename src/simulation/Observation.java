package simulation;
import ant.AntColony;
import event.AntMoveEvent;
import event.EvaporationEvent;
import event.Event;
import pec.PecHolder;

public class Observation implements Event{
    private float time,maxTime;
    private int observation,steps;
    private AntColony colony;

    public Observation(float maxTime, int steps, AntColony colony) {
        this.colony = colony;
        observation=0;
        PecHolder.pec.addEvent(this);
        this.maxTime=maxTime;
        this.steps=steps;
    }

    @Override
    public void doEvent() {
        System.out.println("Observation "+observation+':');
        System.out.println("    Present instant:                "+time);
        System.out.println("    Number of move events:          "+AntMoveEvent.getNmbr_of_moves());
        System.out.println("    Number of evaporation events:   "+EvaporationEvent.getNmbr_of_moves());
        System.out.println("    Hamiltonian cycle:              "+colony.toString());
        time+=maxTime/steps;
        observation++;
        PecHolder.pec.addEvent(this);
    }

    @Override
    public double getTime() {
        return time;
    }
}
