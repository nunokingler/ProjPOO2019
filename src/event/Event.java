package event;
/**
 *  {@link Event} defines an event that ocurrs @ time getTime() and does the event as an implementation of doEvent()*/
public interface Event{
    /** Does the refered event

    * */
    void doEvent();
    /** Returns the time for this event
    @return double event time
* */
    double getTime();
}
