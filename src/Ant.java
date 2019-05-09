import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class Ant implements EventHolder{
	
    private Node current_node;
    private ArrayList<Node> path;
 // private float alpha, beta;
    private AntColony colony;
    private AntMoveEvent event;

    public Ant(AntColony colony,Node current_node,float sig) {
        this.current_node = current_node;
        path=new ArrayList<>();
        path.add(current_node);
        this.colony=colony;
        event = new AntMoveEvent(this,sig);
    }

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

    private double travelTime(Edge travel_back_edge,float sigma) {
        return PEC.expRandom(travel_back_edge.getWeight()*sigma);
    }

    private static Node calc_probs(Node s, ArrayList<Node> to_avoid,float alpha,float beta){
        int nmbrOfProbs= s.getEdgeNmbr(), counter=0;
        float probs[]=new float[nmbrOfProbs],total=0;
        Node n[]= new Node[nmbrOfProbs];
        ListIterator<Edge> eit=s.getEdges();

        while(eit.hasNext()){                                                                               //WHILE THERE ARE STILL EDGES TO CHECK
            Edge edg = eit.next();
            boolean is_valid=true;
            ListIterator<Node> nit= to_avoid.listIterator();

            while(nit.hasNext()){                                                                           //CHECK IF NODE IS ONE OF THE NODES TO AVOID
                Node to_check=nit.next();
                try{
                    if( edg.otherNode(s).getID()==to_check.getID()){
                        is_valid=false;
                    }
                }
                catch (NotThisEdge_exeption ex) {
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
        //System.out.println("ticket is "+ticket+"counter is "+counter);
        float[] probabilities=new float[counter];
        for(int i =0;i<counter;i++){
            probabilities[i]=probs[i]/total;
        }
        for(int i=0;i<counter;i++){
            if(ticket<probabilities[i])
                return n[i];
        }
/*        for (float pro:probs
             ) {
            System.out.print(", "+pro);
        }
        System.out.println("--");*/
        return n[0];
    }

    @Override
    public Event getEvent() {
        return event;
    }
}
