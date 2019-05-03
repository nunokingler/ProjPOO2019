import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class Ant {
    private Node current_node;
    private ArrayList<Node> path;
    private float alpha, beta;

    public Ant(Node current_node,float al,float bt) {
        this.current_node = current_node;
        path=new ArrayList<Node>();
        alpha=al;
        beta=bt;
    }

    public int next(ArrayList<Node> Path, Node previNode)  {
        int nextNode=0;
        if(Path.get(0).getID()==current_node.getID()){
            //TODO meter ferormonas nessa cena
        }
//        if(path.get(path.size()-1)==this)
//            Path.remove(this);

        if(current_node.getEdgeNmbr()==1 || (current_node.getEdgeNmbr()==2 && previNode!=null)){//Estamos ou viemos de um beco
            Node temp= path.get(path.size()-1);
            path.remove(temp);
            current_node=path.get(path.size()-1);
            return this.next(path,temp);
            //Path.get(Path.size()-1).next(Path,temp);
        }
        else{//EVERYTHING IS GOOD, calc probability and add node to chain
            ArrayList<Node> arr= new ArrayList<Node>();
            arr.add(Path.get(Path.size()-1));
            if(previNode!=null)
                arr.add(previNode);
            path.add(current_node);
            return Ant.calc_probs(current_node,arr);
        }
        return -1;
    }

    private static int calc_probs(Node s, ArrayList<Node> to_avoid){
        int nmbrOfProbs= s.getEdgeNmbr(), counter=0;
        float probs[]=new float[nmbrOfProbs],total=0;
        int n[]= new int[nmbrOfProbs];
        ListIterator<Edge> eit=s.getEdges();

        while(eit.hasNext()){//WHILE THERE ARE STILL EDGES TO CHECK
            Edge edg = eit.next();
            boolean is_valid=true;
            ListIterator<Node> nit= to_avoid.listIterator();

            while(nit.hasNext()){///CHECK IF NODE IS ONE OF THE NODES TO AVOID
                Node to_check=nit.next();
                try{
                    if( edg.otherNode(s)==to_check.getID()){
                        is_valid=false;
                    }
                }
                catch (NotThisEdge_exeption ex) {
                    ex.print_da_data();
                    ex.printStackTrace();
                }
            }
            if(is_valid){//IF THIS EDGE IS VALID ADD IT TO THE FORMULA
                float probability= 1+1;//TODO FORMULA
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
        return -1;
    }

}
