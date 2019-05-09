package event;
public interface Event{
    /** Does the refered event

    * */
    void doEvent();
    /** Returns the time for this event
    @return double event time
* */
    double getTime();
}
