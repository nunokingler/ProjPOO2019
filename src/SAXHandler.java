import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler{

    private String currentValue=null;
    private List<Integer> nodes = new ArrayList<>();
    private List<Integer> target_nodes = new ArrayList<>();
    
    private int node1, node2;
    private List<Float> edges = null; 
    private float edge;
    
    private ArrayList<Integer> parametersInt=new ArrayList<>();
    private ArrayList<Float> parametersFloat=new ArrayList<>();
    
    private float evap1;
    private float evap2;
    
    private int nb_nodes;
	private int nest_node;
	
	private float f_inst;
	private float phe_level;
	private int ant_colsize;
	
	float alpha;
	float beta;
	float delta;
    
   // private float node1;
   // private float node2;
    
    
    static String fileName;
	private ArrayList<Float> moves= new ArrayList<>();

	@Override
    public void startDocument() throws SAXException {
    	
    	//System.out.println("Beginning the parsing of " + fileName);
        nodes = new ArrayList<Integer>();
        edges = new ArrayList<Float>();

    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue = new String(ch, start, length);
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	if (qName.equalsIgnoreCase("simulation")) {
            
            if(attributes.getLength() > 0){
            	
                String f_instant = attributes.getValue("finalinst");
                String p_level = attributes.getValue("plevel");
                String s_antcol = attributes.getValue("antcolsize");
                
                f_inst=Float.valueOf(f_instant);
    			phe_level=Float.valueOf(p_level);
    			ant_colsize=Integer.valueOf(s_antcol);

    			parametersFloat.add(f_inst);
    			parametersFloat.add(phe_level);
    			parametersInt.add(ant_colsize);
    			
                //Simulation simul  = new Simulation(Float.valueOf(f_instant),Float.valueOf(p_level), Integer.valueOf(s_antcol));
                //System.out.println("Final Instant: " + Float.valueOf(f_instant) + " Colony Size: " + Integer.valueOf(s_antcol) + " Pheromone level: " + Float.valueOf(p_level));
            }    
        }
    	else if (qName.equalsIgnoreCase("graph")) {
    		if(attributes.getLength() > 0){
    			String n_nodes = attributes.getValue("nbnodes");
    			String n_nest = attributes.getValue("nestnode");
    			
    			nb_nodes=Integer.valueOf(n_nodes);
    			nest_node=Integer.valueOf(n_nest);
    			
    			parametersInt.add(nest_node);

                //System.out.println("Number of nodes: " + nb_nodes + " Nest node: " + nest_node);

    			
                //Graph graph = new Graph(Integer.valueOf(n_nodes),Integer.valueOf(n_nest), Float.valueOf(in_eta), Float.valueOf(in_rho));
    		}
    	}
    	else if (qName.equalsIgnoreCase("node")) {
    		    		
    		if(attributes.getLength() > 0){
    			
    			String id_node = attributes.getValue("nodeidx");
    			
    			node1=Integer.valueOf(id_node);
    			
                //System.out.println("Node ID: " + Integer.valueOf(id_node));

				//nodes.add(node1);

      		}
    	}
    	else if("weight".equals(qName)) {

        	 if(attributes.getLength() > 0){
        		 
        		 String t_node = attributes.getValue("targetnode");
        		// node2=Integer.valueOf(t_node);
        		// node2 = new Node(Integer.valueOf(t_node));
        		 int targ_node=Integer.valueOf(t_node);
        		 
        		 //System.out.print("Target node: " + Integer.valueOf(t_node));
        		 target_nodes.add(targ_node);
				 nodes.add(node1);
        	 }
        }
    	else if (qName.equalsIgnoreCase("move")) {
    		if(attributes.getLength() > 0){
    		
    			String in_alpha = attributes.getValue("alpha");
    			String in_beta = attributes.getValue("beta");
    			String in_delta = attributes.getValue("delta");
    			//alpha=Float.valueOf(in_alpha);
    			//beta=Float.valueOf(in_beta);
    			//delta=Float.valueOf(in_delta);
    			moves.add(Float.valueOf(in_alpha));
    			moves.add(Float.valueOf(in_beta));
    			moves.add(Float.valueOf(in_delta));
               // System.out.println("Alpha: " + alpha  + " Beta: " + beta + " Delta: " + delta);
    		}
    	}
    	else if (qName.equalsIgnoreCase("evaporation")) {
    		if(attributes.getLength() > 0){
    			
    			String in_eta = attributes.getValue("eta");
    			String in_rho = attributes.getValue("rho");
    			evap1=Float.valueOf(in_eta);
    			evap2=Float.valueOf(in_rho);
    			
    			parametersFloat.add(evap1);
    			parametersFloat.add(evap2);
    			
                //System.out.println("Eta: " + evap1 + " Rho: " + evap2);
                
    		}	
    		
    	}        		 

    	
    }

	public void endElement(String namespaceURI,String localName,String qname){

		switch(qname){
		
		   case "weight":      float peso = Float.valueOf(currentValue);
           					  // System.out.print(" Peso: " + peso + "\n");
		   					   //edge = new Edge(peso,evap1, evap2, node1,node2);
		   					  
		   					   edges.add(peso);
		   					   
		                       break;
		                       
		}
	}
	
	    
	
	  public List<Integer> getNodes() {
	        return nodes;
	    }
	  
	  public List<Integer> getTargetNode(){
		  return target_nodes;
	  }

	  public List<Float> getEdges() {
	        return edges;
	    }
	  
	  public ArrayList<Float> getParamFloat() {
		  return parametersFloat;
	  }
	  

	  public ArrayList<Integer> getParamInt() {
		  return parametersInt;
	  }

	  public ArrayList<Float> getMoves(){
    	return moves;
	  }
	  	  
	public void endDocument(){
	
		 //System.out.println("Parsing concluded");
		} 
}