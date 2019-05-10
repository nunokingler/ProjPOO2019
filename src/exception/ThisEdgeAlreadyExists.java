package exception;
/**Exception called by {@link simulation.Simulation} when an edge is added that connects 2 nodes that were already connected
 * */
public class ThisEdgeAlreadyExists extends Exception {
    public ThisEdgeAlreadyExists() {super();
    }
}
