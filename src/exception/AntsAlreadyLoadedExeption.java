package exception;
/**{@link Exception} called by @{@link simulation.Simulation} when an {@link ant.Edge} is added after a call to LoadAnts() */
public class AntsAlreadyLoadedExeption extends Exception {
    public AntsAlreadyLoadedExeption(String message) {
        super(message);
    }
}
