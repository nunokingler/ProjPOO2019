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
       // alpha=al;
       // beta=bt;
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
                boolean is_ham=false;
                if(current_node.hasEdge(path.get(0).getID())){
                    ArrayList<Node> possibleHamil= new ArrayList<>(path);
                    possibleHamil.add(path.get(0));
                    if(colony.isHamiltonian(possibleHamil.listIterator())){  //the cicle is hamiltonian, there is no problem
                        current_node=path.get(0);
                        path= new ArrayList<>();
                        path.add(current_node);
                        is_ham=true;
                    }
                }
                if(!is_ham){
                    current_node= calc_probs(current_node,new ArrayList<>(),colony.getAlpha(),colony.getBeta());
                    int index= path.indexOf(current_node);
                    for(int i= index+1;i<path.size()-1;i++){
                        path.remove(i);
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
        return PEC.expRandom(travel_back_edge.getWeight()*sigma);//TODO formula para travel time
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
                float probability= (alpha+edg.getPheromoneLevel())/(beta+edg.getWeight());             //TODO FORMULA
                probs[counter]= probability;
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
        for(int i =nmbrOfProbs-1;i>=0;i--){
            if(ticket<probs[i]){
                return n[i];
            }
        }
        return null;
    }

    @Override
    public Event getEvent() {
        return event;
    }
}
