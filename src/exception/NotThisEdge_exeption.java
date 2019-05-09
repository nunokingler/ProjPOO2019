package exception;
import ant.Edge;

public class NotThisEdge_exeption extends Exception {
    private Edge this_edge;
    private int this_node;
    public NotThisEdge_exeption(String message,Edge thisEdge,int node) {
        super(message);
        this_edge=thisEdge;
        this_node = node;
    }
    public void print_da_data(){
        System.out.println("Fam, tou so fdd, tenho uma edge que nao ta ligada a mim, sou o node");
        System.out.println(this_node);
        System.out.println(" e o edge tinha chave");
        System.out.println(this_edge);
    }
}
