
//contains a list of weights

import java.util.ArrayList;
import java.util.Objects;

public class Node {
	
		private int nodeidx;
		private ArrayList<Edge> edges;

	public Node(int nodeidx, ArrayList<Edge> edges) {
		this.nodeidx = nodeidx;
		this.edges = edges;
	}
	public int nextHop(ArrayList<Integer> Path){
		int nextNode=0;
		if(Path.get(0)==nodeidx){
			//TODO meter ferormonas nessa cena
		}
		try{
		if(edges.size()==1 && Path.get(Path.size()-1)==edges.get(0).otherNode(this)){
			//TODO must turn back this is dead end
		}
		//TODO computation to get next hop
		}
		catch(NotThisEdge_exeption ex){
			System.out.println("Fam, tou so fdd, tenho uma edge que nao ta ligada a mim, sou o node");
			System.out.println(nodeidx);
			System.out.println(" e o edge tinha chave");
			System.out.println(ex.this_edge);
			System.out.println(ex.getMessage());
		}
		return nextNode;
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
}
