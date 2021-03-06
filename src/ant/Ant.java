package ant;
import event.AntMoveEvent;
import event.Event;
import event.EventHolder;
import exception.NotThisEdge_exeption;
import pec.PEC;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
/**{@link Ant} class, implements the static function to calculate the probabilities of hopping to the next node and
 * the function that actually performs the jump
 * */
public class Ant implements EventHolder{
	/**
     * The current node where the ant is located*/
    protected Node current_node;
    /**
     * The path the ant traveled to get here
     * */
    protected ArrayList<Node> path;
 // private float alpha, beta;
 /**
    The {@link AntColony} that spawned this ant*/
    protected AntColony colony;
    /**
     * The event used to move the ant*/
    protected AntMoveEvent event;
    /** Default constructor for ant
        @param colony the colony this ant belongs to, it is used to get parameters regaring movement
        @param current_node the starting node for this ant
        @param sig used to calculate travel time
    * */
    public Ant(AntColony colony,Node current_node,float sig) {
        this.current_node = current_node;
        path=new ArrayList<>();
        path.add(current_node);
        this.colony=colony;
        event = new AntMoveEvent(this);
    }
/** This method calculates what the next hop location will be acording to the current path and returns the travel time of
    the edge chosen
    @return double travel time
* */
    public double nextHop(){

        ListIterator<Edge> edges = current_node.getEdges();
        boolean hasUnvisitedNodes= false;
        try{
            while(edges.hasNext()){
                Edge next = edges.next();
                if(!path.contains(next.otherNode(current_node))){
                    hasUnvisitedNodes=true;
                    break;
                }
            }
            Node previNode=current_node;
            if(hasUnvisitedNodes){
                current_node= calc_probs(current_node,path,colony.getAlpha(),colony.getBeta());
                path.add(current_node);
            }
            else{

                current_node= calc_probs(current_node,new ArrayList<>(),colony.getAlpha(),colony.getBeta());
                ArrayList<Node> possibleHamil= new ArrayList<>(path);
                possibleHamil.add(current_node);
                if(current_node==path.get(0) && colony.isHamiltonian(possibleHamil)){//is hamil
                    path= new ArrayList<>();
                    path.add(current_node);
                }
                else{
                    int index= path.indexOf(current_node);
                    int previousSize=path.size();
                    for(int i= 0;i<previousSize-index-1;i++){
                        path.remove(index+1);
                    }
                }
            }
            Edge edge_used= current_node.getEdgeTo(previNode);
            return travelTime(edge_used,colony.getDelta());
        }catch (NotThisEdge_exeption ex){
            ex.printStackTrace();
        }
         return -1;
    }
    /** This method returns the time it takes to travel a specific edge acording to a parameter sigma
        @param travel_back_edge the edge to calculate travel time
        @param sigma used to calculate travel time
        @return double travel time
    * */
    private double travelTime(Edge travel_back_edge,float sigma) {
        return PEC.expRandom(travel_back_edge.getWeight()*sigma);
    }
    /** This method returns the node to travel to acording to the to_avoid list and using parameters alpha and beta
        @param s node from wich to travel
        @param to_avoid list of nodes that cant be traveled to

        @param alpha parameter used to calculated the next node
        @param beta  parameter used to calculated the next node

        @return Node the node for wich was decided the ant would travel
    * */
    private static Node calc_probs(Node s, ArrayList<Node> to_avoid,float alpha,float beta){
        int nmbrOfProbs= s.getEdgeNmbr(), counter=0;
        float probs[]=new float[nmbrOfProbs],total=0;
        Node n[]= new Node[nmbrOfProbs];
        ListIterator<Edge> eit=s.getEdges();

        while(eit.hasNext()){                                                                               //WHILE THERE ARE STILL EDGES TO CHECK
            Edge edg = eit.next();
            boolean is_valid=true;

            for (Node to_check : to_avoid) {                                                                           //CHECK IF NODE IS ONE OF THE NODES TO AVOID
                try {
                    if (edg.otherNode(s).getID() == to_check.getID()) {
                        is_valid = false;
                    }
                } catch (NotThisEdge_exeption ex) {
                    ex.print_da_data();
                    ex.printStackTrace();
                }
            }
            if(is_valid){                                                                                   //IF THIS EDGE IS VALID ADD IT TO THE FORMULA
                float probability= (alpha+edg.getPheromoneLevel())/(beta+edg.getWeight());
                probs[counter]= probability+total;
                try {
                    n[counter]= edg.otherNode(s);
                } catch (NotThisEdge_exeption notThisEdge_exeption) {
                    notThisEdge_exeption.print_da_data();
                    notThisEdge_exeption.printStackTrace();
                }
                counter++;
                total+= probability;
            }
        }
        Random r = new Random();
        float ticket = r.nextFloat() * (total);
        float[] probabilities=new float[counter];
        for(int i =0;i<counter;i++){
            probabilities[i]=probs[i]/total;
        }
        for(int i=0;i<counter;i++){
            if(ticket<probabilities[i])
                return n[i];
        }
        return n[0];
    }
    /** This method returns the event for wich this instance is associated
    @return the event related to this ant
* */
    @Override
    public Event getEvent() {
        return event;
    }
}
