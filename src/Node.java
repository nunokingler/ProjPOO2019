
//contains a list of weights

import jdk.internal.org.objectweb.asm.tree.analysis.SourceValue;
import sun.awt.geom.Curve;

import java.util.*;

public class Node {
	
		private int nodeidx;
		private ArrayList<Edge> edges;

	public Node(int nodeidx, ArrayList<Edge> edges) {
		this.nodeidx = nodeidx;
		this.edges = edges;
	}

	public Node(int nodeidx) {
		this.nodeidx = nodeidx;
		this.edges = new ArrayList<>();
	}

	/*
        public int nextHop(ArrayList<Node> Path)  {
            int nextNode=0;
            if(Path.get(0).getID()==nodeidx){

            }
            try{
                if(edges.size()==1 && Path.get(Path.size()-1).getID()==edges.get(0).otherNode(this)){

                    boolean dead_end=true;
                    ListIterator<Edge> it;//= this.getEdges();
                    for(int i=Path.size()-2;dead_end;i--) {
                        Node s=Path.get(i);
                        if(Path.size()==1){
                            System.out.println("EVERYTHING IS DED END!!kill me");
                        }
                        if(s.getEdgeNmbr()<=2){// This node is the only path backwards, next node
                            i--;
                        }
                        else{	//This node has more than 2 edges wich means that it can get us out of this situation
                            ArrayList<Node> to_avoid=new ArrayList<Node>();
                            to_avoid.add(Path.get(i-1));
                            to_avoid.add(Path.get(i+1));
                            Node.calc_probs(s,to_avoid );
                        }
                    }
                }
                else{//EVERYTHING IS GOOD, calc probability and add node to chain
                    ArrayList<Node> arr= new ArrayList<Node>();
                    arr.add(Path.get(Path.size()-1));
                    Path.add(this);
                    return Node.calc_probs(this,arr);
                }
            }
            catch(NotThisEdge_exeption ex){
                ex.print_da_data();
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
            return nextNode;
        }

        public int next(ArrayList<Node> Path, Node previNode)  {
            int nextNode=0;
            if(Path.get(0).getID()==nodeidx){

            }
            if(Path.get(Path.size()-1)==this)
                Path.remove(this);

                if(edges.size()==1 || (edges.size()==2 && previNode!=null)){//Estamos ou viemos de um beco
                    Path.get(Path.size()-1).next(Path,this);
                }
                else{//EVERYTHING IS GOOD, calc probability and add node to chain
                    ArrayList<Node> arr= new ArrayList<Node>();
                    arr.add(Path.get(Path.size()-1));
                    if(previNode!=null)
                        arr.add(previNode);
                    Path.add(this);
                    return Node.calc_probs(this,arr);
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
                    float probability= 1+1;
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
    */
	public int getEdgeNmbr(){
		return edges.size();
	}


	public ListIterator<Edge> getEdges(){
		ArrayList<Edge> edg = new ArrayList<Edge>();
		for(int i= 0;i<edges.size();i++){
			edg.add( new Edge(edges.get(i)));
		}
		return edg.listIterator(edg.size());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node node = (Node) o;
		return nodeidx == node.nodeidx;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nodeidx);
	}

	public int getID() {
		return nodeidx;
	}

	public void addEdge(Edge edge_to_add) {
		edges.add(edge_to_add);
	}

	@Override
	public String toString() {
		return "Node{" +
				"nodeidx=" + nodeidx +
				'}';
	}
	public float hasEdge(int other_nodeNmbr) throws NotThisEdge_exeption {
		for(int i =0;i<edges.size();i++){
			if(this.edges.get(i).otherNode(this)==other_nodeNmbr)
				return this.edges.get(i).getWeight();
		}
		return -1;

	}
}
