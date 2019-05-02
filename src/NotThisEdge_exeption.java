public class NotThisEdge_exeption extends Throwable {
    Edge this_edge;
    public NotThisEdge_exeption(String message,Edge thisEdge) {
        super(message);
        this_edge=thisEdge;
    }
}
