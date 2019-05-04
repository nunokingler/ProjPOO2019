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
/*
    public double next( Node previNode)  {
        int nextNode=0;
        float travel_time;
        try {
            if (current_node.getEdgeNmbr() == 1 || (current_node.getEdgeNmbr() == 2 && previNode != null)) {//We are or came from a no way out path
                Node temp = path.get(path.size() - 1);
                path.remove(temp);                                                                          //this node is no good so we remove it from the path
                current_node = path.get(path.size() - 1);                                                   // get the new current node
                Edge travel_back_edge = current_node.getEdgeTo(temp);                                       //and the edge we took to get to it
                travel_time = travelTime(travel_back_edge);                                                 // calculate the travel time to go back
                return travel_time + this.next(temp);                                                       //and join it with the travel time to go for a new node
            }

            else {                                                                                          //EVERYTHING IS GOOD, calc probability and add node to chain
                ArrayList<Node> arr = new ArrayList<>();                                                    //this is the list of nodes to avoid
                arr.add(path.get(path.size() - 1));                                                         //we avoid the node we came from
                if (previNode != null)                                                                      //and if in a no way out we also add it to the avoid list
                    arr.add(previNode);
                path.add(current_node);                                                                     //add the current node to the path
                Node new_node = Ant.calc_probs(current_node, arr);
                travel_time = travelTime(new_node.getEdgeTo(current_node));
                return travel_time;
            }
        }catch (NotThisEdge_exeption notThisEdge_exeption) {
            notThisEdge_exeption.printStackTrace();
        }
        if(path.get(0).getID()==current_node.getID()){
            if(colony.isHamiltonian(path.listIterator())){
                //TODO meter ferormonas nessa cena
                path=new ArrayList<>();
                path.add(current_node);
            }
            else
        }
        return 0;
    }*/
    public float nextHop(){
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
                current_node= calc_probs(current_node,path);
                path.add(current_node);
            }
            else{
                current_node= calc_probs(current_node,new ArrayList<Node>());
                int index= path.indexOf(current_node);
                for(int i= index+1;i<path.size()-1;i++){
                    path.remove(i);
                }
            }
            Edge edge_used= current_node.getEdgeTo(previNode);
            return travelTime(edge_used);
        }catch (NotThisEdge_exeption ex){
            ex.printStackTrace();
        }
         return -1;
    }

    private float travelTime(Edge travel_back_edge) {
        return travel_back_edge.getWeight();//TODO formula para travel time
    }

    private static Node calc_probs(Node s, ArrayList<Node> to_avoid){
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
                float probability= 1+1;             //TODO FORMULA
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
        float ticket = r.nextFloat();
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
